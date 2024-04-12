package br.com.fiap.GerenciadorDeCursos.controller;


import br.com.fiap.GerenciadorDeCursos.dto.curso.AtualizarCursoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.curso.CadastroCursoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.curso.DetalhamentoCursoDTO;
import br.com.fiap.GerenciadorDeCursos.exceptions.NotFoundResourceException;
import br.com.fiap.GerenciadorDeCursos.model.Aluno;
import br.com.fiap.GerenciadorDeCursos.model.Curso;
import br.com.fiap.GerenciadorDeCursos.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @Operation(summary = "Busca os cursos na base de dados com Hateoas", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Curso.class)))})
    @GetMapping("/hateoas")
    public ResponseEntity<List<EntityModel<Curso>>> buscarCursosComHateoas(){
        List<EntityModel<Curso>> cursos = cursoService.buscarTodosCursos().stream()
                .map(curso -> EntityModel.of(curso,
                        linkTo(methodOn(CursoController.class).buscarPorId(curso.getId())).withSelfRel()),
                        linkTo((methodOn(ProfessorController.class).buscarPorId())))
                .collect(Collectors.toList());
        return ResponseEntity.status(200).body(cursos);
    }

    @Operation(summary = "Cadastra um curso na base de dados", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar curso")})
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody CadastroCursoDTO cadastroCursoDTO){
        try {
            Curso curso = cursoService.salvarCurso(cadastroCursoDTO);
            return ResponseEntity.status(201).body(new DetalhamentoCursoDTO(curso));
        }catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Operation(summary = "Lista os cursos da base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Curso.class)))})
    @GetMapping
    public ResponseEntity buscarTodos(){
        try {
            List<Curso> cursos = cursoService.buscarTodosCursos();
            return ResponseEntity.status(200).body(cursos);
        }catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Operation(summary = "Busca um curso pelo ID", responses = {
            @ApiResponse(responseCode = "204", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "400", description = "Curso não encontrado")})
    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        try {
            Curso curso = cursoService.buscarCursoPorId(id);
            Link link = linkTo(CursoController.class).slash(curso.getId()).withSelfRel();
            curso.add(link);
            return ResponseEntity.status(200).body(curso);
        }catch (NotFoundResourceException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Operation(summary = "Atualiza um curso pelo ID", responses = {
            @ApiResponse(responseCode = "204", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "400", description = "Curso não encontrado")})
    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody AtualizarCursoDTO atualizarCursoDTO){
        try {
            Curso Curso = cursoService.atualizarCurso(id, atualizarCursoDTO);
            return ResponseEntity.status(200).body(Curso);
        }catch (NotFoundResourceException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    //Este endpoint não está funcionando por conta do "CascadeType.PERSIST" que foi solicitado na documentação.
    //Em caso de substituição para o "CascadeType.ALL" o endpoint funciona normalmente
    @Operation(summary = "Deleta um curso pelo ID", responses = {
            @ApiResponse(responseCode = "204", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "400", description = "Curso não encontrado")})
    @DeleteMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id){
        try {
            cursoService.deletarCurso(id);
            return ResponseEntity.status(204).build();
        }catch (NotFoundResourceException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    
}
