package com.nortec.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nortec.escola.modelo.Materia;

public interface MateriaRepository extends JpaRepository<Materia, Long>{
    
    @Query( value = "select * from materia where nome like ?", nativeQuery = true )   
    public Materia buscarMateria(String nome);

    boolean existsByNome(String nome);
}
