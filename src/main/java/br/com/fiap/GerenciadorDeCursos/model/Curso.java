package br.com.fiap.GerenciadorDeCursos.model;

import br.com.fiap.GerenciadorDeCursos.dto.curso.AtualizarCursoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.curso.CadastroCursoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Curso extends RepresentationModel<Curso> {
    @Id
    @Column(name = "curso_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    private String nome;

    @Size(max = 1000)
    private String descricao;

    @FutureOrPresent
    private LocalDateTime dataDeinicio;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Materia> materias;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Professor> professores;

    @OneToMany
    @JsonIgnore
    private List<Inscricao> inscricoes;

    public Curso(CadastroCursoDTO cadastroCursoDTO) {
        this.nome = cadastroCursoDTO.nome();
        this.descricao = cadastroCursoDTO.descricao();
        this.dataDeinicio = cadastroCursoDTO.dataDeInicio();
    }

    public void atualizarCurso(AtualizarCursoDTO atualizarCursoDTO) {
        if(atualizarCursoDTO.nome() != null) this.nome = atualizarCursoDTO.nome();
        if(atualizarCursoDTO.descricao() != null) this.descricao = atualizarCursoDTO.descricao();
        if(atualizarCursoDTO.dataDeInicio() != null) this.dataDeinicio = atualizarCursoDTO.dataDeInicio();
    }
}
