package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.repository.AcademiaRepository;
import com.example.NovoTesteCrud.domain.planoacad.PlanoAcademia;
import com.example.NovoTesteCrud.repository.PlanoAcademiaRepository;
import com.example.NovoTesteCrud.domain.planoacad.dto.PlanoAcademiaRequestDTO;
import com.example.NovoTesteCrud.domain.planoacad.dto.PlanoAcademiaResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlanoAcademiaService {

    @Autowired
    private PlanoAcademiaRepository repository;

    @Autowired
    private AcademiaRepository academiaRepository;

    // Metodo para buscar todos os planos de academia
    public List<PlanoAcademia> buscarTodosPlanos() {
        return repository.findAll();
    }

    // Metodo para buscar planos de academia por ID da academia
    public List<PlanoAcademia> buscarPlanosPorAcademia(Long academiaId) {
        return repository.findByAcademiaId(academiaId);
    }

    // Metodo para buscar plano de academia por ID
    public Optional<PlanoAcademia> buscarPorId(Long id) {
        return repository.findById(id);
    }


    // Metodo para registrar um novo plano de academia
    @Transactional
    public PlanoAcademiaResponseDTO registrarPlano(PlanoAcademiaRequestDTO data) {
        Academia academia = academiaRepository.findById(data.academiaId())
                .orElseThrow(() -> new EntityNotFoundException("Academia não encontrada!"));

        PlanoAcademia plano = new PlanoAcademia();
        plano.setNome(data.nome());
        plano.setDescricao(data.descricao());
        plano.setPreco(data.preco());
        plano.setAcademia(academia);

        repository.save(plano);
        return new PlanoAcademiaResponseDTO(plano);
    }


    // Metodo para atualizar um plano de academia existente
    @Transactional
    public PlanoAcademiaResponseDTO atualizarPlano(Long id, PlanoAcademiaRequestDTO data) {
        PlanoAcademia plano = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado!"));

        plano.setNome(data.nome());
        plano.setDescricao(data.descricao());
        plano.setPreco(data.preco());

        Academia academia = academiaRepository.findById(data.academiaId())
                .orElseThrow(() -> new EntityNotFoundException("Academia não encontrada"));
        plano.setAcademia(academia);

        repository.save(plano);
        return new PlanoAcademiaResponseDTO(plano);
    }

// Metodo para deletar um plano de academia por ID
    @Transactional
    public void deletarPlano(Long id) {
        PlanoAcademia plano = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado!"));
        repository.delete(plano);
    }
}
