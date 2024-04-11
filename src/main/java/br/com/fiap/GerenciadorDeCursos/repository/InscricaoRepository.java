package br.com.fiap.GerenciadorDeCursos.repository;

import br.com.fiap.GerenciadorDeCursos.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
}
