package com.example.NovoTesteCrud.domain.useradmin;

import com.example.NovoTesteCrud.domain.useradmin.dto.RequestUserAdmin;
import com.example.NovoTesteCrud.domain.userbase.Role;
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

    public UserAdmin(Long id, String nome, String email, String senha, String telefone, Role role) {
        this.usuario = new Usuario(nome, email, senha, telefone, role);
        this.id = id;
    }

    public void atualizarDados(RequestUserAdmin data) {
        usuario.setNome(data.nome());
        usuario.setEmail(data.email());
        usuario.setTelefone(data.telefone());
    }
}
