package br.com.fiap.GerenciadorDeCursos.dto.aluno;

import br.com.fiap.GerenciadorDeCursos.model.Aluno;
import br.com.fiap.GerenciadorDeCursos.model.Endereco;
import br.com.fiap.GerenciadorDeCursos.model.Idiomas;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record DetalhamentoAlunoDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        LocalDate dataNascimento,
        LocalDate dataDeConclusaoDaGraduacao,
        LocalDate dataInicioDaExperiencia,
        LocalDate dataFimDaExperiencia,
        List<Idiomas> idiomas,
        Endereco endereco
){
    public DetalhamentoAlunoDTO(Aluno aluno) {
        this(aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getCpf(),
                aluno.getDataNascimento(),
                aluno.getDataDeConclusaoDaGraduacao(),
                aluno.getDataInicioDaExperiencia(),
                aluno.getDataFimDaExperiencia(),
                aluno.getIdiomas(),
                aluno.getEndereco());
    }
}
