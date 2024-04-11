package br.com.fiap.GerenciadorDeCursos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_endereco;
    @NotBlank
    private int cep;
    @NotBlank
    private String rua;
    @NotBlank
    private int numero;
    private String complemento;
}
