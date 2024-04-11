package br.com.fiap.GerenciadorDeCursos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
public class Inscricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @NotNull
    @PastOrPresent
    private LocalDateTime dataInscricao;

    public Inscricao(Curso curso, Aluno aluno, LocalDateTime dataInscricao) {
        this.curso = curso;
        this.aluno = aluno;
        this.dataInscricao = dataInscricao;
    }
}
