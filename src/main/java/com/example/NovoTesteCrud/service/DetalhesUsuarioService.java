package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.repository.PersonalRepository;
import com.example.NovoTesteCrud.repository.UserAcadAdminRepository;
import com.example.NovoTesteCrud.repository.UserAcadRepository;
import com.example.NovoTesteCrud.repository.UserAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalhesUsuarioService implements UserDetailsService {

    @Autowired private UserAcadRepository userAcadRepository;
    @Autowired private UserAdminRepository userAdminRepository;
    @Autowired private UserAcadAdminRepository userAcadAdminRepository;
    @Autowired private PersonalRepository personalRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userAcadRepository.findByUsuario_Email(email)
                .map(user -> buildUserDetails(user.getUsuario().getEmail(), user.getUsuario().getSenha(), user.getRole().name()))
                .or(() -> userAdminRepository.findByUsuario_Email(email)
                        .map(user -> buildUserDetails(user.getUsuario().getEmail(), user.getUsuario().getSenha(), user.getRole().name())))
                .or(() -> userAcadAdminRepository.findByUsuario_Email(email)
                        .map(user -> buildUserDetails(user.getUsuario().getEmail(), user.getUsuario().getSenha(), user.getRole().name())))
                .or(() -> personalRepository.findByUsuario_Email(email)
                        .map(user -> buildUserDetails(user.getUsuario().getEmail(), user.getUsuario().getSenha(), user.getRole().name())))
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    private UserDetails buildUserDetails(String email, String senha, String role) {
        return new User(email, senha, List.of(new SimpleGrantedAuthority("ROLE_" + role)));
    }
}