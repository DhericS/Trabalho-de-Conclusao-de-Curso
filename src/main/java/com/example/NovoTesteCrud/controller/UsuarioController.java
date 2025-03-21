package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.userbase.Usuario;
import com.example.NovoTesteCrud.domain.userbase.dto.IRequestUsuario;
import com.example.NovoTesteCrud.domain.userbase.dto.UsuarioResponseDTO;
import com.example.NovoTesteCrud.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/useracad")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodosUserAcad() {
        return ResponseEntity.ok(usuarioService.buscarTodosUserAcad().stream().map(UsuarioResponseDTO::new).toList());
    }

    @GetMapping("/useradmin")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodosUserAdmin() {
        return ResponseEntity.ok(usuarioService.buscarTodosUserAdmin().stream().map(UsuarioResponseDTO::new).toList());
    }

    @GetMapping("/useracadadmin")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodosUserAcadAdmin() {
        return ResponseEntity.ok(usuarioService.buscarTodosUserAcadAdmin().stream().map(UsuarioResponseDTO::new).toList());
    }

    @GetMapping("/personal")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodosPersonal() {
        return ResponseEntity.ok(usuarioService.buscarTodosPersonal().stream().map(UsuarioResponseDTO::new).toList());
    }

    @GetMapping("/buscar-por-email")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorEmail(@RequestParam String email) {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorEmail(email);
        return usuario.map(value -> ResponseEntity.ok(new UsuarioResponseDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Map<String, String>> registrarUsuario(@RequestBody @Valid IRequestUsuario data) {
        usuarioService.registrarUsuario(data);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário cadastrado com sucesso!");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> atualizarUsuario(
            @PathVariable Long id, @RequestBody @Valid IRequestUsuario data) {
        usuarioService.atualizarUsuario(id, data);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário atualizado com sucesso!");

        return ResponseEntity.ok(response);
    }

    // Exemplo: DELETE http://localhost:8080/usuarios/1?userType=useracad
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarUsuario(@PathVariable Long id, @RequestParam("userType") String userType) {

        usuarioService.deletarUsuario(id, userType);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuário deletado com sucesso!");

        return ResponseEntity.ok(response);
    }
}
