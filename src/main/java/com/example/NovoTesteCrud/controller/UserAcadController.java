package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.user.RequestUserAcad;
import com.example.NovoTesteCrud.domain.user.UserAcad;
import com.example.NovoTesteCrud.service.UserAcadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/useracad")
public class UserAcadController {

    @Autowired
    private UserAcadService userAcadService;

    @GetMapping
    public ResponseEntity<List<UserAcad>> getAllUserAcad() {
        return ResponseEntity.ok(userAcadService.getAllUserAcad());
    }

    @PostMapping
    public ResponseEntity<Void> registerUserAcad(@RequestBody @Valid RequestUserAcad data) {
        userAcadService.registerUserAcad(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserAcad> updateUserAcad(@RequestBody @Valid RequestUserAcad data) {
        return ResponseEntity.ok(userAcadService.updateUserAcad(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAcad(@PathVariable Long id) {
        userAcadService.deleteUserAcad(id);
        return ResponseEntity.noContent().build();
    }
}
