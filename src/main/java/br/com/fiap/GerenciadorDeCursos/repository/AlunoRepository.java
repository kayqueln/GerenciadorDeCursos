package br.com.fiap.GerenciadorDeCursos.repository;

import br.com.fiap.GerenciadorDeCursos.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
