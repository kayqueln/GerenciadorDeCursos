package br.com.fiap.GerenciadorDeCursos.dto.curso;

import br.com.fiap.GerenciadorDeCursos.dto.materia.CadastroMateriasDTO;
import br.com.fiap.GerenciadorDeCursos.dto.professor.CadastroProfessorDTO;
import br.com.fiap.GerenciadorDeCursos.model.Materia;
import br.com.fiap.GerenciadorDeCursos.model.Professor;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record CadastroCursoDTO(
        @NotBlank
        @Size(max = 150)
        String nome,
        @Size(max = 1000)
        String descricao,
        @NotNull
        @FutureOrPresent
        LocalDate dataDeInicio,
        List<CadastroMateriasDTO> materias,
        List<CadastroProfessorDTO> professores
) {
}
