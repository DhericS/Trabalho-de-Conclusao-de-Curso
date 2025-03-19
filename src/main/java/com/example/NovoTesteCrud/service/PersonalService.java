//package com.example.NovoTesteCrud.service;
//
//import com.example.NovoTesteCrud.domain.personal.Personal;
//import com.example.NovoTesteCrud.domain.personal.PersonalRepository;
//import com.example.NovoTesteCrud.domain.personal.RequestPersonal;
//import com.example.NovoTesteCrud.dto.PersonalDTO;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//public class PersonalService {
//
//    @Autowired
//    private PersonalRepository repository;
//
//    public List<PersonalDTO> getAllPersonais() {
//        return repository.findAll().stream()
//                .map(PersonalDTO::new)
//                .toList();
//    }
//
//    @Transactional
//    public PersonalDTO registerPersonal(RequestPersonal data) {
//        Personal newPersonal = new Personal(
//                data.name(),
//                data.email(),
//                data.password(),
//                data.telefone(),
//                data.cref()
//        );
//
//        repository.save(newPersonal);
//        return new PersonalDTO(newPersonal);
//    }
//
//    @Transactional
//    public PersonalDTO updatePersonal(RequestPersonal data, Long id) {
//        Personal personal = repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Personal não encontrado!"));
//
//        personal.atualizarDados(data);
//        repository.save(personal);
//
//        return new PersonalDTO(personal);
//    }
//
//    @Transactional
//    public void deletePersonal(Long id) {
//        Personal personal = repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Personal não encontrado!"));
//
//        repository.delete(personal);
//    }
//}
