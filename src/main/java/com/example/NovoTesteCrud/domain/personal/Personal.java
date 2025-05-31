package com.example.NovoTesteCrud.domain.personal;

import com.example.NovoTesteCrud.domain.dieta.Dieta;
import com.example.NovoTesteCrud.domain.personal.dto.RequestPersonal;
import com.example.NovoTesteCrud.domain.treino.Treino;
import com.example.NovoTesteCrud.domain.userbase.Role;
import com.example.NovoTesteCrud.domain.userbase.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Delegate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personais")
@NoArgsConstructor
@Getter
@Setter
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Delegate
    private Usuario usuario;

    @Column(unique = true, nullable = false)
    private String cref;


    private String imagemUrl;

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Treino> treinos = new ArrayList<>();

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dieta> dietas = new ArrayList<>();


    public Personal(Long id, String nome, String email, String senha, String telefone, String cref, String imagemUrl, Role role) {
        this.usuario = new Usuario(nome, email, senha, telefone, role);
        this.id = id;
        this.cref = cref;
    }

    public void atualizarDados(RequestPersonal data) {
        usuario.setNome(data.nome());
        usuario.setEmail(data.email());
        usuario.setTelefone(data.telefone());
        this.imagemUrl = data.imagemUrl();
        this.cref = data.cref();
    }
}
