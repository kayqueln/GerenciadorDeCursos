package br.com.fiap.GerenciadorDeCursos.controller;

import br.com.fiap.GerenciadorDeCursos.dto.inscricao.CadastroInscricaoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.inscricao.DetalhamentoInscricaoDTO;
import br.com.fiap.GerenciadorDeCursos.exceptions.NotFoundResourceException;
import br.com.fiap.GerenciadorDeCursos.model.Aluno;
import br.com.fiap.GerenciadorDeCursos.model.Inscricao;
import br.com.fiap.GerenciadorDeCursos.service.InscricaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/inscricao")
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

    @Operation(summary = "Inscreve os aluno nos curso", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Inscricao.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao inscrever aluno")})
    @PostMapping
    public ResponseEntity inscreverAluno(@RequestBody CadastroInscricaoDTO cadastroInscricaoDTO){
        try{
            Inscricao inscricao = inscricaoService.inscreverUmAluno(cadastroInscricaoDTO);
            return ResponseEntity.status(201).body(new DetalhamentoInscricaoDTO(inscricao));
        }catch (NotFoundResourceException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @Operation(summary = "Lista as inscrições da base de dados", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Inscricao.class)))})
    @GetMapping
    public ResponseEntity<List<DetalhamentoInscricaoDTO>> listarInscricoes(){
        List<DetalhamentoInscricaoDTO> inscricaos = inscricaoService.listarInscricoes();
        return ResponseEntity.status(200).body(inscricaos);
    }
}
