package br.com.fiap.GerenciadorDeCursos.dto.professor;

import br.com.fiap.GerenciadorDeCursos.model.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record AtualizarProfessorDTO(
        String nome,
        String especialidade
) {
}
