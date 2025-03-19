package com.example.NovoTesteCrud.domain.userbase;

import com.example.NovoTesteCrud.domain.personal.Personal;
import com.example.NovoTesteCrud.domain.personal.RequestPersonal;
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

    public void atualizarDados(RequestUsuario data) {
        this.name = data.name();
        this.email = data.email();
        this.senha = data.senha();
        this.telefone = data.telefone();
    }


    public abstract void atualizarDados(IRequestUsuario data);
}
