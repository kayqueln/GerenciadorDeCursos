package br.com.fiap.GerenciadorDeCursos.dto.inscricao;


import br.com.fiap.GerenciadorDeCursos.model.Curso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalhamentoCursoDTO {
    private Long idCurso;
    private String nome;
    private String descricao;

    public DetalhamentoCursoDTO(Curso curso) {
        this.idCurso = curso.getId();
        this.nome = curso.getNome();
        this.descricao = curso.getDescricao();
    }
}
