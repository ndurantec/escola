package com.nortec.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nortec.escola.modelo.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    
}
