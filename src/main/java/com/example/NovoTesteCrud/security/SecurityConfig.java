package com.example.NovoTesteCrud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/auth/**",                       // Acesso sem autenticação para autenticação
                        "/",                               // Página inicial
                        "/index.html",                     // Página inicial
                        "/login",                           // Página de login
                        "/cadastro",                       // Página de cadastro
                        "/reset-senha",                    // Página de reset de senha
                        "/troca-senha",                    // Página de troca de senha
                        "/senha",                          // Página de alteração de senha
                        "/perfil-usuario",                 // Página do perfil do usuário
                        "/perfil-academias",               // Página do perfil das academias
                        "/perfil-personais",               // Página do perfil dos personal trainers
                        "/academias",                      // Página das academias
                        "/cadastrar-academia",             // Página de cadastro de academia
                        "/detalhes-academias",             // Página de detalhes de academia
                        "/personais",                      // Página de personal trainers
                        "/detalhes-personais",             // Página de detalhes dos personal trainers
                        "/treinos",                        // Página de treinos
                        "/detalhes-treinos",               // Página de detalhes dos treinos
                        "/static/**",                      // Certifique-se de permitir acesso à pasta estática
                        "/favicon.ico",                    // Ícone do site
                        "/**/*.html"                       // Páginas Thymeleaf que geram arquivos HTML
                ).permitAll()                         // Permitir acesso a todas as páginas estáticas e de login

                .anyRequest().authenticated()           // Exige autenticação para todas as outras requisições

                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Sessões sem estado para API
        // Adiciona o filtro JWT antes do filtro de autenticação padrão
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}