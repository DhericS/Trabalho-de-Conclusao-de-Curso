package com.example.NovoTesteCrud.domain.atvd;

import com.example.NovoTesteCrud.domain.acad.Academia;
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


    @ManyToOne
    @JoinColumn(name = "academia_id", nullable = false)
    private Academia academia;
}
