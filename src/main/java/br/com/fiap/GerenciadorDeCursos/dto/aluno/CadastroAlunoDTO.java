package br.com.fiap.GerenciadorDeCursos.dto.aluno;

import br.com.fiap.GerenciadorDeCursos.model.Endereco;
import br.com.fiap.GerenciadorDeCursos.model.Idiomas;
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
    String cpf,
    @NotNull
    @Past
    LocalDate dataNascimento,
    @NotNull
    LocalDate dataDeConclusaoDaGraduacao,
    @NotNull
    LocalDate dataInicioDaExperiencia,
    LocalDate dataFimDaExperiencia,
    @NotNull
    List<Idiomas> idiomas,
    @NotNull
    Endereco endereco
) {
}
