package com.nortec.escola.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.nortec.escola.modelo.Materia;
import com.nortec.escola.modelo.Professor;
import com.nortec.escola.repository.MateriaRepository;
import com.nortec.escola.repository.ProfessorRepository;

@RestController // Transforma a nossa classe em um controller antigamente chamdo bean
@RequestMapping(value = "/materia") // Mapeando a url, navegador chama pelo valor
public class MateriaController {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping("/salvarQuintoExemplo") // injeção de dependência
    public ResponseEntity<?> salvarQuintoExemplo(@RequestBody MateriaDto materiaDto) {
         // 2) se o professor não existe então cria o professor
        Professor professor =  Optional.ofNullable(                              
                        professorRepository.findByNome( materiaDto.nomeDoProfessor() ) )                                    
                        .orElseGet( () -> professorRepository.save(materiaDto.novoProfessor() ) );

        // 3) verfica se a materia existe (pela lógica do seu repositório)
        return  Optional.ofNullable(                              
                        
                        materiaRepository.buscarMateria( materiaDto.getNome() ) )
                        
                        .map(m -> ResponseEntity.status(HttpStatus.CONFLICT).body(m))
                        
                        .orElseGet( () -> { 
                            
                            Materia materiaNova = materiaDto.novaMateria();

                            materiaNova.setProfessor(professor);
                            
                            Materia materiaSalva = materiaRepository.save( materiaNova );

                            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(materiaSalva.getId())
                                    .toUri();
                            return ResponseEntity.created(uri).body(materiaSalva);
                            
                        });       
    }

    @PostMapping("/salvarQuartoExemplo") // injeção de dependência
    public ResponseEntity<?> salvarQuartoExemplo(@RequestBody MateriaDto materiaDto) {
       
        // 2) se o professor não existe então cria o professor
        Professor professor =  Optional.ofNullable(                              
                        professorRepository.findByNome( materiaDto.nomeDoProfessor() ) )                                    
                        .orElseGet( () -> professorRepository.save(materiaDto.novoProfessor() ) );

        // 3) verfica se a materia existe (pela lógica do seu repositório)
        return  Optional.ofNullable(                              
                        
                        materiaRepository.buscarMateria( materiaDto.getNome() ) )
                        
                        .map(m -> ResponseEntity.status(HttpStatus.CONFLICT).body(m))
                        
                        .orElseGet( () -> { 
                            
                            Materia materiaNova = materiaDto.novaMateria();

                            // Refatorando
                            //materiaDto.novaMateriaComProfessor(professor);

                            materiaNova.setProfessor(professor);
                            
                            Materia materiaSalva = materiaRepository.save( materiaNova );

                            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(materiaSalva.getId())
                                    .toUri();
                            return ResponseEntity.created(uri).body(materiaSalva);
                            
                        });       
      
    }

    @PostMapping("/salvarTerceiroExemplo") // injeção de dependência
    public ResponseEntity<?> salvarTerceiroExemplo(@RequestBody MateriaDto materiaDto) {

        // ================================================================
        // Terceiro Exemplo
        // ================================================================

        // 1) verificar se o professor existe (pelo nome, conforme seu código)
        Professor professorDoBanco = professorRepository.findByNome(materiaDto.nomeDoProfessor());

        // 2) se o professor não existe então cria o professor
        Professor professor;
        if (professorDoBanco == null) {

            // usa o helper do DTO para criar a entidade (ainda não persistida)
            Professor novo = materiaDto.novoProfessor();

            // salva no banco e recebe o professor com id
            professor = professorRepository.save(novo);

        } else {

            // depois de recuperado, devolve o professor do banco
            professor = professorDoBanco;

        }

        // 3) verfica se a materia existe (pela lógica do seu repositório)
        Materia materiaDoBanco = materiaRepository.buscarMateria(materiaDto.getNome());

        // 4) se a materia já existe então devolve CONFLICT (pode ajustar conforme
        // necessidade)
        if (materiaDoBanco != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(materiaDoBanco);
        }

        // 5) se a materia não existe então cria a materia (usa o DTO para criar a
        // instância)
        Materia materia = materiaDto.novaMateria(); // cria com um Professor "novo" do DTO

        // 6) agora que eu já tenho o professor persistido, substituo/associo
        // corretamente
        materia.setProfessor(professor);

        // 7) salvo a materia (tem que salvar pois inseri/associei o professor
        // persistido)
        Materia materiaSalva = materiaRepository.save(materia);

        // 8) depois de criado a materia devolve a materia criada
        // return ResponseEntity.status(HttpStatus.CREATED).body(materiaSalva);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(materiaSalva.getId())
                .toUri();
        return ResponseEntity.created(uri).body(materiaSalva);
    }

    @PostMapping("/salvarSegundoExemplo") // injeção de dependência
    public ResponseEntity<?> salvarSegundoExemplo(@RequestBody MateriaDto materiaDto) {
        // ================================================================
        // Segundo Exemplo
        // ================================================================
        // verificar se o professor existe
        Materia materiaNova = null;
        if (professorRepository.existsByNome(materiaDto.getProfessor().getNome())) {
            // se o professor existe

            Professor professor = professorRepository.findByNome(materiaDto.getProfessor().getNome());

            if (materiaRepository.existsByNome(materiaDto.getNome())) {

                Materia materia = materiaRepository.buscarMateria(materiaDto.getNome());

                // adicionar o professor na materia
                materia.setProfessor(professor);

            } else {
                materiaNova = new Materia(materiaDto.getNome());
                materiaRepository.save(materiaNova);
            }

        } else {

            Professor professorNovo = new Professor(materiaDto.getProfessor().getNome(),
                    materiaDto.getProfessor().getCpf());

            professorRepository.save(professorNovo);

        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(materiaNova.getId())
                .toUri();
        return ResponseEntity.created(uri).body(materiaNova);
    }

    @PostMapping("/salvarPrimeiroExemplo") // injeção de dependência
    public ResponseEntity<?> salvarPrimeiroExemplo(@RequestBody MateriaDto materiaDto) {
        // ==================================================================
        // Primeiro Exemplo --> Salvando no Banco de dados somente a materia
        // ==================================================================
        Materia materia = materiaDto.novaMateria();
        materiaRepository.save(materia);
        System.out.println(materia.toString());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(materia.getId())
                .toUri();
        return ResponseEntity.created(uri).body(materia);
    }

    @GetMapping(value = "/imprimir") // Mapeamento do método imprimir. Usando o verbo Get -> Pegar ou Buscar
    public void imprimir() { // void -> não retorna nada
        System.out.println("Chamou o método imprimir");
    }

    @GetMapping(value = "/ola")
    public String ola() { // Adicionado retorno como String - Tipo Texto //Não quer dizer html
        System.out.println("Chamou o método ola");
        return "<h1>Norberto</h1>"; // return -> Devolve o retorno para quem chamou
    }

    @GetMapping(value = "/listaMateria")
    public List<Materia> findAll() {
        return materiaRepository.findAll();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        materiaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/consultaPorNome/{nome}")
    public ResponseEntity<Materia> buscarMateriaPorNome(@PathVariable String nome) {
        Materia MateriaBanco = materiaRepository.buscarMateria(nome);
        return ResponseEntity.ok().body(MateriaBanco);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Materia> buscarMateriaPor(@PathVariable Long id) {
        Optional<Materia> materiaBanco = materiaRepository.findById(id);
        return ResponseEntity.ok(materiaBanco.get());
    }

    @GetMapping(value = "/buscaPorNome/{nome}")
    public ResponseEntity<Materia> findByNome(@PathVariable String nome) {
        Materia Materia = materiaRepository.buscarMateria(nome);
        return ResponseEntity.ok().body(Materia);
    }

}
