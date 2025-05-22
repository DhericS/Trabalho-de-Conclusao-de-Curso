package com.example.NovoTesteCrud.repository;

import com.example.NovoTesteCrud.domain.avaliacao.Avaliacao;
import com.example.NovoTesteCrud.domain.avaliacao.enums.TipoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByTipoEntidadeAndEntidadeId(TipoEntidade tipoEntidade, Long entidadeId);

    boolean existsByUsuarioIdAndTipoEntidadeAndEntidadeId(Long usuarioId, TipoEntidade tipoEntidade, Long entidadeId);
}
