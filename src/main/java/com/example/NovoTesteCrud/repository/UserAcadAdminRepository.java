package com.example.NovoTesteCrud.repository;

import com.example.NovoTesteCrud.domain.useracadadmin.UserAcadAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAcadAdminRepository extends JpaRepository<UserAcadAdmin, Long> {
    Optional<UserAcadAdmin> findByUsuario_Email(String email);
}
