package br.com.fiap.GerenciadorDeCursos.dto.aluno;

import br.com.fiap.GerenciadorDeCursos.model.Aluno;

import java.time.LocalDate;

public record DetalhamentoAlunoDTO(String nome, String email, LocalDate dataNascimento) {
    public DetalhamentoAlunoDTO(Aluno aluno) {
        this(aluno.getNome(), aluno.getEmail(), aluno.getDataNascimento());
    }
}
