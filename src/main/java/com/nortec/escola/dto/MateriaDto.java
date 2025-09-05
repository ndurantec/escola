package com.nortec.escola.dto;

import com.nortec.escola.modelo.Materia;
import com.nortec.escola.modelo.Professor;

public class MateriaDto {
    
    private String nome;
    private ProfessorDto professor;

    @Deprecated
    public MateriaDto() {
    }

    public MateriaDto(String nome) {
        this.nome = nome;
    }

    public MateriaDto(String nome, ProfessorDto professor) {
        this.nome = nome;
        this.professor = professor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ProfessorDto getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorDto professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "MateriaDto [nome=" + nome + ", professor=" + professor + "]";
    }

    public Materia novaMateria() {
        return new Materia(this.nome, this.professor.novoProfessor());
    }

    public Materia novaMateriaComProfessor(Professor professor) {
        return new Materia(this.nome, professor);
    }

    public String nomeDoProfessor() {
        return this.professor.getNome();
    }

    public Professor novoProfessor() {
        return new Professor(this.getProfessor().getNome(), this.getProfessor().getCpf());
    }
    
    // @Override
    // public String toString() {
    //     return "MateriaDto [nome=" + nome                + 
    //         ", Nome do professor=" + professor.getNome() + 
    //                       ", cpf=" + professor.getCpf()  + "]";
    // }
    
    


}