package br.com.fiap.GerenciadorDeCursos.controller;


import br.com.fiap.GerenciadorDeCursos.dto.professor.AtualizarProfessorDTO;
import br.com.fiap.GerenciadorDeCursos.dto.professor.CadastroProfessorDTO;
import br.com.fiap.GerenciadorDeCursos.dto.professor.DetalhamentoProfessorDTO;
import br.com.fiap.GerenciadorDeCursos.exceptions.NotFoundResourceException;
import br.com.fiap.GerenciadorDeCursos.model.Professor;
import br.com.fiap.GerenciadorDeCursos.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private ProfessorService ProfessorService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody CadastroProfessorDTO cadastroProfessorDTO){
        try {
            DetalhamentoProfessorDTO Professors = ProfessorService.salvarProfessor(cadastroProfessorDTO);
            return ResponseEntity.status(201).body(Professors);
        }catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity buscarTodos(){
        try {
            List<Professor> Professors = ProfessorService.buscarTodosProfessors();
            return ResponseEntity.status(200).body(Professors);
        }catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        try {
            Professor Professor = ProfessorService.buscarProfessorPorId(id);
            return ResponseEntity.status(200).body(Professor);
        }catch (NotFoundResourceException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody AtualizarProfessorDTO atualizarProfessorDTO){
        try {
            Professor Professor = ProfessorService.atualizarProfessor(id, atualizarProfessorDTO);
            return ResponseEntity.status(200).body(Professor);
        }catch (NotFoundResourceException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id){
        try {
            ProfessorService.deletarProfessor(id);
            return ResponseEntity.status(204).build();
        }catch (NotFoundResourceException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
