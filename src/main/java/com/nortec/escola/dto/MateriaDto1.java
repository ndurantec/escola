package com.nortec.escola.dto;

public class MateriaDto1 {
    
    private String nome;
    private ProfessorDto professorDto;
    
    @Deprecated
    public MateriaDto1() {
    }

    public MateriaDto1(String nome) {
        this.nome = nome;
    }

    public MateriaDto1(String nome, ProfessorDto professorDto) {
        this.nome = nome;
        this.professorDto = professorDto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ProfessorDto getProfessorDto() {
        return professorDto;
    }

    public void setProfessorDto(ProfessorDto professorDto) {
        this.professorDto = professorDto;
    }

    @Override
    public String toString() {
        return "MateriaDto1 [nome=" + nome + " " +
                "professorDto=" + professorDto.getNome() + " " +
                                  professorDto.getCpf()  + "]";
    }    
}