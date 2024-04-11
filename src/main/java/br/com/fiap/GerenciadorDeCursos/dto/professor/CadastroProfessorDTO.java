package br.com.fiap.GerenciadorDeCursos.dto.professor;

import br.com.fiap.GerenciadorDeCursos.model.Curso;
import br.com.fiap.GerenciadorDeCursos.model.Professor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CadastroProfessorDTO(
    @NotBlank
    @Size(max = 100)
    String nome,
    @NotBlank
    String especialidade
) {
}
