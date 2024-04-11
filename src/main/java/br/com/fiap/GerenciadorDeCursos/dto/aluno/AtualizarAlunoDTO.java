package br.com.fiap.GerenciadorDeCursos.dto.aluno;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record AtualizarAlunoDTO(
        String nome,
        String email,
        LocalDate dataNascimento
) {
}
