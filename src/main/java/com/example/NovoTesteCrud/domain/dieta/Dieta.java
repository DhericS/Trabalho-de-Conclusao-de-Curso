package com.example.NovoTesteCrud.domain.dieta;

import com.example.NovoTesteCrud.domain.user.UserAcad;
import com.example.NovoTesteCrud.domain.userbase.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "dieta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dieta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    private Integer calorias;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Objetivo objetivo;

    @ManyToOne
    @JoinColumn(name = "user_acad_id")
    private UserAcad userAcad;
    @NotNull

    public enum Objetivo {
        BULKING, CUTTING
    }
}