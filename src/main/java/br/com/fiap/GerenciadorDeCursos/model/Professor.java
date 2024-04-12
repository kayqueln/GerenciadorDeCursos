package br.com.fiap.GerenciadorDeCursos.model;

import br.com.fiap.GerenciadorDeCursos.dto.professor.AtualizarProfessorDTO;
import br.com.fiap.GerenciadorDeCursos.dto.professor.CadastroProfessorDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Professor extends RepresentationModel<Professor> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotBlank
    private String especialidade;

    @ManyToMany(mappedBy = "professores", cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Curso> cursos;

    public Professor(CadastroProfessorDTO cadastroProfessorDTO) {
        this.nome = cadastroProfessorDTO.nome();
        this.especialidade = cadastroProfessorDTO.especialidade();
    }

    public void atualizarProfessor(AtualizarProfessorDTO atualizarProfessorDTO) {
        if(atualizarProfessorDTO.nome() != null) this.nome = atualizarProfessorDTO.nome();
        if(atualizarProfessorDTO.especialidade() != null) this.especialidade = atualizarProfessorDTO.especialidade();
    }
}
