package com.example.NovoTesteCrud.repository;

import com.example.NovoTesteCrud.domain.acad.Academia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademiaRepository extends JpaRepository<Academia, Long> {
}
