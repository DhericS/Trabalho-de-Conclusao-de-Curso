package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.user.RequestUserAcad;
import com.example.NovoTesteCrud.domain.user.UserAcad;
import com.example.NovoTesteCrud.domain.user.UserAcadRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserAcadService {

    @Autowired
    private UserAcadRepository repository;

    public List<UserAcad> getAllUserAcad() {
        return repository.findAll();
    }

    public void registerUserAcad(RequestUserAcad data) {
        UserAcad newUserAcad = new UserAcad(data);
        repository.save(newUserAcad);
    }

    @Transactional
    public UserAcad updateUserAcad(RequestUserAcad data) {
        Optional<UserAcad> optionalUserAcad = repository.findById(data.id());
        if (optionalUserAcad.isPresent()) {
            UserAcad userAcad = optionalUserAcad.get();
            userAcad.setName(data.name());
            userAcad.setEmail(data.email());
            userAcad.setSenha(data.senha());
            userAcad.setTelefone(data.telefone());
            return userAcad;
        } else {
            throw new EntityNotFoundException("Usuário não encontrado!");
        }
    }

    @Transactional
    public void deleteUserAcad(Long id) {
        Optional<UserAcad> optionalUserAcad = repository.findById(id);
        if (optionalUserAcad.isPresent()) {
            repository.delete(optionalUserAcad.get());
        } else {
            throw new EntityNotFoundException("Usuário não encontrado!");
        }
    }
}
