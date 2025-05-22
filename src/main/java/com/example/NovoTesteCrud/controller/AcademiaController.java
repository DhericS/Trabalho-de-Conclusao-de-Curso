package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.acad.dto.*;
import com.example.NovoTesteCrud.domain.acad.enums.TipoAcad;
import com.example.NovoTesteCrud.domain.acad.enums.Estrutura;
import com.example.NovoTesteCrud.domain.acad.enums.Servicos;
import com.example.NovoTesteCrud.service.AcademiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/academia")
public class AcademiaController {

    @Autowired
    private AcademiaService academiaService;


//    @PreAuthorize("hasAnyRole('USERADMIN', 'USERACADADMIN', 'USERACAD', 'PERSONAL')")
    @GetMapping
    public ResponseEntity<List<AcademiaResponseDTO>> buscarTodasAcademias() {
        List<AcademiaResponseDTO> academias = academiaService.buscarTodasAcademias().stream()
                .map(AcademiaResponseDTO::new)
                .toList();
        return ResponseEntity.ok(academias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademiaResponseDTO> buscarPorId(@PathVariable Long id) {
        var academia = academiaService.buscarPorId(id);
        return ResponseEntity.ok(new AcademiaResponseDTO(academia));
    }


    @GetMapping("/filtro")
    public ResponseEntity<List<AcademiaResponseDTO>> buscarComFiltro(
            @RequestParam(required = false) List<TipoAcad> tipos,
            @RequestParam(required = false) List<Estrutura> estruturas,
            @RequestParam(required = false) List<Servicos> servicos
    ) {
        var filtro = new AcademiaFilterDTO(tipos, estruturas, servicos);
        var lista = academiaService.buscarTodasAcademiasFiltradas(filtro)
                .stream().map(AcademiaResponseDTO::new).toList();

        return ResponseEntity.ok(lista);
    }

    @PreAuthorize("hasAnyRole('USERADMIN', 'USERACADADMIN')")
    @PostMapping
    public ResponseEntity<Map<String, Object>> registrarAcademia(@RequestBody @Valid AcademiaRequestDTO data) {
        AcademiaResponseDTO academiaResponseDTO = new AcademiaResponseDTO(academiaService.registrarAcademia(data));
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Academia cadastrada com sucesso!");
        response.put("academia", academiaResponseDTO);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("@academiaService.usuarioPodeGerenciar(#id) or hasAnyRole('USERADMIN', 'USERACADADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarAcademia(@PathVariable Long id, @RequestBody @Valid AcademiaRequestDTO data) {
        AcademiaResponseDTO updatedAcademia = new AcademiaResponseDTO(academiaService.atualizarAcademia(data, id));
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Academia atualizada com sucesso!");
        response.put("academia", updatedAcademia);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("@academiaService.usuarioPodeGerenciar(#id) or hasAnyRole('USERADMIN', 'USERACADADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarAcademia(@PathVariable Long id) {
        academiaService.deletarAcademia(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Academia deletada com sucesso!");
        return ResponseEntity.ok(response);
    }

    @GetMapping("externas")
    public List<AcademiaExternaDTO> buscarAcademias(@RequestParam String endereco) {
        return academiaService.buscarAcademiasProximas(endereco);
    }

    @GetMapping("externas-detalhes")
    public AcademiaDetalhesDTO buscarDetalhes(@RequestParam String placeId) {
        return academiaService.detalhesComoDTO(placeId);
    }
}
