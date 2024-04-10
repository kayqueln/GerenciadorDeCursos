package br.com.fiap.GerenciadorDeCursos.model;

import lombok.Data;

import java.util.Date;

@Data
public class Inscricao {
    private int id_inscricao;
    private int curso_id;
    private int aluno_id;
    private Date dataDaInscricao;
}
