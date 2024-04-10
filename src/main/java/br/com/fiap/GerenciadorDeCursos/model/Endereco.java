package br.com.fiap.GerenciadorDeCursos.model;

import lombok.Data;

@Data
public class Endereco {
    private int codigo_endereco;
    private String cep;
    private String rua;
    private int numero;
    private String complemento;
}
