package com.example.NovoTesteCrud.domain.useracadadmin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAcadAdminRepository extends JpaRepository<UserAcadAdmin, Long> {
    Optional<UserAcadAdmin> findByEmail(String email);
}
