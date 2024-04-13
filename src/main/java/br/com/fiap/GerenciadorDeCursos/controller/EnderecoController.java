package br.com.fiap.GerenciadorDeCursos.controller;

import br.com.fiap.GerenciadorDeCursos.exceptions.ErrorMessage;
import br.com.fiap.GerenciadorDeCursos.exceptions.NotFoundResourceException;
import br.com.fiap.GerenciadorDeCursos.model.Curso;
import br.com.fiap.GerenciadorDeCursos.model.Endereco;
import br.com.fiap.GerenciadorDeCursos.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ErrorMessage error;


    @Operation(summary = "Lista os endereços da base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Curso.class)))})
    @GetMapping
    public ResponseEntity buscarTodos(){
        try {
            List<Endereco> enderecos = enderecoService.buscarTodosEnderecos();
            return ResponseEntity.status(200).body(enderecos);
        }catch (Exception e){
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }

    @Operation(summary = "Busca um endereço pelo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "400", description = "Endereço não encontrado")})
    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        try {
            Endereco endereco = enderecoService.buscarPorId(id);
            return ResponseEntity.status(200).body(endereco);
        }catch (NotFoundResourceException e){
            error.setError(e.getMessage());
            return ResponseEntity.status(400).body(error);
        }
    }
}
