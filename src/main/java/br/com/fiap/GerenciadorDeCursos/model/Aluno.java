package br.com.fiap.GerenciadorDeCursos.model;

import br.com.fiap.GerenciadorDeCursos.dto.aluno.AtualizarAlunoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.aluno.CadastroAlunoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Aluno {
    @Id
    @Column(name = "aluno_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Inscricao> inscricoes;

    public Aluno(CadastroAlunoDTO cadastroAlunoDTO) {
        this.nome = cadastroAlunoDTO.nome();
        this.email = cadastroAlunoDTO.email();
        this.dataNascimento = cadastroAlunoDTO.dataNascimento();
    }

    public void atualizarAluno(AtualizarAlunoDTO atualizarAlunoDTO) {
        if(atualizarAlunoDTO.nome() != null) this.nome = atualizarAlunoDTO.nome();
        if(atualizarAlunoDTO.email() != null) this.email = atualizarAlunoDTO.email();
        if(atualizarAlunoDTO.dataNascimento() != null) this.dataNascimento = atualizarAlunoDTO.dataNascimento();
    }
}
