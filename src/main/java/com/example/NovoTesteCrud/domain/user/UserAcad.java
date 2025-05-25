package com.example.NovoTesteCrud.domain.user;

import com.example.NovoTesteCrud.domain.user.dto.RequestUserAcad;
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

    private String imagemUrl;

    public UserAcad(Long id, String nome, String email, String senha, String telefone, String imagemUrl, Role role) {
        this.usuario = new Usuario(nome, email, senha, telefone, role);
        this.id = id;
    }

    public UserAcad(Long id, Usuario usuario, Role role) {
        this.id = id;
        this.usuario = usuario;
    }

    public void atualizarDados(RequestUserAcad data) {
        usuario.setNome(data.nome());
        usuario.setEmail(data.email());
        usuario.setSenha(data.senha());
        usuario.setTelefone(data.telefone());
        this.imagemUrl = data.imagemUrl();

    }
}
