package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.acad.AcademiaRepository;
import com.example.NovoTesteCrud.domain.agendamento.Agendamento;
import com.example.NovoTesteCrud.domain.agendamento.AgendamentoRepository;
import com.example.NovoTesteCrud.domain.agendamento.RequestAgendamento;
import com.example.NovoTesteCrud.domain.personal.Personal;
import com.example.NovoTesteCrud.domain.personal.PersonalRepository;
import com.example.NovoTesteCrud.domain.user.UserAcad;
import com.example.NovoTesteCrud.domain.user.UserAcadRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;


import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private AcademiaRepository academiaRepository;

    @Autowired
    private PersonalRepository personalRepository;

    @Autowired
    private UserAcadRepository userAcadRepository;

    public List<Agendamento> getAllAgendamentos() {
        return repository.findAll();
    }

    public List<Agendamento> getAgendamentosByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<Agendamento> getAgendamentosByUserAndDate(Long userId, LocalDateTime start, LocalDateTime end) {
        return repository.findByUserIdAndDataHoraBetween(userId, start, end);
    }

    public List<Agendamento> getAgendamentosByPersonal(Long personalId) {
        return repository.findByPersonalIdAndDataHoraBetween(personalId, LocalDateTime.now(), LocalDateTime.now().plusDays(30));
    }

    public List<Agendamento> getAgendamentosByPersonalAndUser(Long personalId, Long userId) {
        return repository.findByPersonalIdAndUserId(personalId, userId);
    }

    public List<Agendamento> getAgendamentosByAcademiaAndPersonal(Long academiaId, Long personalId) {
        return repository.findByAcademiaIdAndPersonalId(academiaId, personalId);
    }

    public List<Agendamento> getAgendamentosByPersonalUserAndDate(Long personalId, Long userId, LocalDateTime start, LocalDateTime end) {
        return repository.findByPersonalIdAndUserIdAndDataHoraBetween(personalId, userId, start, end);
    }

    @Transactional
    public Agendamento registerAgendamento(RequestAgendamento data) {
        Academia academia = academiaRepository.findById(data.academiaId())
                .orElseThrow(() -> new EntityNotFoundException("Academia não encontrada!"));

        Personal personal = personalRepository.findById(data.personalId())
                .orElseThrow(() -> new EntityNotFoundException("Personal não encontrado!"));

        UserAcad user = userAcadRepository.findById(data.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));

        Agendamento agendamento = new Agendamento();
        agendamento.setDataHora(data.dataHora());
        agendamento.setAcademia(academia);
        agendamento.setPersonal(personal);
        agendamento.setUser(user);

        return repository.save(agendamento);
    }


    @Transactional
    public Agendamento updateAgendamento(RequestAgendamento data, Long id) {
        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado!"));

        Academia academia = academiaRepository.findById(data.academiaId())
                .orElseThrow(() -> new EntityNotFoundException("Academia não encontrada!"));

        Personal personal = personalRepository.findById(data.personalId())
                .orElseThrow(() -> new EntityNotFoundException("Personal não encontrado!"));

        UserAcad user = userAcadRepository.findById(data.userId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));

        agendamento.setDataHora(data.dataHora());
        agendamento.setAcademia(academia);
        agendamento.setPersonal(personal);
        agendamento.setUser(user);

        return repository.save(agendamento);
    }

    @Transactional
    public void deleteAgendamento(Long id) {
        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado!"));
        repository.delete(agendamento);
    }
}
