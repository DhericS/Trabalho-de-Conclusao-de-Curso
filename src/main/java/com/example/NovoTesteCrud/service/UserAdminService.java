package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.useradmin.RequestUserAdmin;
import com.example.NovoTesteCrud.domain.useradmin.UserAdmin;
import com.example.NovoTesteCrud.domain.useradmin.UserAdminRepository;
import com.example.NovoTesteCrud.dto.UserAdminDTO;
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

    public List<UserAdminDTO> getAllUserAdmin() {
        return repository.findAll().stream()
                .map(UserAdminDTO::new)
                .toList();
    }

    @Transactional
    public UserAdminDTO registerUserAdmin(RequestUserAdmin data) {
        UserAdmin newUserAdmin = new UserAdmin();
        newUserAdmin.setName(data.name());
        newUserAdmin.setEmail(data.email());
        newUserAdmin.setSenha(data.senha());

        repository.save(newUserAdmin);
        return new UserAdminDTO(newUserAdmin);
    }

    @Transactional
    public UserAdminDTO updateUserAdmin(Long id, RequestUserAdmin data) {
        UserAdmin userAdmin = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário Admin não encontrado!"));

        userAdmin.setName(data.name());
        userAdmin.setEmail(data.email());
        userAdmin.setSenha(data.senha());

        return new UserAdminDTO(userAdmin);
    }

    @Transactional
    public void deleteUserAdmin(Long id) {
        UserAdmin userAdmin = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário Admin não encontrado!"));
        repository.delete(userAdmin);
    }
}
