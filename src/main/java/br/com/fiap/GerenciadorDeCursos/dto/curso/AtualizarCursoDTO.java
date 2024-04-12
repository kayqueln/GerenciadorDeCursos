package br.com.fiap.GerenciadorDeCursos.dto.curso;

import br.com.fiap.GerenciadorDeCursos.model.Materia;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record AtualizarCursoDTO(
        @Size(max = 150)
        String nome,
        @Size(max = 1000)
        String descricao,
        @FutureOrPresent
        LocalDateTime dataDeInicio
) {
}
