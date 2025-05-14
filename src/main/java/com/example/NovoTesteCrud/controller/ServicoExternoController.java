package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.servicoextra.dto.ServicoExternoRequestDTO;
import com.example.NovoTesteCrud.domain.servicoextra.dto.ServicoExternoResponseDTO;
import com.example.NovoTesteCrud.service.ServicoExternoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/servicos-externos")
@RequiredArgsConstructor
public class ServicoExternoController {

    private final ServicoExternoService service;


    @GetMapping
    public List<ServicoExternoResponseDTO> listar() {
        return service.listarServicosExternos();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USERADMIN', 'USERACADADMIN')")
    public ResponseEntity<?> criar(@RequestBody @Valid ServicoExternoRequestDTO dto) {
        var entity = service.registrarServicoExterno(dto);
        return ResponseEntity.ok(Map.of(
                "message", "Serviço externo cadastrado com sucesso!",
                "servico", new ServicoExternoResponseDTO(entity)
        ));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USERADMIN', 'USERACADADMIN')")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid ServicoExternoRequestDTO dto) {
        var entity = service.atualizarServicoExterno(id, dto);
        return ResponseEntity.ok(Map.of(
                "message", "Serviço externo atualizado com sucesso!",
                "servico", new ServicoExternoResponseDTO(entity)
        ));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USERADMIN', 'USERACADADMIN')")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        service.deletarServicoExterno(id);
        return ResponseEntity.ok(Map.of("message", "Serviço externo removido com sucesso!"));
    }
}
