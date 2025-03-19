package com.example.NovoTesteCrud.dto;

import com.example.NovoTesteCrud.domain.useracadadmin.UserAcadAdmin;

public record UserAcadAdminDTO(Long id, String name, String email, String cnpj, Long academiaId) {
    public UserAcadAdminDTO(UserAcadAdmin userAcadAdmin) {
        this(
                userAcadAdmin.getId(),
                userAcadAdmin.getName(),
                userAcadAdmin.getEmail(),
                userAcadAdmin.getCnpj(),
                userAcadAdmin.getAcademia().getId()
        );
    }
}
