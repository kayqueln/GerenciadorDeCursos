package br.com.fiap.GerenciadorDeCursos.repository;

import br.com.fiap.GerenciadorDeCursos.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("""
        SELECT DISTINCT p
        FROM Professor p
        INNER JOIN p.cursos c
        WHERE c.id = :cursoId
    """)
    List<Professor> findAllByCurso(Long cursoId);
}
