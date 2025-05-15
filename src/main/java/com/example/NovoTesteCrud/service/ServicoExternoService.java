package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.acad.Academia;
import com.example.NovoTesteCrud.domain.servicoextra.ServicoExterno;
import com.example.NovoTesteCrud.domain.servicoextra.dto.ServicoExternoRequestDTO;
import com.example.NovoTesteCrud.domain.servicoextra.dto.ServicoExternoResponseDTO;
import com.example.NovoTesteCrud.repository.AcademiaRepository;
import com.example.NovoTesteCrud.repository.ServicoExternoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoExternoService {

    private final ServicoExternoRepository servicoExternoRepository;
    private final AcademiaRepository academiaRepository;

    public ServicoExterno registrarServicoExterno(ServicoExternoRequestDTO dto) {
        Academia academia = academiaRepository.findById(dto.academiaId())
                .orElseThrow(() -> new RuntimeException("Academia não encontrada"));

        ServicoExterno servico = ServicoExterno.builder()
                .nome(dto.nome())
                .tipo(dto.tipo())
                .descricao(dto.descricao())
                .contato(dto.contato())
                .link(dto.link())
                .academia(academia)
                .build();

        return servicoExternoRepository.save(servico);
    }

    public ServicoExterno atualizarServicoExterno(Long id, ServicoExternoRequestDTO dto) {
        ServicoExterno servicoExistente = servicoExternoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço externo não encontrado"));

        Academia academia = academiaRepository.findById(dto.academiaId())
                .orElseThrow(() -> new RuntimeException("Academia não encontrada"));

        servicoExistente.setNome(dto.nome());
        servicoExistente.setTipo(dto.tipo());
        servicoExistente.setDescricao(dto.descricao());
        servicoExistente.setContato(dto.contato());
        servicoExistente.setLink(dto.link());
        servicoExistente.setAcademia(academia);

        return servicoExternoRepository.save(servicoExistente);
    }

    public List<ServicoExternoResponseDTO> listarServicosExternos() {
        return servicoExternoRepository.findAll().stream()
                .map(ServicoExternoResponseDTO::new)
                .toList();
    }

    public void deletarServicoExterno(Long id) {
        servicoExternoRepository.deleteById(id);
    }
}
