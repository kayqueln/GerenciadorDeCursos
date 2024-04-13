package br.com.fiap.GerenciadorDeCursos.model;

import br.com.fiap.GerenciadorDeCursos.dto.aluno.AtualizarAlunoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.aluno.CadastroAlunoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Aluno extends RepresentationModel<Aluno> {
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

    @NotBlank
    private String cpf;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Inscricao> inscricoes;

    public Aluno(CadastroAlunoDTO cadastroAlunoDTO) {
        this.nome = cadastroAlunoDTO.nome();
        this.email = cadastroAlunoDTO.email();
        this.cpf = cadastroAlunoDTO.cpf();
        this.dataNascimento = cadastroAlunoDTO.dataNascimento();
        this.endereco = cadastroAlunoDTO.endereco();
    }

    public void atualizarAluno(AtualizarAlunoDTO atualizarAlunoDTO) {
        if(atualizarAlunoDTO.nome() != null) this.nome = atualizarAlunoDTO.nome();
        if(atualizarAlunoDTO.email() != null) this.email = atualizarAlunoDTO.email();
        if(atualizarAlunoDTO.dataNascimento() != null) this.dataNascimento = atualizarAlunoDTO.dataNascimento();
    }
}
