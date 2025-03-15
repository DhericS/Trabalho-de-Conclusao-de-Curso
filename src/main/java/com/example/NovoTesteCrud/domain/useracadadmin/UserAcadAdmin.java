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

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "O e-mail é obrigatório")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String password;

    @NotBlank(message = "O CNPJ é obrigatório")
    @Column(name = "cnpj", unique = true, length = 14)
    private String cnpj;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    @OneToOne
    @JoinColumn(name = "academia_id", unique = true)
    private Academia academia;
}
