package com.nortec.escola.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nortec.escola.dto.MateriaDto;
import com.nortec.escola.dto.MateriaDto1;
import com.nortec.escola.dto.ProfessorDto;
import com.nortec.escola.modelo.Materia;
import com.nortec.escola.modelo.Professor;
import com.nortec.escola.repository.MateriaRepository;
import com.nortec.escola.repository.ProfessorRepository;

@RestController //Transforma a nossa classe em um controller antigamente chamdo bean
@RequestMapping( value = "/materia") //Mapeando a url, navegador chama pelo valor
public class MateriaController {
    

   
    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private ProfessorRepository professorRepository;
  

    @PostMapping("/cadMat") //injeção de dependência
    public ResponseEntity<?> cadastrarMateria(@RequestBody MateriaDto materiaDto) {
        
        //Cola a materia no objeto       //busca a materia no banco de dados
        Materia materia           =      materiaRepository.buscarMateria(materiaDto.getNome());

        Professor professor       =      professorRepository.findByNome(materiaDto.getProfessor().getNome());
        
         Professor professorNovo = null;
        
        //Se não achou no banco a materia o objeto está null
        if (professor == null) {
            //cria o objeto pra mim na memoria;
            Materia materiaNova = new Materia(materiaDto.getNome());
            
            professorNovo = new Professor(materiaDto.getProfessor().getNome(), materiaDto.getProfessor().getCpf());
            
            //salva o objeto da memoria no banco de dados
            professorRepository.save(professorNovo);
        } else {
            System.out.println("Já existe a professor no banco");
        }
        
        //Se não achou no banco a materia o objeto está null
        if (materia == null) {
            //cria o objeto pra mim na memoria;
            Materia materiaNova = new Materia(materiaDto.getNome());
            
            materiaNova.setProfessor(professorNovo);

            //salva o objeto da memoria no banco de dados
            materiaRepository.save(materiaNova);
        } else {
            System.out.println("Já existe a matéria no banco");
        }




        
        //Cadastrar isso no Banco de dados

        //Instanciar os objetos

        //







       
       


        

       
        

        // Materia materia = materiaDto.novaMateria();
        // materiaRepository.save(materia);
        // System.out.println(materia.toString());
        // URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        //                                      .path("/{id}")
        //                                      .buildAndExpand(materia.getId())
        //                                      .toUri();
        // return ResponseEntity.created(uri).body(materia); 
        return null;       
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

    @GetMapping( value = "/listaMateria")
    public List<Materia> findAll() {
        return materiaRepository.findAll();
    }
    
    @DeleteMapping( value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {       
        materiaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping( value = "/consultaPorNome/{nome}")
    public ResponseEntity<Materia> buscarMateriaPorNome(@PathVariable String nome) {
        Materia MateriaBanco =  materiaRepository.buscarMateria(nome);
        return ResponseEntity.ok().body(MateriaBanco);
    }
    
    @GetMapping( value = "/{id}")
    public ResponseEntity<Materia> buscarMateriaPor(@PathVariable Long id) {
        Optional<Materia> materiaBanco =  materiaRepository.findById(id);
        return ResponseEntity.ok(materiaBanco.get());
    }

    @GetMapping( value = "/buscaPorNome/{nome}")
    public ResponseEntity<Materia> findByNome(@PathVariable String nome) {
        Materia Materia = materiaRepository.buscarMateria(nome);
        return ResponseEntity.ok().body(Materia);
    }


    //  Professor professor = professorRepository.findByNome(materiaDto.getProfessor().getNome());
        
    //     System.out.println("================================================");
    //     System.out.println("================================================");
    //     System.out.println("================================================");
    //     System.out.println( professor.toString() );        
    //     System.out.println("================================================");
    //     System.out.println("================================================");
    //     System.out.println("================================================");

}
