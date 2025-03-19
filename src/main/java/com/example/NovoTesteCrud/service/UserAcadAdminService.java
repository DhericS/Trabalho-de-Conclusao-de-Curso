//package com.example.NovoTesteCrud.service;
//
//import com.example.NovoTesteCrud.domain.acad.Academia;
//import com.example.NovoTesteCrud.domain.acad.AcademiaRepository;
//import com.example.NovoTesteCrud.domain.useracadadmin.UserAcadAdmin;
//import com.example.NovoTesteCrud.domain.useracadadmin.UserAcadAdminRepository;
//import com.example.NovoTesteCrud.domain.useracadadmin.RequestUserAcadAdmin;
//import com.example.NovoTesteCrud.dto.UserAcadAdminDTO;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserAcadAdminService {
//
//    @Autowired
//    private UserAcadAdminRepository repository;
//
//    @Autowired
//    private AcademiaRepository academiaRepository;
//
//    public List<UserAcadAdmin> getAllUserAcadAdmins() {
//        return repository.findAll();
//    }
//    //Teste commit
//    @Transactional
//    public void registerUserAcadAdmin(RequestUserAcadAdmin data) {
//        Academia academia = academiaRepository.findById(data.academiaId())
//                .orElseThrow(() -> new EntityNotFoundException("Academia não encontrada!"));
//        UserAcadAdmin newAdmin = new UserAcadAdmin(
//                data.name(),
//                data.email(),
//                data.senha(),
//                data.telefone(),
//                data.cnpj(),
//                academia
//        );
//
//        repository.save(newAdmin);
//    }
//
//    @Transactional
//    public UserAcadAdminDTO updateUserAcadAdmin(Long id, RequestUserAcadAdmin data) {
//        UserAcadAdmin user = repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Usuário Administrador não encontrado!"));
//
//        user.atualizarDados(data, academiaRepository); // <- Passando o repositório para buscar a academia
//
//        return new UserAcadAdminDTO(user);
//    }
//
//    @Transactional
//    public void deleteUserAcadAdmin(Long id) {
//        UserAcadAdmin user = repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Usuário Administrador não encontrado!"));
//        repository.delete(user);
//    }
//}
