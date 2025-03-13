package com.example.NovoTesteCrud.domain.user;

import com.example.NovoTesteCrud.domain.useracadadmin.UserAcadAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAcadRepository extends JpaRepository<UserAcad, Long> {
    Optional<UserAcad> findByEmail(String email);
}
