package com.example.NovoTesteCrud.repository;

import com.example.NovoTesteCrud.domain.user.UserAcad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAcadRepository extends JpaRepository<UserAcad, Long> {
    Optional<UserAcad> findByUsuario_Email(String email);
}
