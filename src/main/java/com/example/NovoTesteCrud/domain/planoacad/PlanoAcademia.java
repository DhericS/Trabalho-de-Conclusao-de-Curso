package com.example.NovoTesteCrud.domain.planoacad;

import com.example.NovoTesteCrud.domain.acad.Academia;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "planos_academia")
public class PlanoAcademia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotBlank(message = "O nome do plano é obrigatório")
    private String nome;

    @NotBlank(message = "A descricao é obrigatória")
    private String descricao;

    @NotNull(message = "O preço do plano é obrigatório")
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "academia_id", nullable = false)
    private Academia academia;
}
