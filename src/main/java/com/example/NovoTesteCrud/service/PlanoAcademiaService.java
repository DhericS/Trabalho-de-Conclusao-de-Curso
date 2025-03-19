package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.acad.AcademiaRepository;
import com.example.NovoTesteCrud.domain.planoacad.PlanoAcademia;
import com.example.NovoTesteCrud.domain.planoacad.PlanoAcademiaRepository;
import com.example.NovoTesteCrud.domain.planoacad.RequestPlanoAcademia;
import com.example.NovoTesteCrud.dto.PlanoAcademiaDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlanoAcademiaService {

    @Autowired
    private PlanoAcademiaRepository repository;

    @Autowired
    private AcademiaRepository academiaRepository;

    public List<PlanoAcademia> getAllPlanos() {
        return repository.findAll();
    }

    public List<PlanoAcademia> getPlanosByAcademia(Long academiaId) {
        return repository.findByAcademiaId(academiaId);
    }

    @Transactional
    public PlanoAcademiaDTO registerPlano(RequestPlanoAcademia data) {
        Academia academia = academiaRepository.findById(data.academiaId())
                .orElseThrow(() -> new EntityNotFoundException("Academia não encontrada!"));

        PlanoAcademia plano = new PlanoAcademia();
        plano.setNome(data.nome());
        plano.setDescricao(data.descricao());
        plano.setPreco(data.preco());
        plano.setAcademia(academia);

        repository.save(plano);
        return new PlanoAcademiaDTO(plano);
    }


    @Transactional
    public PlanoAcademiaDTO updatePlano(Long id, RequestPlanoAcademia data) {
        PlanoAcademia plano = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado!"));

        plano.setNome(data.nome());
        plano.setDescricao(data.descricao());
        plano.setPreco(data.preco());

        repository.save(plano);
        return new PlanoAcademiaDTO(plano);
    }

    @Transactional
    public void deletePlano(Long id) {
        PlanoAcademia plano = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado!"));
        repository.delete(plano);
    }
}
