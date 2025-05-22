package com.example.NovoTesteCrud.domain.avaliacao;

import com.example.NovoTesteCrud.domain.avaliacao.enums.TipoEntidade;
import com.example.NovoTesteCrud.domain.user.UserAcad;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacao",
        uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "tipo_entidade", "entidade_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nota; // 1 a 5

    private String comentario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserAcad usuarioId;


    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_entidade", nullable = false)
    private TipoEntidade tipoEntidade;

    @Column(name = "entidade_id", nullable = false)
    private Long entidadeId;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    public void prePersist() {
        dataCriacao = LocalDateTime.now();
    }
}
