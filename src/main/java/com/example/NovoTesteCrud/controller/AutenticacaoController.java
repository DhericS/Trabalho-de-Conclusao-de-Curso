package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.autenticacao.dto.AutenticacaoRegisterDTO;
import com.example.NovoTesteCrud.domain.autenticacao.dto.AutenticacaoRequestDTO;
import com.example.NovoTesteCrud.domain.autenticacao.dto.AutenticacaoResponseDTO;
import com.example.NovoTesteCrud.domain.personal.Personal;
import com.example.NovoTesteCrud.domain.user.UserAcad;
import com.example.NovoTesteCrud.domain.useracadadmin.UserAcadAdmin;
import com.example.NovoTesteCrud.domain.useradmin.UserAdmin;
import com.example.NovoTesteCrud.domain.userbase.Usuario;
import com.example.NovoTesteCrud.repository.PersonalRepository;
import com.example.NovoTesteCrud.repository.UserAcadAdminRepository;
import com.example.NovoTesteCrud.repository.UserAcadRepository;
import com.example.NovoTesteCrud.repository.UserAdminRepository;
import com.example.NovoTesteCrud.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserAcadRepository userAcadRepository;
    @Autowired private UserAdminRepository userAdminRepository;
    @Autowired private UserAcadAdminRepository userAcadAdminRepository;
    @Autowired private PersonalRepository personalRepository;

    @PostMapping("/login")
    public ResponseEntity<AutenticacaoResponseDTO> login(@RequestBody @Valid AutenticacaoRequestDTO dto) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.email(), dto.senha())
            );

            if (userAcadRepository.findByUsuario_Email(dto.email()).isPresent()) {
                var user = userAcadRepository.findByUsuario_Email(dto.email()).get();
                String token = jwtUtil.generateToken(user.getUsuario().getEmail(), user.getRole());
                return ResponseEntity.ok(new AutenticacaoResponseDTO(token));
            }

            if (userAdminRepository.findByUsuario_Email(dto.email()).isPresent()) {
                var user = userAdminRepository.findByUsuario_Email(dto.email()).get();
                String token = jwtUtil.generateToken(user.getUsuario().getEmail(), user.getRole());
                return ResponseEntity.ok(new AutenticacaoResponseDTO(token));
            }

            if (userAcadAdminRepository.findByUsuario_Email(dto.email()).isPresent()) {
                var user = userAcadAdminRepository.findByUsuario_Email(dto.email()).get();
                String token = jwtUtil.generateToken(user.getUsuario().getEmail(), user.getUsuario().getRole());
                return ResponseEntity.ok(new AutenticacaoResponseDTO(token));
            }

            if (personalRepository.findByUsuario_Email(dto.email()).isPresent()) {
                var user = personalRepository.findByUsuario_Email(dto.email()).get();
                String token = jwtUtil.generateToken(user.getUsuario().getEmail(), user.getUsuario().getRole());
                return ResponseEntity.ok(new AutenticacaoResponseDTO(token));
            }

            throw new UsernameNotFoundException("Usuário não encontrado");

        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciais inválidas", e);
        }
    }


    @PostMapping("/register")
    public String register(@RequestBody @Valid AutenticacaoRegisterDTO dto) {

        switch (dto.getTipoUsuario().toLowerCase()) {
            case "useracad" -> {
                var user = new UserAcad(
                        null,
                        dto.getNome(),
                        dto.getEmail(),
                        passwordEncoder.encode(dto.getSenha()),
                        dto.getTelefone(),
                        dto.getRoleAsEnum()
                );
                userAcadRepository.save(user);
            }

            case "useradmin" -> {
                var user = new UserAdmin(
                        null,
                        dto.getNome(),
                        dto.getEmail(),
                        passwordEncoder.encode(dto.getSenha()),
                        dto.getTelefone(),
                        dto.getRoleAsEnum()
                );
                userAdminRepository.save(user);
            }




            case "personal" -> {
                var user = new Personal(
                        null,
                        dto.getNome(),
                        dto.getEmail(),
                        passwordEncoder.encode(dto.getSenha()),
                        dto.getTelefone(),
                        dto.getCref(),     // vem do DTO
                        null               // academia será associada depois
                );
                personalRepository.save(user);
            }


            default -> throw new IllegalArgumentException("Tipo de usuário inválido.");
        }

        return "Usuário registrado com sucesso!";
    }

}
