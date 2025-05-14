package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.userbase.Usuario;
import com.example.NovoTesteCrud.domain.userbase.dto.IRequestUsuario;
import com.example.NovoTesteCrud.domain.userbase.dto.UsuarioResponseDTO;
import com.example.NovoTesteCrud.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('USERADMIN')")
    @GetMapping("/todos")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodosUsuarios() {
        return ResponseEntity.ok(usuarioService.buscarTodosUsuarios());
    }


    @PreAuthorize("hasRole('USERADMIN')")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> buscarUsuariosPorTipo(@RequestParam("tipoUsuario") String tipoUsuario, @RequestHeader("Authorization") String token) {
        return switch (tipoUsuario.toLowerCase()) {
            case "useracad" -> ResponseEntity.ok(usuarioService.buscarTodosUserAcad().stream().map(UsuarioResponseDTO::new).toList());
            case "useradmin" -> ResponseEntity.ok(usuarioService.buscarTodosUserAdmin().stream().map(UsuarioResponseDTO::new).toList());
            case "useracadadmin" -> ResponseEntity.ok(usuarioService.buscarTodosUserAcadAdmin().stream().map(UsuarioResponseDTO::new).toList());
            case "personal" -> ResponseEntity.ok(usuarioService.buscarTodosPersonal().stream().map(UsuarioResponseDTO::new).toList());
            default -> ResponseEntity.badRequest().build();
        };
    }

    @PreAuthorize("hasAnyRole('USERADMIN', 'USERACAD')")
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
