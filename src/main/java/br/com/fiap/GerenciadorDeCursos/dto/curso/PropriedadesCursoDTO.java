package br.com.fiap.GerenciadorDeCursos.dto.curso;

import br.com.fiap.GerenciadorDeCursos.model.Curso;

import java.time.LocalDate;

public record PropriedadesCursoDTO(Long id, String nome, String descricao, LocalDate dataDeInicio) {

    public PropriedadesCursoDTO(Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getDescricao(), curso.getDataDeinicio());
    }
}
