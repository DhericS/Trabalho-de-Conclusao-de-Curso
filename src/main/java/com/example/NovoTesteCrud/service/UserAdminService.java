package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.useradmin.RequestUserAdmin;
import com.example.NovoTesteCrud.domain.useradmin.UserAdmin;
import com.example.NovoTesteCrud.domain.useradmin.UserAdminRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserAdminService {

    @Autowired
    private UserAdminRepository repository;

    public List<UserAdmin> getAllUserAdmin() {
        return repository.findAll();
    }

    public void registerUserAdmin(RequestUserAdmin data) {
        UserAdmin newUserAdmin = new UserAdmin(data);
        repository.save(newUserAdmin);
    }

    @Transactional
    public UserAdmin updateUserAdmin(RequestUserAdmin data) {
        Optional<UserAdmin> optionalUserAdmin = repository.findById(data.id());
        if (optionalUserAdmin.isPresent()) {
            UserAdmin userAdmin = optionalUserAdmin.get();
            userAdmin.setName(data.name());
            userAdmin.setEmail(data.email());
            userAdmin.setSenha(data.senha());
            return userAdmin;
        } else {
            throw new EntityNotFoundException("Usuário Admin não encontrado!");
        }
    }

    @Transactional
    public void deleteUserAdmin(Long id) {
        Optional<UserAdmin> optionalUserAdmin = repository.findById(id);
        if (optionalUserAdmin.isPresent()) {
            repository.delete(optionalUserAdmin.get());
        } else {
            throw new EntityNotFoundException("Usuário Admin não encontrado!");
        }
    }
}
