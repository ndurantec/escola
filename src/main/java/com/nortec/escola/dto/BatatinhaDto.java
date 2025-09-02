package com.nortec.escola.dto;

public class BatatinhaDto {
    
    private String nome;

    @Deprecated
    public BatatinhaDto() {
    }

    public BatatinhaDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "BatatinhaDto [nome=" + nome + "]";
    }
}