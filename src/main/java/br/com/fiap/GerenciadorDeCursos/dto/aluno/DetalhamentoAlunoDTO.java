package br.com.fiap.GerenciadorDeCursos.dto.aluno;

import br.com.fiap.GerenciadorDeCursos.model.Aluno;
import br.com.fiap.GerenciadorDeCursos.model.Endereco;

import java.time.LocalDate;

public record DetalhamentoAlunoDTO(String nome, String email, String cpf, LocalDate dataNascimento, Endereco endereco) {
    public DetalhamentoAlunoDTO(Aluno aluno) {
        this(aluno.getNome(), aluno.getEmail(), aluno.getCpf(), aluno.getDataNascimento(), aluno.getEndereco());
    }
}
