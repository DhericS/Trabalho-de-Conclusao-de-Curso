package com.example.NovoTesteCrud.domain.feedback;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.personal.Personal;
import com.example.NovoTesteCrud.domain.user.UserAcad;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Column(nullable = false)
    private Integer estrelas;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserAcad user;

    @ManyToOne
    @JoinColumn(name = "academia_id")
    private Academia academia;

    @ManyToOne
    @JoinColumn(name = "personal_id")
    private Personal personal;
}
