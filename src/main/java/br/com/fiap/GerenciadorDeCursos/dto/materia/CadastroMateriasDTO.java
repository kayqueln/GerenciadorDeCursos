package br.com.fiap.GerenciadorDeCursos.dto.materia;

import br.com.fiap.GerenciadorDeCursos.model.Curso;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastroMateriasDTO(
        @NotBlank
        @Size(max = 100)
        String nome,
        @Size(max = 500)
        String descricao,
        Curso curso
) {
}
