package com.example.NovoTesteCrud.domain.autenticacao;

import com.example.NovoTesteCrud.domain.userbase.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AutenticacaoRegister {

    @NotBlank
    private String nome;

    @NotBlank @Email
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private String telefone;

    @NotBlank
    private String tipoUsuario;

    @NotBlank
    private String role;

    private String cnpj;
    private String cref;
    private String imagemUrl;

    public Role getRoleAsEnum() {
        return Role.valueOf(role.toUpperCase());
    }
}
