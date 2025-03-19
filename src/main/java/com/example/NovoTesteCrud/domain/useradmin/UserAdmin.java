package com.example.NovoTesteCrud.domain.useradmin;

import com.example.NovoTesteCrud.domain.userbase.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "user_admin")
@Entity(name = "user_admin")
@NoArgsConstructor
public class UserAdmin extends Usuario {

    public UserAdmin(String name, String email, String senha, String telefone) {
        super(name, email, senha, telefone);
    }

    public void atualizarDados(RequestUserAdmin data) {
        setName(data.name());
        setEmail(data.email());
        setSenha(data.senha());
        setTelefone(data.telefone());
    }
}