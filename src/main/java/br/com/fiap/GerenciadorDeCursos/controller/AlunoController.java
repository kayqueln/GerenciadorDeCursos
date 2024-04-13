package br.com.fiap.GerenciadorDeCursos.controller;

import br.com.fiap.GerenciadorDeCursos.dto.aluno.AtualizarAlunoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.aluno.CadastroAlunoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.aluno.DetalhamentoAlunoDTO;
import br.com.fiap.GerenciadorDeCursos.exceptions.ErrorMessage;
import br.com.fiap.GerenciadorDeCursos.exceptions.NotFoundResourceException;
import br.com.fiap.GerenciadorDeCursos.model.Aluno;
import br.com.fiap.GerenciadorDeCursos.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;
    @Autowired
    private ErrorMessage error;

    @Operation(summary = "Cadastra um aluno na base de dados", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar aluno")})
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody CadastroAlunoDTO cadastroAlunoDTO){
        try {
            DetalhamentoAlunoDTO alunos = alunoService.salvarAluno(cadastroAlunoDTO);
            return ResponseEntity.status(201).body(alunos);
        }catch (Exception e){
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }


    @Operation(summary = "Lista os alunos da base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Aluno.class)))})
    @GetMapping
    public ResponseEntity buscarTodos(){
        try {
            List<Aluno> alunos = alunoService.buscarTodosAlunos();
            return ResponseEntity.status(200).body(alunos);
        }catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Operation(summary = "Busca um aluno pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Aluno encontrado",
                    content = @Content(schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "400", description = "Aluno não encontrado")})
    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        try {
            Aluno aluno = alunoService.buscarAlunoPorId(id);
            return ResponseEntity.status(200).body(aluno);
        }catch (NotFoundResourceException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Operation(summary = "Altera um aluno pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Aluno encontrado",
                    content = @Content(schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "400", description = "Aluno não encontrado")})
    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody AtualizarAlunoDTO atualizarAlunoDTO){
        try {
            Aluno aluno = alunoService.atualizarAluno(id, atualizarAlunoDTO);
            return ResponseEntity.status(200).body(aluno);
        }catch (NotFoundResourceException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Operation(summary = "Deleta um aluno pelo ID", responses = {
            @ApiResponse(responseCode = "204", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "400", description = "Aluno não encontrado")})
    @DeleteMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id){
        try {
            alunoService.deletarAluno(id);
            return ResponseEntity.status(204).build();
        }catch (NotFoundResourceException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
