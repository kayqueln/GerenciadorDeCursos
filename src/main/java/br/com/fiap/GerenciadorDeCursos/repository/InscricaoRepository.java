package br.com.fiap.GerenciadorDeCursos.repository;

import br.com.fiap.GerenciadorDeCursos.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    @Query("""
            SELECT count(i.id)
            FROM Inscricao i
            WHERE i.curso.id = :cursoId
            """)
    Integer getQuantidadeCursos(Long cursoId);
}
