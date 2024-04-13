package br.com.fiap.GerenciadorDeCursos.dto.aluno;

import br.com.fiap.GerenciadorDeCursos.model.Idiomas;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record AtualizarAlunoDTO(
        String nome,
        String email,
        String cpf,
        LocalDate dataNascimento,
        LocalDate dataDeConclusaoDaGraduacao,
        LocalDate dataInicioDaExperiencia,
        LocalDate dataFimDaExperiencia,
        List<Idiomas> idiomas
) {
}
