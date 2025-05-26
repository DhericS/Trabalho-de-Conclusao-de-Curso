package com.example.NovoTesteCrud.domain.exercicios;

public enum GrupoMuscular {
    PEITO("Peito"),
    COSTA("Costas"),
    PERNAS("Pernas"),
    BRACOS("Braços"),
    OMBROS("Ombros");

    private final String nome;

    GrupoMuscular(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
