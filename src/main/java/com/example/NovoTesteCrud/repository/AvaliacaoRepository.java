package com.example.NovoTesteCrud.repository;

import com.example.NovoTesteCrud.domain.avaliacao.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByAcademiaId(Long academiaId);
    List<Avaliacao> findByPersonalId(Long personalId);
    List<Avaliacao> findByUserId(Long userId);
}
