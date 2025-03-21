package com.example.NovoTesteCrud.domain.useradmin;

import com.example.NovoTesteCrud.domain.userbase.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Delegate;

@Entity
@Table(name = "user_admin")
@NoArgsConstructor
@Getter
@Setter
public class UserAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Delegate
    private Usuario usuario;

    public UserAdmin(Long id, String name, String email, String senha, String telefone) {
        this.usuario = new Usuario(name, email, senha, telefone);
        this.id = id;
    }

    public void atualizarDados(RequestUserAdmin data) {
        usuario.setNome(data.name());
        usuario.setEmail(data.email());
        usuario.setSenha(data.senha());
        usuario.setTelefone(data.telefone());
    }
}
