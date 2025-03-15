package com.example.NovoTesteCrud.domain.acad;

import com.example.NovoTesteCrud.domain.atvd.Atividade;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "academias")
public class Academia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;
    private String telefone;

    @OneToMany(mappedBy = "academia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atividade> activities;
}
