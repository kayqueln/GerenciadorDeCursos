package br.com.fiap.GerenciadorDeCursos.dto.curso;

import br.com.fiap.GerenciadorDeCursos.model.Curso;
import br.com.fiap.GerenciadorDeCursos.model.Materia;
import br.com.fiap.GerenciadorDeCursos.model.Professor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record DetalhamentoCursoDTO(Long id, String nome, String descricao, List<Materia> materias, List<Professor> professores) {
    public DetalhamentoCursoDTO(Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getDescricao(), curso.getMaterias(), curso.getProfessores());
    }
}
