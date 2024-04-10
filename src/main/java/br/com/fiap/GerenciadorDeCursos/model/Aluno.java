package br.com.fiap.GerenciadorDeCursos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo_aluno;
    private String nome;
    private String email;
    private String CPF;
    private Date dataDeNascimento;
    @ManyToMany
    private List<Inscricao> inscricaos;
    private Endereco endereco;
}
