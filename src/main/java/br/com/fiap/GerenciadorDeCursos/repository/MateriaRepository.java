package br.com.fiap.GerenciadorDeCursos.repository;

import br.com.fiap.GerenciadorDeCursos.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
}
