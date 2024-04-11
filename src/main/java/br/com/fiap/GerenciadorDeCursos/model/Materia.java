package br.com.fiap.GerenciadorDeCursos.model;

import br.com.fiap.GerenciadorDeCursos.dto.materia.CadastroMateriasDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @Size(max = 500)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "curso_id")
    @JsonIgnore
    private Curso curso;

    public Materia(CadastroMateriasDTO cadastroMateriasDTO) {
        this.nome = cadastroMateriasDTO.nome();
        this.descricao = cadastroMateriasDTO.descricao();
        this.curso = cadastroMateriasDTO.curso();
    }
}
