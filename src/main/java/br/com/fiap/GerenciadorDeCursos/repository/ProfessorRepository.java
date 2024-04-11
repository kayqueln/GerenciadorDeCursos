package br.com.fiap.GerenciadorDeCursos.repository;

import br.com.fiap.GerenciadorDeCursos.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
