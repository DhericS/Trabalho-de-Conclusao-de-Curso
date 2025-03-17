package com.example.NovoTesteCrud.domain.user;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "user_academia")
@Entity(name = "user_academia")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class UserAcad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome é obrigatório")
    private String name;
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;
    @NotBlank(message = "A senha é obrigatória")
    private String senha;
    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;


}
