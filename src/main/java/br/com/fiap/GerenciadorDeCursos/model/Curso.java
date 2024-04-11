package br.com.fiap.GerenciadorDeCursos.model;

import br.com.fiap.GerenciadorDeCursos.dto.curso.AtualizarCursoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.curso.CadastroCursoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Curso {
    @Id
    @Column(name = "curso_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    private String nome;

    @Size(max = 1000)
    private String descricao;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Materia> materias;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Professor> professores;

    public Curso(CadastroCursoDTO cadastroCursoDTO) {
        this.nome = cadastroCursoDTO.nome();
        this.descricao = cadastroCursoDTO.descricao();
    }

    public void atualizarCurso(AtualizarCursoDTO atualizarCursoDTO) {
        if(atualizarCursoDTO.nome() != null) this.nome = atualizarCursoDTO.nome();
        if(atualizarCursoDTO.descricao() != null) this.descricao = atualizarCursoDTO.descricao();
    }
}
