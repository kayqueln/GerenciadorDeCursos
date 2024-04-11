package br.com.fiap.GerenciadorDeCursos.controller;

import br.com.fiap.GerenciadorDeCursos.dto.inscricao.CadastroInscricaoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.inscricao.DetalhamentoInscricaoDTO;
import br.com.fiap.GerenciadorDeCursos.exceptions.NotFoundResourceException;
import br.com.fiap.GerenciadorDeCursos.model.Inscricao;
import br.com.fiap.GerenciadorDeCursos.service.InscricaoService;
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

    @PostMapping
    public ResponseEntity inscreverAluno(@RequestBody CadastroInscricaoDTO cadastroInscricaoDTO){
        try{
            Inscricao inscricao = inscricaoService.inscreverUmAluno(cadastroInscricaoDTO);
            return ResponseEntity.status(201).body(new DetalhamentoInscricaoDTO(inscricao));
        }catch (NotFoundResourceException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<DetalhamentoInscricaoDTO>> listarInscricoes(){
        List<DetalhamentoInscricaoDTO> inscricaos = inscricaoService.listarInscricoes();
        return ResponseEntity.status(200).body(inscricaos);
    }
}
