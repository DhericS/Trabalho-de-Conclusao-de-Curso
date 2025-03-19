package com.example.NovoTesteCrud.dto;

import com.example.NovoTesteCrud.domain.userbase.Usuario;
import lombok.Getter;

@Getter
public class UsuarioDTO {
    private Long id;
    private String name;
    private String email;
    private String telefone;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.name = usuario.getName();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();
    }
}
