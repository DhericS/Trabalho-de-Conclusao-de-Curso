package com.example.NovoTesteCrud.domain.useradmin;

import com.example.NovoTesteCrud.domain.userbase.IRequestUsuario;
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

    @Override
    public void atualizarDados(IRequestUsuario data) {
        setName(data.name());
        setEmail(data.email());
        setSenha(data.senha());
        setTelefone(data.telefone());
    }
}