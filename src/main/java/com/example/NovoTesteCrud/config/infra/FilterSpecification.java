package com.example.NovoTesteCrud.config.infra;

import org.springframework.data.jpa.domain.Specification;

public interface FilterSpecification<T> {
    public Specification<T> toSpecification();
}
