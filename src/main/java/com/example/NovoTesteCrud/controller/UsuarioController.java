package com.example.NovoTesteCrud.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.NovoTesteCrud.domain.acad.dto.AcademiaRequestDTO;
import com.example.NovoTesteCrud.domain.acad.dto.AcademiaResponseDTO;
import com.example.NovoTesteCrud.domain.personal.Personal;
import com.example.NovoTesteCrud.domain.userbase.Usuario;
import com.example.NovoTesteCrud.domain.userbase.dto.IRequestUsuario;
import com.example.NovoTesteCrud.domain.userbase.dto.UsuarioResponseDTO;
import com.example.NovoTesteCrud.config.security.JwtUtil;
import com.example.NovoTesteCrud.repository.PersonalRepository;
import com.example.NovoTesteCrud.repository.UserAcadAdminRepository;
import com.example.NovoTesteCrud.repository.UserAcadRepository;
import com.example.NovoTesteCrud.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private UserAcadRepository userAcadRepository;

    @Autowired
    private UserAcadAdminRepository userAcadAdminRepository;

    @Autowired
    private PersonalRepository personalRepository;

    @PreAuthorize("hasRole('USERADMIN')")
    @GetMapping("/todos")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodosUsuarios() {
        return ResponseEntity.ok(usuarioService.buscarTodosUsuarios());
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> buscarUsuariosPorTipo(@RequestParam("tipoUsuario") String tipoUsuario) {
        switch (tipoUsuario.toLowerCase()) {
            case "useracad":
                return ResponseEntity.ok(usuarioService.buscarTodosUserAcadDTO());
            case "useradmin":
                return ResponseEntity.ok(usuarioService.buscarTodosUserAdminDTO());
            case "useracadadmin":
                return ResponseEntity.ok(usuarioService.buscarTodosUserAcadAdminDTO());
            case "personal":
                return ResponseEntity.ok(usuarioService.buscarTodosPersonalDTO());
            default:
                return ResponseEntity.badRequest().build();
        }
    }


    @PreAuthorize("hasAnyRole('USERADMIN', 'USERACAD')")
    @GetMapping("/buscar-por-email")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorEmail(@RequestParam String email) {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorEmail(email);
        return usuario.map(value -> ResponseEntity.ok(new UsuarioResponseDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioLogado(
            @RequestHeader("Authorization") String token) {

        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));

        return usuarioService.buscarTodosUsuarios().stream()
                .filter(dto -> dto.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<Personal> personal = personalRepository.findById(id);
        if (personal.isPresent()) {
            return ResponseEntity.ok(new UsuarioResponseDTO(personal.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
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

    @PutMapping("/atualizar")
    public ResponseEntity<Map<String, String>> atualizarUsuarioPorEmail(@RequestBody @Valid IRequestUsuario data) {
        usuarioService.atualizarUsuarioPorEmail(data);

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

    @PostMapping("/{id}/academia")
    @PreAuthorize("hasRole('USERACADADMIN')")
    public ResponseEntity<AcademiaResponseDTO> associarAcademia(
            @PathVariable Long id,
            @RequestParam("tipoUsuario") String tipoUsuario,
            @RequestBody @Valid AcademiaRequestDTO dto
    ) {
        if (!tipoUsuario.equalsIgnoreCase("useracadadmin")) {
            return ResponseEntity.badRequest().build();
        }

        AcademiaResponseDTO response = usuarioService.associarAcademiaAoUserAcadAdmin(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/academia")
    @PreAuthorize("hasRole('USERACADADMIN')")
    public ResponseEntity<AcademiaResponseDTO> buscarAcademiaDoUsuario(@PathVariable Long id) {
        var user = userAcadAdminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserAcadAdmin não encontrado"));

        if (user.getAcademia() == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(new AcademiaResponseDTO(user.getAcademia()));
    }

    @PutMapping("/{id}/academia")
    @PreAuthorize("hasRole('USERACADADMIN')")
    public ResponseEntity<AcademiaResponseDTO> editarAcademiaDoUsuario(
            @PathVariable Long id,
            @RequestParam("tipoUsuario") String tipoUsuario,
            @RequestBody @Valid AcademiaRequestDTO dto
    ) {
        if (!tipoUsuario.equalsIgnoreCase("useracadadmin")) {
            return ResponseEntity.badRequest().build();
        }

        AcademiaResponseDTO response = usuarioService.editarAcademiaDoUserAcadAdmin(id, dto);
        return ResponseEntity.ok(response);
    }



    @PostMapping("/{id}/upload-imagem")
    public ResponseEntity<?> uploadImagem(
            @PathVariable Long id,
            @RequestParam("tipoUsuario") String tipoUsuario,
            @RequestParam("file") MultipartFile file) throws IOException {

        // Definir pasta com base no tipo de usuário
        String folderPath = "";
        switch (tipoUsuario.toLowerCase()) {
            case "useracad":
                folderPath = "useracads/";
                break;
            case "personal":
                folderPath = "personals/";
                break;
            default:
                return ResponseEntity.badRequest().body("Tipo de usuário inválido");
        }

        // 1. Upload para Cloudinary
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("folder", folderPath));
        String imageUrl = (String) uploadResult.get("secure_url");

        switch (tipoUsuario.toLowerCase()) {
            case "useracad":
                var userAcad = userAcadRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("UserAcad não encontrado"));
                userAcad.setImagemUrl(imageUrl);
                userAcadRepository.save(userAcad);
                break;
            case "personal":
                var personal = personalRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Personal não encontrado"));
                personal.setImagemUrl(imageUrl);
                personalRepository.save(personal);
                break;
            default:
                return ResponseEntity.badRequest().body("Tipo de usuário inválido");
        }

        return ResponseEntity.ok(Map.of("url", imageUrl));
    }

    @PostMapping("/upload-imagem-temp")
    public ResponseEntity<?> uploadImagemTemp(
            @RequestParam("file") MultipartFile file,
            @RequestParam("tipoUsuario") String tipoUsuario) throws IOException {

        // Determinar a pasta com base no tipo de usuário
        String folderPath = "";

        switch (tipoUsuario.toLowerCase()) {
            case "useracad":
                folderPath = "useracads/";
                break;
            case "personal":
                folderPath = "personals/";
                break;
            default:
                return ResponseEntity.badRequest().body("Tipo de usuário inválido");
        }

        // Upload da imagem para a pasta do Cloudinary
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("folder", folderPath));

        String imageUrl = (String) uploadResult.get("secure_url");

        // Retorna a URL da imagem
        return ResponseEntity.ok(Map.of("url", imageUrl));
    }

}
