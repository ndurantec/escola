package com.nortec.escola.controller;



import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nortec.escola.dto.ProfessorDto;
import com.nortec.escola.modelo.Professor;
import com.nortec.escola.repository.ProfessorRepository;
import org.springframework.web.bind.annotation.PostMapping;




@RestController //Transforma a nossa classe em um controller antigamente chamdo bean
@CrossOrigin("*")
@RequestMapping( value = "/professor") //Mapeando a url, navegador chama pelo valor
public class ProfessorController {
    
    @Autowired
    private ProfessorRepository professorRepository;

    // @PostMapping(value = "/insert")  
    // public ResponseEntity<?> insert(@RequestBody ProfessorDto professorDto) {

    //     Professor professor = professorDto.novoProfessor();
        
    //     professorRepository.save(professor);
        
    //     System.out.println(professor.toString());

    //     URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
    //                                          .path("/id")
    //                                          .buildAndExpand(professor.getId())
    //                                          .toUri();

    //     return ResponseEntity.created(uri).body(professor);
    // }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody ProfessorDto professorDto) {
        Professor professor = professorDto.novoProfessor();
        professorRepository.save(professor);
        System.out.println(professor.toString());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                                             .path("{/id}")
                                             .buildAndExpand(professor.getId())
                                             .toUri();
        return ResponseEntity.created(uri).body(professor);        
    }


    @GetMapping( value = "/imprimir") //Mapeamento do método imprimir. Usando o verbo Get -> Pegar ou Buscar
    public void imprimir() { //void -> não retorna nada
        System.out.println("Chamou o método imprimir");
    }

    @GetMapping( value = "/ola")
    public String ola() { //Adicionado retorno como String - Tipo Texto //Não quer dizer html
        System.out.println("Chamou o método ola");
        return "<h1>Norberto</h1>"; //return -> Devolve o retorno para quem chamou
    }  

    @GetMapping( value = "/listaprofessor")
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }
    
    @DeleteMapping( value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {       
        professorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping( value = "/consultaPorNome/{nome}")
    public ResponseEntity<Professor> buscarProfessorPorNome(@PathVariable String nome) {
        Professor professorBanco =  professorRepository.findByNome(nome);
        return ResponseEntity.ok().body(professorBanco);
    }
    
    @GetMapping( value = "/{id}")
    public ResponseEntity<Professor> buscarProfessorPor(@PathVariable Long id) {
        Optional<Professor> professorBanco =  professorRepository.findById(id);
        return ResponseEntity.ok(professorBanco.get());
    }

    @GetMapping( value = "/buscaPorNome/{nome}")
    public ResponseEntity<Professor> findByNome(@PathVariable String nome) {
        Professor professor = professorRepository.findByNome(nome);
        return ResponseEntity.ok().body(professor);
    }
}