package com.example.NovoTesteCrud.domain.planoacad;

import com.example.NovoTesteCrud.domain.acad.Academia;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "planos_academia")
public class PlanoAcademia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "academia_id", nullable = false)
    private Academia academia;
}
