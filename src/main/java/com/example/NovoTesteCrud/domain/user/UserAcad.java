package com.example.NovoTesteCrud.domain.user;


import com.example.NovoTesteCrud.domain.userbase.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_academia")
@NoArgsConstructor
public class UserAcad extends Usuario {

    public UserAcad(String name, String email, String senha, String telefone) {
        super(name, email, senha, telefone);
    }

    public void atualizarDados(RequestUserAcad data) {
        setName(data.name());
        setEmail(data.email());
        setSenha(data.senha());
        setTelefone(data.telefone());
    }
}