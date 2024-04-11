package br.com.fiap.GerenciadorDeCursos.dto.aluno;

import br.com.fiap.GerenciadorDeCursos.model.Inscricao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record CadastroAlunoDTO(
    @NotBlank
    @Size(max = 100)
    String nome,
    @NotBlank
    @Email
    String email,
    @NotNull
    @Past
    LocalDate dataNascimento
) {
}
