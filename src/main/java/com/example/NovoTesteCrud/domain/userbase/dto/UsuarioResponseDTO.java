package com.example.NovoTesteCrud.domain.userbase.dto;

import com.example.NovoTesteCrud.domain.user.UserAcad;
import com.example.NovoTesteCrud.domain.useradmin.UserAdmin;
import com.example.NovoTesteCrud.domain.useracadadmin.UserAcadAdmin;
import com.example.NovoTesteCrud.domain.personal.Personal;
import com.example.NovoTesteCrud.domain.userbase.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String tipoUsuario;
    private String cnpj;
    private String cref;
    private Long academiaId;

    public UsuarioResponseDTO(UserAcad user) {
        this.id = user.getId();
        this.nome = user.getUsuario().getNome();
        this.email = user.getUsuario().getEmail();
        this.telefone = user.getUsuario().getTelefone();
        this.tipoUsuario = "useracad";
    }

    public UsuarioResponseDTO(UserAdmin user) {
        this.id = user.getId();
        this.nome = user.getUsuario().getNome();
        this.email = user.getUsuario().getEmail();
        this.telefone = user.getUsuario().getTelefone();
        this.tipoUsuario = "useradmin";
    }

    public UsuarioResponseDTO(UserAcadAdmin user) {
        this.id = user.getId();
        this.nome = user.getUsuario().getNome();
        this.email = user.getUsuario().getEmail();
        this.telefone = user.getUsuario().getTelefone();
        this.cnpj = user.getCnpj();
        this.academiaId = user.getAcademia() != null ? user.getAcademia().getId() : null;
        this.tipoUsuario = "useracadadmin";
    }

    public UsuarioResponseDTO(Personal user) {
        this.id = user.getId();
        this.nome = user.getUsuario().getNome();
        this.email = user.getUsuario().getEmail();
        this.telefone = user.getUsuario().getTelefone();
        this.cref = user.getCref();
        this.tipoUsuario = "personal";
    }

    public UsuarioResponseDTO(Usuario value) {
        this.email = value.getEmail();
    }
}
