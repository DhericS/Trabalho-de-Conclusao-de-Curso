package com.example.NovoTesteCrud.domain.userbase;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Usuario {

    @Column(name = "name")
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    private String telefone;

    public Usuario(String name, String email, String senha, String telefone) {
        this.nome = name;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }
}
