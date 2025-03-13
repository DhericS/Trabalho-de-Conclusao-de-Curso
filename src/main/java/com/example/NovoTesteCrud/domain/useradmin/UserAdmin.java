package com.example.NovoTesteCrud.domain.useradmin;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "user_admin")
@Entity(name = "user_admin")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class UserAdmin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome é obrigatório")
    private String name;
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;
    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    public UserAdmin(RequestUserAdmin requestUserAdmin){
        this.name = requestUserAdmin.name();
        this.email = requestUserAdmin.email();
        this.senha = requestUserAdmin.senha();
    }
}
