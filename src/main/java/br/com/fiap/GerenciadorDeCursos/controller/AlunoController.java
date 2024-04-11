package br.com.fiap.GerenciadorDeCursos.controller;

import br.com.fiap.GerenciadorDeCursos.dto.aluno.AtualizarAlunoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.aluno.CadastroAlunoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.aluno.DetalhamentoAlunoDTO;
import br.com.fiap.GerenciadorDeCursos.exceptions.NotFoundResourceException;
import br.com.fiap.GerenciadorDeCursos.model.Aluno;
import br.com.fiap.GerenciadorDeCursos.service.AlunoService;
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

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody CadastroAlunoDTO cadastroAlunoDTO){
        try {
            DetalhamentoAlunoDTO alunos = alunoService.salvarAluno(cadastroAlunoDTO);
            return ResponseEntity.status(201).body(alunos);
        }catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity buscarTodos(){
        try {
            List<Aluno> alunos = alunoService.buscarTodosAlunos();
            return ResponseEntity.status(200).body(alunos);
        }catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        try {
            Aluno aluno = alunoService.buscarAlunoPorId(id);
            return ResponseEntity.status(200).body(aluno);
        }catch (NotFoundResourceException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody AtualizarAlunoDTO atualizarAlunoDTO){
        try {
            Aluno aluno = alunoService.atualizarAluno(id, atualizarAlunoDTO);
            return ResponseEntity.status(200).body(aluno);
        }catch (NotFoundResourceException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

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
