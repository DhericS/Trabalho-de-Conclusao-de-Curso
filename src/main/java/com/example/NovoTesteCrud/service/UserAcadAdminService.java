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
    //Teste commit
    @Transactional
    public void registerUserAcadAdmin(RequestUserAcadAdmin data) {
        Academia academia = academiaRepository.findById(data.academiaId())
                .orElseThrow(() -> new EntityNotFoundException("Academia não encontrada!"));

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
    public void updateUserAcadAdmin(Long id, RequestUserAcadAdmin data) {
        UserAcadAdmin user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário Administrador não encontrado!"));

        Academia academia = academiaRepository.findById(data.academiaId())
                .orElseThrow(() -> new EntityNotFoundException("Academia não encontrada!"));

        user.setName(data.name());
        user.setEmail(data.email());
        user.setPassword(data.password());
        user.setCnpj(data.cnpj());
        user.setAcademia(academia);
        user.setTelefone(data.telefone());
    }

    @Transactional
    public void deleteUserAcadAdmin(Long id) {
        UserAcadAdmin user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário Administrador não encontrado!"));
        repository.delete(user);
    }
}
