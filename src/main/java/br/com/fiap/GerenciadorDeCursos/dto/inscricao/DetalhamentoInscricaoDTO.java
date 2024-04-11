package br.com.fiap.GerenciadorDeCursos.dto.inscricao;

import br.com.fiap.GerenciadorDeCursos.model.Aluno;
import br.com.fiap.GerenciadorDeCursos.model.Curso;
import br.com.fiap.GerenciadorDeCursos.model.Inscricao;

import java.time.LocalDateTime;
import java.util.Currency;

public record DetalhamentoInscricaoDTO(Long id, Aluno aluno, DetalhamentoCursoModel curso, LocalDateTime dataInscricao) {
    public DetalhamentoInscricaoDTO(Inscricao inscricao) {
        this(inscricao.getId(), inscricao.getAluno(), new DetalhamentoCursoModel(inscricao.getCurso()), inscricao.getDataInscricao());
    }
}
