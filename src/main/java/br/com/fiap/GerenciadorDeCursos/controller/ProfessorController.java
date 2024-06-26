package br.com.fiap.GerenciadorDeCursos.controller;


import br.com.fiap.GerenciadorDeCursos.dto.professor.AtualizarProfessorDTO;
import br.com.fiap.GerenciadorDeCursos.dto.professor.CadastroProfessorDTO;
import br.com.fiap.GerenciadorDeCursos.dto.professor.DetalhamentoProfessorDTO;
import br.com.fiap.GerenciadorDeCursos.exceptions.ErrorMessage;
import br.com.fiap.GerenciadorDeCursos.exceptions.NotFoundResourceException;
import br.com.fiap.GerenciadorDeCursos.model.Professor;
import br.com.fiap.GerenciadorDeCursos.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @Autowired
    private ErrorMessage error;

    @Operation(summary = "Cadastra um professor na base de dados", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Professor.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar professor")})
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody CadastroProfessorDTO cadastroProfessorDTO){
        try {
            DetalhamentoProfessorDTO Professors = professorService.salvarProfessor(cadastroProfessorDTO);
            return ResponseEntity.status(201).body(Professors);
        }catch (Exception e){
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @Operation(summary = "Lista os professores da base de dados", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Professor.class)))})
    @GetMapping
    public ResponseEntity buscarTodos(){
        try {
            List<Professor> Professors = professorService.buscarTodosProfessors();
            return ResponseEntity.status(200).body(Professors);
        }catch (Exception e){
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @Operation(summary = "Busca um professor pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Professor.class))),
            @ApiResponse(responseCode = "400", description = "Professor não encontrado")})
    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        try {
            Professor professor = professorService.buscarProfessorPorId(id);
            Link link = linkTo(ProfessorController.class).slash(professor.getId()).withSelfRel();
            professor.add(link);
            return ResponseEntity.status(200).body(professor);
        }catch (NotFoundResourceException e){
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @Operation(summary = "Altera um professor pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Professor.class))),
            @ApiResponse(responseCode = "400", description = "Professor não encontrado")})
    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody AtualizarProfessorDTO atualizarProfessorDTO){
        try {
            Professor Professor = professorService.atualizarProfessor(id, atualizarProfessorDTO);
            return ResponseEntity.status(200).body(Professor);
        }catch (NotFoundResourceException e){
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @Operation(summary = "Deleta um professor pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Professor.class))),
            @ApiResponse(responseCode = "400", description = "Professor não encontrado")})
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        try {
            professorService.deletarProfessor(id);
            return ResponseEntity.status(204).build();
        }catch (Exception e){
            error.setError(e.getMessage());
            error.setObservacao("Este endpoint funciona parcialmente por conta do 'CascadeType.MERGE' solicitado na documentação.");
            return ResponseEntity.status(400).body(error);
        } catch (NotFoundResourceException e){
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

}
