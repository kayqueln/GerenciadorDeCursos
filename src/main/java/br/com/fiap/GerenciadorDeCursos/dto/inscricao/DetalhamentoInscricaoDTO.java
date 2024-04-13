package br.com.fiap.GerenciadorDeCursos.dto.inscricao;

import br.com.fiap.GerenciadorDeCursos.model.Aluno;
import br.com.fiap.GerenciadorDeCursos.model.Inscricao;

import java.time.LocalDateTime;

public record DetalhamentoInscricaoDTO(Long id, Aluno aluno, DetalhamentoCursoDTO curso, LocalDateTime dataInscricao) {
    public DetalhamentoInscricaoDTO(Inscricao inscricao) {
        this(inscricao.getId(), inscricao.getAluno(), new DetalhamentoCursoDTO(inscricao.getCurso()), inscricao.getDataInscricao());
    }
}
