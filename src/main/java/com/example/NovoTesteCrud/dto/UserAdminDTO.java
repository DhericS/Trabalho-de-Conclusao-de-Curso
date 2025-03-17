package com.example.NovoTesteCrud.dto;

import com.example.NovoTesteCrud.domain.useradmin.UserAdmin;

public record UserAdminDTO(Long id, String name, String email) {
    public UserAdminDTO(UserAdmin userAdmin) {
        this(userAdmin.getId(), userAdmin.getName(), userAdmin.getEmail());
    }
}
