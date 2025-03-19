//package com.example.NovoTesteCrud.service;
//
//import com.example.NovoTesteCrud.domain.user.RequestUserAcad;
//import com.example.NovoTesteCrud.domain.user.UserAcad;
//import com.example.NovoTesteCrud.domain.user.UserAcadRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserAcadService {
//
//    @Autowired
//    private UserAcadRepository repository;
//
//    public List<UserAcad> getAllUserAcad() {
//        return repository.findAll();
//    }
//
//    @Transactional
//    public void registerUserAcad(RequestUserAcad data) {
//        UserAcad newUserAcad = new UserAcad(data.name(), data.email(), data.senha(), data.telefone());
//        repository.save(newUserAcad);
//
//    }
//
//    @Transactional
//    public UserAcad updateUserAcad(Long id, RequestUserAcad data) {
//        UserAcad userAcad = repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));
//
//        userAcad.atualizarDados(data);
//
//        return repository.save(userAcad);
//    }
//
//
//    @Transactional
//    public void deleteUserAcad(Long id) {
//        Optional<UserAcad> optionalUserAcad = repository.findById(id);
//        if (optionalUserAcad.isPresent()) {
//            repository.delete(optionalUserAcad.get());
//        } else {
//            throw new EntityNotFoundException("Usuário não encontrado!");
//        }
//    }
//}
