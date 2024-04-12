package br.com.fiap.GerenciadorDeCursos.dto.curso;

import br.com.fiap.GerenciadorDeCursos.model.Curso;

public record PropriedadesCursoDTO(Long id, String nome, String descricao) {

    public PropriedadesCursoDTO(Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getDescricao());
    }
}
