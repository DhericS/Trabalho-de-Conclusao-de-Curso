package com.example.NovoTesteCrud.domain.userbase.dto;

import com.example.NovoTesteCrud.domain.personal.dto.RequestPersonal;
import com.example.NovoTesteCrud.domain.user.dto.RequestUserAcad;
import com.example.NovoTesteCrud.domain.useracadadmin.RequestUserAcadAdmin;
import com.example.NovoTesteCrud.domain.useradmin.dto.RequestUserAdmin;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipoUsuario")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RequestUserAcad.class, name = "useracad"),
        @JsonSubTypes.Type(value = RequestUserAdmin.class, name = "useradmin"),
        @JsonSubTypes.Type(value = RequestUserAcadAdmin.class, name = "useracadadmin"),
        @JsonSubTypes.Type(value = RequestPersonal.class, name = "personal")
})
public interface IRequestUsuario {
    String nome();
    String email();
    String senha();
    String telefone();
    String tipoUsuario();
    Long id();
}