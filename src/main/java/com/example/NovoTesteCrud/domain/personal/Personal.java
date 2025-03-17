package com.example.NovoTesteCrud.domain.personal;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "personais")
@Table(name = "personais")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String telefone;

    @Column(unique = true)
    private String cref;
}
