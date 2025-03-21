package com.example.NovoTesteCrud.domain.useradmin;

import com.example.NovoTesteCrud.domain.useracadadmin.UserAcadAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAdminRepository extends JpaRepository <UserAdmin, Long> {
    Optional<UserAdmin> findByUsuario_Email(String email);
}
