package com.example.NovoTesteCrud.domain.avaliacao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByAcademiaId(Long academiaId);
    List<Avaliacao> findByPersonalId(Long personalId);
    List<Avaliacao> findByUserId(Long userId);
}
