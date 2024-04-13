package br.com.fiap.GerenciadorDeCursos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table
public class Endereco {
    @Id
    @Column(name = "endereco_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String cep;
    @NotBlank
    private String rua;
    @NotNull
    private int numero;
    private String complemento;

    @OneToOne
    @JoinColumn(name = "aluno_id")
    @JsonIgnore
    private Aluno aluno;
}
