package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.atvd.Atividade;
import com.example.NovoTesteCrud.repository.AtividadeRepository;
import com.example.NovoTesteCrud.domain.atvd.dto.AtividadeRequestDTO;
import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.repository.AcademiaRepository;
import com.example.NovoTesteCrud.repository.UserAcadAdminRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private AcademiaRepository academiaRepository;

    @Autowired
    private UserAcadAdminRepository userAcadAdminRepository;

    public List<Atividade> buscarTodasAtividades() {
        return atividadeRepository.findAll();
    }

    public List<Atividade> buscarTodasAtividadesPorAcademia(Long academiaId) {
        Academia academia = academiaRepository.findById(academiaId)
                .orElseThrow(() -> new EntityNotFoundException("Academia não encontrada!"));

        return atividadeRepository.findByAcademia(academia);
    }

    public Atividade buscarPorId(Long id) {
        return atividadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Atividade não encontrada com ID: " + id));
    }

    @Transactional
    public Atividade registrarAtividade(AtividadeRequestDTO data) {
        Academia academia = academiaRepository.findById(data.academiaId())
                .orElseThrow(() -> new EntityNotFoundException("Academia não encontrada!"));

        Atividade newAtividade = new Atividade();
        newAtividade.setNome(data.nome());
        newAtividade.setHorario(data.horario());
        newAtividade.setDiaSemana(data.diaSemana());
        newAtividade.setAcademia(academia);

        return atividadeRepository.save(newAtividade);
    }

    @Transactional
    public Atividade atualizarAtividade(AtividadeRequestDTO data, Long id) {
        Atividade atividade = atividadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Atividade não encontrada!"));

        atividade.setNome(data.nome());

        return atividadeRepository.save(atividade);
    }


    @Transactional
    public void deletarAtividade(Long id) {
        Optional<Atividade> optionalAtividade = atividadeRepository.findById(id);
        if (optionalAtividade.isPresent()) {
            atividadeRepository.delete(optionalAtividade.get());
        } else {
            throw new EntityNotFoundException("Atividade não encontrada!");
        }
    }

    public boolean usuarioPodeEditar(Long atividadeId) {
        var authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = authUser.getUsername();

        return atividadeRepository.findById(atividadeId)
                .map(atividade -> userAcadAdminRepository.findByUsuario_Email(email)
                        .map(user -> user.getAcademia() != null && user.getAcademia().getId().equals(atividade.getAcademia().getId()))
                        .orElse(false))
                .orElse(false);
    }

}
