package com.example.NovoTesteCrud.domain.user;

import com.example.NovoTesteCrud.domain.avaliacao.Avaliacao;
import com.example.NovoTesteCrud.domain.dieta.Dieta;
import com.example.NovoTesteCrud.domain.user.dto.RequestUserAcad;
import com.example.NovoTesteCrud.domain.userbase.Role;
import com.example.NovoTesteCrud.domain.userbase.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Delegate;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "userAcad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dieta> dietas = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avaliacao> avaliacoes = new ArrayList<>();


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
