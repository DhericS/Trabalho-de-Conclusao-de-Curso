package com.example.NovoTesteCrud.domain.useracadadmin;

import com.example.NovoTesteCrud.domain.acad.Academia;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "user_acad_admin")
@Table(name = "user_acad_admin")
public class UserAcadAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @Column(name = "cnpj", unique = true, length = 14)
    private String cnpj;

    private String telefone;

    @OneToOne
    @JoinColumn(name = "academia_id", unique = true)
    private Academia academia;
}
