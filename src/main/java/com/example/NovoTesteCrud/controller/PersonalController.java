package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.dto.PersonalDTO;
import com.example.NovoTesteCrud.domain.personal.RequestPersonal;
import com.example.NovoTesteCrud.service.PersonalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/personais")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @GetMapping
    public ResponseEntity<List<PersonalDTO>> getAllPersonais() {
        return ResponseEntity.ok(personalService.getAllPersonais());
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> registerPersonal(@RequestBody @Valid RequestPersonal data) {
        PersonalDTO personalDTO = personalService.registerPersonal(data);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Personal cadastrado com sucesso!");
        response.put("personal", personalDTO);

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatePersonal(@PathVariable Long id, @RequestBody @Valid RequestPersonal data) {
        PersonalDTO updatedPersonal = personalService.updatePersonal(data, id);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Personal atualizado com sucesso!");
        response.put("personal", updatedPersonal);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePersonal(@PathVariable Long id) {
        personalService.deletePersonal(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Personal deletado com sucesso!");

        return ResponseEntity.ok(response);
    }

}
