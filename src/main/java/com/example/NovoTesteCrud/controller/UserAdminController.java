package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.useradmin.RequestUserAdmin;
import com.example.NovoTesteCrud.dto.UserAdminDTO;
import com.example.NovoTesteCrud.service.UserAdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/useradmin")
public class UserAdminController {

    @Autowired
    private UserAdminService userAdminService;

    @GetMapping
    public ResponseEntity<List<UserAdminDTO>> getAllUserAdmin() {
        return ResponseEntity.ok(userAdminService.getAllUserAdmin());
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerUserAdmin(@RequestBody @Valid RequestUserAdmin data) {
        userAdminService.registerUserAdmin(data);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário Admin cadastrado com sucesso!");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAdminDTO> updateUserAdmin(@PathVariable Long id, @RequestBody @Valid RequestUserAdmin data) {
        return ResponseEntity.ok(userAdminService.updateUserAdmin(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUserAdmin(@PathVariable Long id) {
        userAdminService.deleteUserAdmin(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário Admin deletado com sucesso!");
        return ResponseEntity.ok(response);
    }
}
