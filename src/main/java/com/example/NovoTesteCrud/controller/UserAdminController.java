package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.useradmin.RequestUserAdmin;
import com.example.NovoTesteCrud.domain.useradmin.UserAdmin;
import com.example.NovoTesteCrud.service.UserAdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/useradmin")
public class UserAdminController {

    @Autowired
    private UserAdminService userAdminService;

    @GetMapping
    public ResponseEntity<List<UserAdmin>> getAllUserAdmin() {
        return ResponseEntity.ok(userAdminService.getAllUserAdmin());
    }

    @PostMapping
    public ResponseEntity<Void> registerUserAdmin(@RequestBody @Valid RequestUserAdmin data) {
        userAdminService.registerUserAdmin(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserAdmin> updateUserAdmin(@RequestBody @Valid RequestUserAdmin data) {
        return ResponseEntity.ok(userAdminService.updateUserAdmin(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAdmin(@PathVariable Long id) {
        userAdminService.deleteUserAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
