package com.example.NovoTesteCrud.domain.user;

import com.example.NovoTesteCrud.domain.userbase.Role;
import com.example.NovoTesteCrud.domain.userbase.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

import jakarta.persistence.*;

@Entity
@Table(name = "user_academia")
@NoArgsConstructor
@Data
public class UserAcad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Delegate
    private Usuario usuario;

    public UserAcad(Long id, String name, String email, String senha, String telefone, Role role) {
        this.usuario = new Usuario(name, email, senha, telefone, role);
        this.id = id;
    }

    public UserAcad(Long id, Usuario usuario, Role role) {
        this.id = id;
        this.usuario = usuario;
    }

    public void atualizarDados(RequestUserAcad data) {
        usuario.setNome(data.name());
        usuario.setEmail(data.email());
        usuario.setSenha(data.senha());
        usuario.setTelefone(data.telefone());
    }
}
