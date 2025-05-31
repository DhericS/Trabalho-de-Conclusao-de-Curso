package com.example.NovoTesteCrud.repository;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.treino.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TreinoRepository extends JpaRepository<Treino, Long>, JpaSpecificationExecutor<Treino> {

    List<Treino> findByPersonalId(Long personalId);

}
