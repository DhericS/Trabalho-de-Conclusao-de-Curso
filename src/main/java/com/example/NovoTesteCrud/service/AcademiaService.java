package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.acad.AcademiaRepository;
import com.example.NovoTesteCrud.domain.acad.dto.AcademiaRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AcademiaService {

    @Autowired
    private AcademiaRepository repository;

    public List<Academia> buscarTodasAcademias() {
        return repository.findAll();
    }

    @Transactional
    public Academia registrarAcademia(AcademiaRequestDTO data) {
        Academia newAcademia = new Academia();
        newAcademia.setNome(data.nome());
        newAcademia.setEndereco(data.endereco());
        newAcademia.setTelefone(data.telefone());

        return repository.save(newAcademia);
    }

    @Transactional
    public Academia atualizarAcademia(AcademiaRequestDTO data, Long id) {
        Optional<Academia> optionalAcademia = repository.findById(id);
        if (optionalAcademia.isPresent()) {
            Academia academia = optionalAcademia.get();
            academia.setNome(data.nome());
            academia.setEndereco(data.endereco());
            academia.setTelefone(data.telefone());
            return academia;
        } else {
            throw new EntityNotFoundException("Academia não encontrada!");
        }
    }

    @Transactional
    public void deletarAcademia(Long id) {
        Optional<Academia> optionalAcademia = repository.findById(id);
        if (optionalAcademia.isPresent()) {
            repository.delete(optionalAcademia.get());
        } else {
            throw new EntityNotFoundException("Academia não encontrada!");
        }
    }
}
