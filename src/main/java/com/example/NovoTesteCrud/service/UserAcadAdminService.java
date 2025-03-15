package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.acad.AcademiaRepository;
import com.example.NovoTesteCrud.domain.useracadadmin.UserAcadAdmin;
import com.example.NovoTesteCrud.domain.useracadadmin.UserAcadAdminRepository;
import com.example.NovoTesteCrud.domain.useracadadmin.RequestUserAcadAdmin;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserAcadAdminService {

    @Autowired
    private UserAcadAdminRepository repository;

    @Autowired
    private AcademiaRepository academiaRepository;

    public List<UserAcadAdmin> getAllUserAcadAdmins() {
        return repository.findAll();
    }

    @Transactional
    public void registerUserAcadAdmin(RequestUserAcadAdmin data) {
        Academia academia;

        if (data.academiaId() != null) {
            academia = academiaRepository.findById(data.academiaId())
                    .orElseThrow(() -> new EntityNotFoundException("Academia não encontrada!"));
        } else {
            academia = new Academia();
            academia.setNome(data.academiaNome());
            academia.setEndereco(data.academiaEndereco());
            academia.setTelefone(data.academiaTelefone());
            academiaRepository.save(academia);
        }

        UserAcadAdmin newAdmin = new UserAcadAdmin();
        newAdmin.setName(data.name());
        newAdmin.setEmail(data.email());
        newAdmin.setPassword(data.password());
        newAdmin.setCnpj(data.cnpj());
        newAdmin.setAcademia(academia);
        newAdmin.setTelefone(data.telefone());

        repository.save(newAdmin);
    }

    @Transactional
    public UserAcadAdmin updateUserAcadAdmin(RequestUserAcadAdmin data, Long id) {
        Optional<UserAcadAdmin> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()) {
            UserAcadAdmin user = optionalUser.get();
            user.setName(data.name());
            user.setEmail(data.email());
            user.setPassword(data.password());
            user.setTelefone(data.telefone());
            return user;
        } else {
            throw new EntityNotFoundException("Usuário não encontrado!");
        }
    }

    @Transactional
    public void deleteUserAcadAdmin(Long id) {
        Optional<UserAcadAdmin> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()) {
            repository.delete(optionalUser.get());
        } else {
            throw new EntityNotFoundException("Usuário não encontrado!");
        }
    }
}
