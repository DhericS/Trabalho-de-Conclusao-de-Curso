package com.example.NovoTesteCrud.domain.personal;

import com.example.NovoTesteCrud.domain.userbase.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Delegate;

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

    public Personal(Long id, String name, String email, String senha, String telefone, String cref) {
        this.usuario = new Usuario(name, email, senha, telefone);
        this.id = id;
        this.cref = cref;
    }

    public void atualizarDados(RequestPersonal data) {
        usuario.setNome(data.name());
        usuario.setEmail(data.email());
        usuario.setSenha(data.senha());
        usuario.setTelefone(data.telefone());
        this.cref = data.cref();
    }
}
