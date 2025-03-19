package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.personal.Personal;
import com.example.NovoTesteCrud.domain.personal.RequestPersonal;
import com.example.NovoTesteCrud.domain.user.UserAcad;
import com.example.NovoTesteCrud.domain.useracadadmin.RequestUserAcadAdmin;
import com.example.NovoTesteCrud.domain.useracadadmin.UserAcadAdmin;
import com.example.NovoTesteCrud.domain.useradmin.UserAdmin;
import com.example.NovoTesteCrud.domain.userbase.IRequestUsuario;
import com.example.NovoTesteCrud.domain.userbase.RequestUsuario;
import com.example.NovoTesteCrud.domain.userbase.Usuario;
import com.example.NovoTesteCrud.dto.UsuarioDTO;
import com.example.NovoTesteCrud.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/useracad")
    public ResponseEntity<List<UsuarioDTO>> getAllUserAcad() {
        List<UsuarioDTO> users = usuarioService.getAllUserAcad().stream().map(UsuarioDTO::new).toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/useradmin")
    public ResponseEntity<List<UsuarioDTO>> getAllUserAdmin() {
        List<UsuarioDTO> users = usuarioService.getAllUserAdmin().stream().map(UsuarioDTO::new).toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/useracadadmin")
    public ResponseEntity<List<UsuarioDTO>> getAllUserAcadAdmin() {
        List<UsuarioDTO> users = usuarioService.getAllUserAcadAdmin().stream().map(UsuarioDTO::new).toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/personal")
    public ResponseEntity<List<UsuarioDTO>> getAllPersonal() {
        List<UsuarioDTO> users = usuarioService.getAllPersonal().stream().map(UsuarioDTO::new).toList();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> registerUsuario(@RequestBody @Valid IRequestUsuario data) {
        Usuario usuario;

        switch (data.tipoUsuario().toLowerCase()) {
            case "useracad" -> usuario = new UserAcad(data.name(), data.email(), data.senha(), data.telefone());
            case "useradmin" -> usuario = new UserAdmin(data.name(), data.email(), data.senha(), data.telefone());
            case "useracadadmin" -> {
                RequestUserAcadAdmin adminData = (RequestUserAcadAdmin) data;
                usuario = new UserAcadAdmin(
                        adminData.name(),
                        adminData.email(),
                        adminData.senha(),
                        adminData.telefone(),
                        adminData.cnpj(),
                        new Academia(adminData.academiaId())
                );
            }
            case "personal" -> {
                RequestPersonal personalData = (RequestPersonal) data;
                usuario = new Personal(
                        personalData.name(),
                        personalData.email(),
                        personalData.senha(),
                        personalData.telefone(),
                        personalData.cref()
                );
            }
            default -> throw new IllegalArgumentException("Tipo de usuário inválido: " + data.tipoUsuario());
        }

        usuarioService.registerUsuario(usuario);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário cadastrado com sucesso!");
        return ResponseEntity.ok(response);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateUsuario(
            @PathVariable Long id, @RequestBody @Valid IRequestUsuario data) {
        usuarioService.updateUsuario(id, data);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário atualizado com sucesso!");

        return ResponseEntity.ok(response);
    }

    //http://localhost:8080/usuarios/1?userType=useracad
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUsuario(
            @PathVariable Long id,
            @RequestParam("userType") String userType) {

        // Converter a string userType para a classe correspondente
        Class<? extends Usuario> userClass = switch (userType.toLowerCase()) {
            case "useracad" -> UserAcad.class;
            case "useradmin" -> UserAdmin.class;
            case "useracadadmin" -> UserAcadAdmin.class;
            case "personal" -> Personal.class;
            default -> throw new IllegalArgumentException("Tipo de usuário inválido: " + userType);
        };

        usuarioService.deleteUsuario(id, userClass);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário deletado com sucesso!");

        return ResponseEntity.ok(response);
    }

}
