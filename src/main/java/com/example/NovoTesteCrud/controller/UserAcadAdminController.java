package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.useracadadmin.RequestUserAcadAdmin;
import com.example.NovoTesteCrud.dto.UserAcadAdminDTO;
import com.example.NovoTesteCrud.service.UserAcadAdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/useracadadmin")
public class UserAcadAdminController {

    @Autowired
    private UserAcadAdminService userAcadAdminService;

    @GetMapping
    public ResponseEntity<List<UserAcadAdminDTO>> getAllUserAcadAdmins() {
        List<UserAcadAdminDTO> users = userAcadAdminService.getAllUserAcadAdmins().stream()
                .map(UserAcadAdminDTO::new)
                .toList();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerUserAcadAdmin(@RequestBody @Valid RequestUserAcadAdmin data) {
        userAcadAdminService.registerUserAcadAdmin(data);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário Administrador da Academia cadastrado com sucesso!");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateUserAcadAdmin(@PathVariable Long id, @RequestBody @Valid RequestUserAcadAdmin data) {
        userAcadAdminService.updateUserAcadAdmin(id, data);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário Administrador da Academia atualizado com sucesso!");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUserAcadAdmin(@PathVariable Long id) {
        userAcadAdminService.deleteUserAcadAdmin(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário Administrador da Academia deletado com sucesso!");
        return ResponseEntity.ok(response);
    }
}
