package com.example.NovoTesteCrud.domain.personal;

import com.example.NovoTesteCrud.domain.userbase.RequestUsuario;
import com.example.NovoTesteCrud.domain.userbase.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "personais")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Personal extends Usuario {

    @Column(unique = true, nullable = false)
    private String cref;

    public Personal(String name, String email, String senha, String telefone, String cref) {
        super(null, name, email, senha, telefone);
        this.cref = cref;
    }

    public void atualizarDados(RequestUsuario data) {
        super.atualizarDados(data);

        if (data instanceof RequestPersonal personalData) {
            this.cref = personalData.cref();
        }
    }

}