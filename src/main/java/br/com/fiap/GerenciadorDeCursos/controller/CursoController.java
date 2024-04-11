package br.com.fiap.GerenciadorDeCursos.controller;


import br.com.fiap.GerenciadorDeCursos.dto.curso.AtualizarCursoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.curso.CadastroCursoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.curso.DetalhamentoCursoDTO;
import br.com.fiap.GerenciadorDeCursos.exceptions.NotFoundResourceException;
import br.com.fiap.GerenciadorDeCursos.model.Curso;
import br.com.fiap.GerenciadorDeCursos.service.CursoService;
import jakarta.persistence.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoService CursoService;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody CadastroCursoDTO cadastroCursoDTO){
        try {
            Curso curso = CursoService.salvarCurso(cadastroCursoDTO);
            return ResponseEntity.status(201).body(new DetalhamentoCursoDTO(curso));
        }catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity buscarTodos(){
        try {
            List<Curso> Cursos = CursoService.buscarTodosCursos();
            return ResponseEntity.status(200).body(Cursos);
        }catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        try {
            Curso Curso = CursoService.buscarCursoPorId(id);
            return ResponseEntity.status(200).body(Curso);
        }catch (NotFoundResourceException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody AtualizarCursoDTO atualizarCursoDTO){
        try {
            Curso Curso = CursoService.atualizarCurso(id, atualizarCursoDTO);
            return ResponseEntity.status(200).body(Curso);
        }catch (NotFoundResourceException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    //Este endpoint não está funcionando por conta do "CascadeType.PERSIST" que foi solicitado na documentação.
    //Em caso de substituição para o "CascadeType.ALL" o endpoint funciona normalmente
    @DeleteMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id){
        try {
            CursoService.deletarCurso(id);
            return ResponseEntity.status(204).build();
        }catch (NotFoundResourceException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    
}
