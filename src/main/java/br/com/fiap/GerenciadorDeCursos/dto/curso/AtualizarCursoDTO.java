package br.com.fiap.GerenciadorDeCursos.dto.curso;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AtualizarCursoDTO(
        @Size(max = 150)
        String nome,
        @Size(max = 1000)
        String descricao,
        @FutureOrPresent
        LocalDate dataDeInicio
) {
}
