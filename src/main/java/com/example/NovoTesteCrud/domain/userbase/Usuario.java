package com.example.NovoTesteCrud.domain.userbase;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected String name;

    @Column(unique = true, nullable = false)
    protected String email;

    @Column(name = "senha", nullable = false)
    protected String senha;

    protected String telefone;

    public Usuario(String name, String email, String senha, String telefone) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    public void atualizarDados(Usuario data) {
        if (data.name != null) this.name = data.name;
        if (data.email != null) this.email = data.email;
        if (data.senha != null) this.senha = data.senha;
        if (data.telefone != null) this.telefone = data.telefone;
    }


}
