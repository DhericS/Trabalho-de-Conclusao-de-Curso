package com.example.NovoTesteCrud.domain.userbase;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository<T extends Usuario> extends JpaRepository<T, Long> {
}
