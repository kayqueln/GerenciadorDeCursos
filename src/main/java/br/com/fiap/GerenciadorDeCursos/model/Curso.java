package br.com.fiap.GerenciadorDeCursos.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Curso {
    private int codigo_curso;
    private String nomeDoCurso;
    private String descricao;
    private Date dataDeInicio;
    private List<Aluno> alunos;
}
