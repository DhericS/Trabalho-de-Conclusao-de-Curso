package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.dieta.Dieta;
import com.example.NovoTesteCrud.domain.dieta.dto.DietaRequestDTO;
import com.example.NovoTesteCrud.domain.user.UserAcad;
import com.example.NovoTesteCrud.domain.userbase.Usuario;
import com.example.NovoTesteCrud.repository.DietaRepository;
import com.example.NovoTesteCrud.repository.UserAcadRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietaService {

    @Autowired
    private DietaRepository dietaRepository;
    @Autowired
    private UserAcadRepository userAcadRepository;

    public List<Dieta> listarTodasDieta() {
        return dietaRepository.findAll();
    }

    public Dieta criarDieta(DietaRequestDTO dto) {
        UserAcad userAcad = userAcadRepository.findById(dto.userAcadId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário acadêmico não encontrado"));

        Dieta dieta = new Dieta(null, dto.titulo(), dto.descricao(), dto.calorias(), dto.objetivo(), userAcad);
        return dietaRepository.save(dieta);
    }

    public Dieta atualizarDieta(Long id, DietaRequestDTO dto) {
        Dieta dieta = dietaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dieta não encontrada"));
        dieta.setTitulo(dto.titulo());
        dieta.setDescricao(dto.descricao());
        dieta.setCalorias(dto.calorias());
        dieta.setObjetivo(dto.objetivo());
        return dietaRepository.save(dieta);
    }

    public void deletarDieta(Long id) {
        dietaRepository.deleteById(id);
    }
}