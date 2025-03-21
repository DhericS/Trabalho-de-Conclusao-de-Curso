package com.example.NovoTesteCrud.domain.atvd;

import com.example.NovoTesteCrud.domain.acad.Academia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    List<Atividade> buscarPorAcademia(Academia academia);
}
