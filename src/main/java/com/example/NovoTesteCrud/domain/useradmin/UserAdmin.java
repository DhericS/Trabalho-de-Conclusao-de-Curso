package com.example.NovoTesteCrud.domain.useradmin;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "user_admin")
@Entity(name = "user_admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String senha;
}
