package com.example.NovoTesteCrud.controller;

import com.example.NovoTesteCrud.domain.avaliacao.dto.AvaliacaoRequestDTO;
import com.example.NovoTesteCrud.domain.avaliacao.dto.AvaliacaoResponseDTO;
import com.example.NovoTesteCrud.domain.avaliacao.enums.TipoEntidade;
import com.example.NovoTesteCrud.service.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoService service;

    public AvaliacaoController(AvaliacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoResponseDTO>> listarPorEntidade(
            @RequestParam TipoEntidade tipoEntidade,
            @RequestParam Long entidadeId
    ) {
        return ResponseEntity.ok(service.listarPorEntidade(tipoEntidade, entidadeId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AvaliacaoResponseDTO>> listarPorUsuarioETipoEntidade(
            @PathVariable Long userId,
            @RequestParam TipoEntidade tipoEntidade
    ) {
        return ResponseEntity.ok(service.listarPorUsuarioETipoEntidade(userId, tipoEntidade));
    }


    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> criar(@Valid @RequestBody AvaliacaoRequestDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AvaliacaoRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
