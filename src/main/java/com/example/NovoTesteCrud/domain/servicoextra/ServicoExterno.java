package com.example.NovoTesteCrud.domain.servicoextra;

import com.example.NovoTesteCrud.domain.acad.Academia;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "servicos_externos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicoExterno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String tipo;

    private String descricao;

    private String contato;

    private String link;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academia_id", nullable = false)
    private Academia academia;

}
