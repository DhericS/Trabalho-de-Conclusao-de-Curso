package com.example.NovoTesteCrud.repository;

import com.example.NovoTesteCrud.domain.useradmin.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAdminRepository extends JpaRepository <UserAdmin, Long> {
    Optional<UserAdmin> findByUsuario_Email(String email);
}
