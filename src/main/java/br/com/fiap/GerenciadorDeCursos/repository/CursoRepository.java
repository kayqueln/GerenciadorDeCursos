package br.com.fiap.GerenciadorDeCursos.repository;

import br.com.fiap.GerenciadorDeCursos.model.Aluno;
import br.com.fiap.GerenciadorDeCursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("""
        SELECT i.aluno
        FROM Inscricao i 
        WHERE i.curso.id = :cursoId
        """)
    List<Aluno> findAlunoByCursoId(Long cursoId);

}
