package br.com.fiap.GerenciadorDeCursos.model;

import lombok.Data;

@Data
public class Professor {
    private int codigo_professor;
    private String nome;
    private String especialidade;
    private String materias;
}
