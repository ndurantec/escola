package com.nortec.escola.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nortec.escola.dto.ProfessorDto;
import com.nortec.escola.modelo.Professor;



@RestController //Transforma a nossa classe em um controller antigamente chamdo bean
@RequestMapping( value = "/professor") //Mapeando a url, navegador chama pelo valor
public class ProfessorController {
    
    @GetMapping( value = "/imprimir") //Mapeamento do método imprimir. Usando o verbo Get -> Pegar ou Buscar
    public void imprimir() { //void -> não retorna nada
        System.out.println("Chamou o método imprimir");
    }

    @GetMapping( value = "/ola")
    public String ola() { //Adicionado retorno como String - Tipo Texto //Não quer dizer html
        System.out.println("Chamou o método ola");
        return "Norberto"; //return -> Devolve o retorno para quem chamou
    }  
    
    @GetMapping( value = "/insert")
    public String insert(@RequestBody ProfessorDto professorDto) {

        Professor professor = professorDto.novoProfessor();
        System.out.println(professor.toString());

        

        return "<h1>Tentando Salvar o Professor dos Alunos</h1>";
    }

}
