package br.com.fiap.GerenciadorDeCursos.dto.inscricao;

import br.com.fiap.GerenciadorDeCursos.model.Aluno;
import br.com.fiap.GerenciadorDeCursos.model.Curso;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public record CadastroInscricaoDTO(
        Long idAluno,
        Long idCurso
) {
}
