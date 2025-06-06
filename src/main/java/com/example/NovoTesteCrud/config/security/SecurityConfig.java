package com.example.NovoTesteCrud.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;


    // Configuração de segurança para a aplicação
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> {})
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                        .requestMatchers("/static/**", "/css/**", "/js/**", "/assets/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/forgot-password").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/reset-password").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/reset-password").permitAll()
                        .requestMatchers(HttpMethod.GET, "reset-password").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuarios").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuarios/me").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuarios/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios/{id}/upload-imagem").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios/upload-imagem-temp").permitAll()
                        .requestMatchers(HttpMethod.GET, "/academia").permitAll()
                        .requestMatchers(HttpMethod.GET, "/academias/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/academia/externas-detalhes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/academia/externas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/academia/filtro").permitAll()
                        .requestMatchers(HttpMethod.GET, "/academia/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/academia/{id}/upload-imagem").permitAll()
                        .requestMatchers(HttpMethod.GET, "/academia/upload-imagem-temp").permitAll()
                        .requestMatchers(HttpMethod.POST, "/academia/{id}/upload-imagem").permitAll()
                        .requestMatchers(HttpMethod.POST, "/academia/upload-imagem-temp").permitAll()
                        .requestMatchers(HttpMethod.GET, "/atividades").permitAll()
                        .requestMatchers("/academias-externas/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/avaliacoes/academia/{academiaId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/avaliacoes/personal/{personalId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/avaliacoes/user/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/avaliacoes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/dietas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/dietas/filtro").permitAll()
                        .requestMatchers(HttpMethod.GET, "/dieta/filtro").permitAll()
                        .requestMatchers(HttpMethod.GET, "/dieta/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/exercicios").permitAll()
                        .requestMatchers(HttpMethod.GET, "/planos/academia/{academiaId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/treino").permitAll()
                        .requestMatchers(HttpMethod.GET, "/treino/filtro").permitAll()
                        .requestMatchers(HttpMethod.GET, "/treinos/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/treino/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/login").permitAll()/**/
                        .requestMatchers(HttpMethod.GET, "/cadastro").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reset-senha").permitAll()
                        .requestMatchers(HttpMethod.GET, "/troca-senha").permitAll()
                        .requestMatchers(HttpMethod.GET, "/senha").permitAll()
                        .requestMatchers(HttpMethod.GET, "/perfil").permitAll()
                        .requestMatchers(HttpMethod.GET, "/perfil-usuario").permitAll()
                        .requestMatchers(HttpMethod.GET, "/perfil-academias").permitAll()
                        .requestMatchers(HttpMethod.GET, "/perfil-personais").permitAll()
                        .requestMatchers(HttpMethod.GET, "/academias").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cadastrar-academia").permitAll()
                        .requestMatchers(HttpMethod.GET, "/detalhes-academias").permitAll()
                        .requestMatchers(HttpMethod.GET, "/personais").permitAll()
                        .requestMatchers(HttpMethod.GET, "/detalhes-personais").permitAll()
                        .requestMatchers(HttpMethod.GET, "/treinos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/detalhes-treinos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cadastrar-treino").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/pagina-dietas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cadastrar_dietas").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/dieta/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/dietas/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/dietas/{id}/editar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/dietas/{id}/deletar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/dietas").permitAll()
                        .anyRequest().authenticated()

                ).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
    }

    // Configuração do PasswordEncoder para criptografar senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuração do AuthenticationManager para autenticação
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Configuração de CORS para permitir requisições de diferentes origens
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("https://*.guidesfit.com.br", "https://guidesfit.com.br", "http://localhost:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
