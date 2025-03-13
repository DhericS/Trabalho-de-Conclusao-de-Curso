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

    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @NotBlank(message = "O endereco é obrigatório")
    private String endereco;
    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    @OneToMany(mappedBy = "academia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atividade> activities;
}
