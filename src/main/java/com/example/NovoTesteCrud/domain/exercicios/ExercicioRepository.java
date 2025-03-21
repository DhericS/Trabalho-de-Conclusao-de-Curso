package com.example.NovoTesteCrud.domain.exercicios;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
    List<Exercicio> buscarPorGrupoMuscular(GrupoMuscular grupoMuscular);
}
