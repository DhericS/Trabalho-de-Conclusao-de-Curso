package com.example.NovoTesteCrud.domain.dieta.dto;

import com.example.NovoTesteCrud.domain.dieta.Dieta;
import com.example.NovoTesteCrud.domain.dieta.enums.TipoDieta;
import com.example.NovoTesteCrud.infra.FilterSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class DietaFilterDto implements FilterSpecification<Dieta> {

    private final List<TipoDieta> tiposDieta;

    public DietaFilterDto(List<TipoDieta> tiposDieta) {
        this.tiposDieta = tiposDieta;
    }

    @Override
    public Specification<Dieta> toSpecification() {
        Specification<Dieta> spec = Specification.where(null);

        if (tiposDieta != null && !tiposDieta.isEmpty()) {
            spec = spec.and((root, query, cb) -> root.get("tipoDieta").in(tiposDieta));
        }

        return spec;
    }
}
