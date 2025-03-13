package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.useracadadmin.UserAcadAdmin;
import com.example.NovoTesteCrud.domain.useracadadmin.RequestUserAcadAdmin;
import com.example.NovoTesteCrud.service.UserAcadAdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/useracadadmin")
public class UserAcadAdminController {

    @Autowired
    private UserAcadAdminService userAcadAdminService;

    @GetMapping
    public ResponseEntity<List<UserAcadAdmin>> getAllUserAcadAdmins() {
        return ResponseEntity.ok(userAcadAdminService.getAllUserAcadAdmins());
    }

    @PostMapping
    public ResponseEntity<Void> registerUserAcadAdmin(@RequestBody @Valid RequestUserAcadAdmin data) {
        userAcadAdminService.registerUserAcadAdmin(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserAcadAdmin> updateUserAcadAdmin(@RequestBody @Valid RequestUserAcadAdmin data, @RequestParam Long id) {
        return ResponseEntity.ok(userAcadAdminService.updateUserAcadAdmin(data, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAcadAdmin(@PathVariable Long id) {
        userAcadAdminService.deleteUserAcadAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
