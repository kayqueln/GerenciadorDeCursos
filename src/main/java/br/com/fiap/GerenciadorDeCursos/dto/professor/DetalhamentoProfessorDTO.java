package br.com.fiap.GerenciadorDeCursos.dto.professor;

import br.com.fiap.GerenciadorDeCursos.model.Curso;
import br.com.fiap.GerenciadorDeCursos.model.Professor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record DetalhamentoProfessorDTO(Long id, String nome, String especialidade, List<Curso> cursos) {
    public DetalhamentoProfessorDTO(Professor professor) {
        this(professor.getId(), professor.getNome(), professor.getEspecialidade(), professor.getCursos());
    }
}
