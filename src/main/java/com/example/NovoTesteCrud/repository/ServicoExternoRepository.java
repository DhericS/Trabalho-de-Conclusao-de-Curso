package com.example.NovoTesteCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.NovoTesteCrud.domain.servicoextra.ServicoExterno;
import java.util.List;


public interface ServicoExternoRepository extends JpaRepository<ServicoExterno, Long>{
    List<ServicoExterno> findByAcademiaId(Long academiaId);
}
