package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.avaliacao.Avaliacao;
import com.example.NovoTesteCrud.domain.avaliacao.dto.AvaliacaoRequestDTO;
import com.example.NovoTesteCrud.domain.avaliacao.dto.AvaliacaoResponseDTO;
import com.example.NovoTesteCrud.domain.avaliacao.enums.TipoEntidade;
import com.example.NovoTesteCrud.domain.user.UserAcad;
import com.example.NovoTesteCrud.repository.AvaliacaoRepository;
import com.example.NovoTesteCrud.repository.UserAcadAdminRepository;
import com.example.NovoTesteCrud.repository.UserAcadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository repository;
    @Autowired
    private UserAcadRepository userAcadRepository;

    public AvaliacaoService(AvaliacaoRepository repository) {
        this.repository = repository;
    }

    // Metodod para Listar todas as avaliações
    public List<AvaliacaoResponseDTO> listarPorUsuarioETipoEntidade(Long usuarioId, TipoEntidade tipoEntidade) {
        return repository.findByUsuarioId_IdAndTipoEntidade(usuarioId, tipoEntidade).stream()
                .map(AvaliacaoResponseDTO::new)
                .collect(Collectors.toList());
    }


    // Metodo para listar avaliações por tipo de entidade e ID da entidade
    public List<AvaliacaoResponseDTO> listarPorEntidade(TipoEntidade tipo, Long entidadeId) {
        return repository.findByTipoEntidadeAndEntidadeId(tipo, entidadeId).stream()
                .map(AvaliacaoResponseDTO::new)
                .collect(Collectors.toList());
    }

    // Metodo para registrar novas avaliações
    public AvaliacaoResponseDTO criar(AvaliacaoRequestDTO dto) {
        UserAcad user = userAcadRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Avaliacao avaliacao = Avaliacao.builder()
                .nota(dto.nota())
                .comentario(dto.comentario())
                .usuarioId(user)
                .tipoEntidade(dto.tipoEntidade())
                .entidadeId(dto.entidadeId())
                .build();

        Avaliacao salvo = repository.save(avaliacao);
        return new AvaliacaoResponseDTO(salvo);
    }

    // Metodo para atualizar avaliações
    public AvaliacaoResponseDTO atualizar(Long id, AvaliacaoRequestDTO dto) {
        Avaliacao aval = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));

        UserAcad user = userAcadRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        aval.setNota(dto.nota());
        aval.setComentario(dto.comentario());
        aval.setUsuarioId(user);

        Avaliacao atualizado = repository.save(aval);
        return new AvaliacaoResponseDTO(atualizado);
    }


    // Metodo para deletar avaliações
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
