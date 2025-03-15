package com.example.NovoTesteCrud.domain.atvd;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    List<Atividade> findByAcademiaId(Long academiaId);
}
