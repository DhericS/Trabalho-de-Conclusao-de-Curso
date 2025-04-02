package com.example.NovoTesteCrud.repository;

import com.example.NovoTesteCrud.domain.personal.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalRepository extends JpaRepository<Personal, Long> {

    Optional<Personal> findByUsuario_Email(String email);
}
