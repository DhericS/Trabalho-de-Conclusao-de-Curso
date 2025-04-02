package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.repository.AcademiaRepository;
import com.example.NovoTesteCrud.domain.avaliacao.Avaliacao;
import com.example.NovoTesteCrud.repository.AvaliacaoRepository;
import com.example.NovoTesteCrud.domain.avaliacao.dto.AvaliacaoRequestDTO;
import com.example.NovoTesteCrud.domain.personal.Personal;
import com.example.NovoTesteCrud.repository.PersonalRepository;
import com.example.NovoTesteCrud.domain.user.UserAcad;
import com.example.NovoTesteCrud.repository.UserAcadRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AcademiaRepository academiaRepository;

    @Autowired
    private PersonalRepository personalRepository;

    @Autowired
    private UserAcadRepository userAcadRepository;

    public List<Avaliacao> buscarTodasAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    public List<Avaliacao> buscarAvaliacaoPorAcademia(Long academiaId) {
        List<Avaliacao> avaliacao = avaliacaoRepository.findByAcademiaId(academiaId);
        if (avaliacao.isEmpty()) {
            throw new EntityNotFoundException("Nenhum feedback encontrado para esta academia.");
        }
        return avaliacao;
    }

    public List<Avaliacao> buscarAvaliacaoPorPersonal(Long personalId) {
        List<Avaliacao> avaliacao = avaliacaoRepository.findByPersonalId(personalId);
        if (avaliacao.isEmpty()) {
            throw new EntityNotFoundException("Nenhum feedback encontrado para este personal.");
        }
        return avaliacao;
    }

    public Avaliacao buscarAvaliacaoPorId(Long id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliacao não encontrado!"));
    }

    public List<Avaliacao> buscarAvaliacaoPorUsuario(Long userId) {
        return avaliacaoRepository.findByUserId(userId);
    }

    @Transactional
    public Avaliacao registrarAvaliacao(AvaliacaoRequestDTO data) {
        UserAcad user = userAcadRepository.findById(data.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + data.userId() + " não encontrado!"));

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setDescricao(data.descricao());
        avaliacao.setEstrelas(data.estrelas());
        avaliacao.setUser(user);

        if (data.academiaId() != null) {
            Academia academia = academiaRepository.findById(data.academiaId())
                    .orElseThrow(() -> new EntityNotFoundException("Academia com ID " + data.academiaId() + " não encontrada!"));
            avaliacao.setAcademia(academia);
        }

        if (data.personalId() != null) {
            Personal personal = personalRepository.findById(data.personalId())
                    .orElseThrow(() -> new EntityNotFoundException("Personal com ID " + data.personalId() + " não encontrado!"));
            avaliacao.setPersonal(personal);
        }

        return avaliacaoRepository.save(avaliacao);
    }

    @Transactional
    public Avaliacao atualizarAvaliacao(Long feedbackId, AvaliacaoRequestDTO data) {
        Avaliacao avaliacao = avaliacaoRepository.findById(feedbackId)
                .orElseThrow(() -> new EntityNotFoundException("Avaliacao não encontrado!"));

        avaliacao.setDescricao(data.descricao());
        avaliacao.setEstrelas(data.estrelas());

        return avaliacaoRepository.save(avaliacao);
    }


    @Transactional
    public void deletarAvaliacao(Long feedbackId, Long userId) {
        Avaliacao avaliacao = avaliacaoRepository.findById(feedbackId)
                .orElseThrow(() -> new EntityNotFoundException("Avaliacao não encontrado!"));

        if (!avaliacao.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Você não tem permissão para excluir este avaliacao.");
        }

        avaliacaoRepository.delete(avaliacao);
    }
}
