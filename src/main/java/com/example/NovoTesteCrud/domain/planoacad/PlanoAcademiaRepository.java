package com.example.NovoTesteCrud.domain.planoacad;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlanoAcademiaRepository extends JpaRepository<PlanoAcademia, Long> {
    List<PlanoAcademia> findByAcademiaId(Long academiaId);
}
