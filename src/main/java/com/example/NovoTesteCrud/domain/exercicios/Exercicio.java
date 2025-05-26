package com.example.NovoTesteCrud.domain.exercicios;

import com.example.NovoTesteCrud.domain.treino.Treino;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "exercicios")
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer series;
    private Integer repeticoes;

    @Enumerated(EnumType.STRING)
    private GrupoMuscular grupoMuscular;

    @ManyToOne
    @JoinColumn(name = "treino_id", nullable = false)
    @JsonBackReference
    private Treino treino;
}
