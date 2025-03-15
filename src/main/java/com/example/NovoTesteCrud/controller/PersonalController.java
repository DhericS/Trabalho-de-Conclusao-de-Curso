package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.personal.Personal;
import com.example.NovoTesteCrud.domain.personal.RequestPersonal;
import com.example.NovoTesteCrud.service.PersonalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personais")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @GetMapping
    public ResponseEntity<List<Personal>> getAllPersonais() {
        return ResponseEntity.ok(personalService.getAllPersonais());
    }

    @PostMapping
    public ResponseEntity<Void> registerPersonal(@RequestBody @Valid RequestPersonal data) {
        personalService.registerPersonal(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Personal> updatePersonal(@RequestBody @Valid RequestPersonal data, @RequestParam Long id) {
        return ResponseEntity.ok(personalService.updatePersonal(data, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonal(@PathVariable Long id) {
        personalService.deletePersonal(id);
        return ResponseEntity.noContent().build();
    }
}
