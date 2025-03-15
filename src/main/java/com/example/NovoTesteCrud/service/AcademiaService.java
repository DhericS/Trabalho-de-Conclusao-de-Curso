package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.acad.AcademiaRepository;
import com.example.NovoTesteCrud.domain.acad.RequestAcademia;
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

    public List<Academia> getAllAcademias() {
        return repository.findAll();
    }

    public void registerAcademia(RequestAcademia data) {
        Academia newAcademia = new Academia();
        newAcademia.setNome(data.nome());
        newAcademia.setEndereco(data.endereco());
        newAcademia.setTelefone(data.telefone());
        repository.save(newAcademia);
    }

    @Transactional
    public Academia updateAcademia(RequestAcademia data, Long id) {
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
    public void deleteAcademia(Long id) {
        Optional<Academia> optionalAcademia = repository.findById(id);
        if (optionalAcademia.isPresent()) {
            repository.delete(optionalAcademia.get());
        } else {
            throw new EntityNotFoundException("Academia não encontrada!");
        }
    }
}
