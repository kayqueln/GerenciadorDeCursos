package br.com.fiap.GerenciadorDeCursos.repository;

import br.com.fiap.GerenciadorDeCursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
