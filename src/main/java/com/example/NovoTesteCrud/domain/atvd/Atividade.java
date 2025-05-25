package com.example.NovoTesteCrud.domain.atvd;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.atvd.enums.DiaSemana;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "atividades")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nome;

    private String horario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiaSemana diaSemana;

    @ManyToOne
    @JoinColumn(name = "academia_id", nullable = false)
    private Academia academia;
}
