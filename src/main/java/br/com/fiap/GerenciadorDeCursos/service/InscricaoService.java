package br.com.fiap.GerenciadorDeCursos.service;

import br.com.fiap.GerenciadorDeCursos.dto.inscricao.CadastroInscricaoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.inscricao.DetalhamentoInscricaoDTO;
import br.com.fiap.GerenciadorDeCursos.exceptions.CourseFullException;
import br.com.fiap.GerenciadorDeCursos.exceptions.NotFoundResourceException;
import br.com.fiap.GerenciadorDeCursos.model.Aluno;
import br.com.fiap.GerenciadorDeCursos.model.Curso;
import br.com.fiap.GerenciadorDeCursos.model.Inscricao;
import br.com.fiap.GerenciadorDeCursos.repository.AlunoRepository;
import br.com.fiap.GerenciadorDeCursos.repository.CursoRepository;
import br.com.fiap.GerenciadorDeCursos.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Inscricao inscreverUmAluno(CadastroInscricaoDTO cadastroInscricaoDTO) throws NotFoundResourceException, CourseFullException {
        Optional<Aluno> aluno = alunoRepository.findById(cadastroInscricaoDTO.idAluno());
        Optional<Curso> curso = cursoRepository.findById(cadastroInscricaoDTO.idCurso());

        if(!aluno.isPresent()) throw new NotFoundResourceException("Não foi possivel encontrar o aluno associado");
        if(!curso.isPresent()) throw new NotFoundResourceException("Não foi possivel encontrar o curso associado");

        Inscricao inscricao = new Inscricao(curso.get(), aluno.get(), LocalDateTime.now());

        Integer quantidadeInscricoesCurso = inscricaoRepository.getQuantidadeCursos(curso.get().getId());

        if(quantidadeInscricoesCurso >= 30){
            throw new CourseFullException("O limite de inscrições nesse curso foi atingido");
        }

        inscricaoRepository.save(inscricao);
        return inscricao;
    }

    public List<DetalhamentoInscricaoDTO> listarInscricoes(){
        List<Inscricao> inscricoes = inscricaoRepository.findAll();

        List<DetalhamentoInscricaoDTO> inscricoesDTOS = new ArrayList<>();
        for (Inscricao inscricao : inscricoes){
            DetalhamentoInscricaoDTO detalhamentoInscricaoDTO = new DetalhamentoInscricaoDTO(inscricao);
            inscricoesDTOS.add(detalhamentoInscricaoDTO);
        }

        return inscricoesDTOS;
    }
}
