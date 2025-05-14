package com.example.NovoTesteCrud.domain.treino.dto;

import com.example.NovoTesteCrud.domain.exercicios.dto.ExercicioSimplesDTO;
import com.example.NovoTesteCrud.domain.treino.Treino;
import com.example.NovoTesteCrud.domain.treino.enums.Cardio;
import com.example.NovoTesteCrud.domain.treino.enums.Hipertrofia_Performace;
import com.example.NovoTesteCrud.domain.treino.enums.Tipos;

import java.util.List;

public record TreinoResponseDTO(
        Long id,
        String nome,
        String descricao,
        List<Cardio> cardios,
        List<Hipertrofia_Performace> hipertrofias,
        List<Tipos> tiposTreino,
        List<ExercicioSimplesDTO> exercicios
) {
    public TreinoResponseDTO(Treino treino) {
        this(
                treino.getId(),
                treino.getNome(),
                treino.getDescricao(),
                treino.getCardios(),
                treino.getHipertrofias(),
                treino.getTiposTreino(),
                treino.getExercicios().stream().map(ExercicioSimplesDTO::new).toList()
        );
    }
}
