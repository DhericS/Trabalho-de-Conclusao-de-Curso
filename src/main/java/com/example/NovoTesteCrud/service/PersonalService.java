package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.personal.Personal;
import com.example.NovoTesteCrud.domain.personal.PersonalRepository;
import com.example.NovoTesteCrud.domain.personal.RequestPersonal;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalService {

    @Autowired
    private PersonalRepository repository;

    public List<Personal> getAllPersonais() {
        return repository.findAll();
    }

    @Transactional
    public void registerPersonal(RequestPersonal data) {
        Personal newPersonal = new Personal();
        newPersonal.setName(data.name());
        newPersonal.setEmail(data.email());
        newPersonal.setPassword(data.password());
        newPersonal.setTelefone(data.telefone());
        newPersonal.setCref(data.cref());

        repository.save(newPersonal);
    }

    @Transactional
    public Personal updatePersonal(RequestPersonal data, Long id) {
        Optional<Personal> optionalPersonal = repository.findById(id);
        if (optionalPersonal.isPresent()) {
            Personal personal = optionalPersonal.get();
            personal.setName(data.name());
            personal.setEmail(data.email());
            personal.setPassword(data.password());
            personal.setTelefone(data.telefone());
            personal.setCref(data.cref());
            return personal;
        } else {
            throw new EntityNotFoundException("Personal não encontrado!");
        }
    }

    @Transactional
    public void deletePersonal(Long id) {
        Optional<Personal> optionalPersonal = repository.findById(id);
        if (optionalPersonal.isPresent()) {
            repository.delete(optionalPersonal.get());
        } else {
            throw new EntityNotFoundException("Personal não encontrado!");
        }
    }
}
