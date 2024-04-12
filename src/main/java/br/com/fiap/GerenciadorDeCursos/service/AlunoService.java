package br.com.fiap.GerenciadorDeCursos.service;

import br.com.fiap.GerenciadorDeCursos.dto.aluno.AtualizarAlunoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.aluno.CadastroAlunoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.aluno.DetalhamentoAlunoDTO;
import br.com.fiap.GerenciadorDeCursos.exceptions.NotFoundResourceException;
import br.com.fiap.GerenciadorDeCursos.model.Aluno;
import br.com.fiap.GerenciadorDeCursos.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public DetalhamentoAlunoDTO salvarAluno(CadastroAlunoDTO cadastroAlunoDTO){
        Aluno novoAluno = new Aluno(cadastroAlunoDTO);
        alunoRepository.save(novoAluno);

        return new DetalhamentoAlunoDTO(novoAluno);
    }

    public List<Aluno> buscarTodosAlunos(){
        return alunoRepository.findAll();
    }

    public Aluno buscarAlunoPorId(Long id) throws NotFoundResourceException {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if(aluno.isPresent()){
            return aluno.get();
        }else{
            throw new NotFoundResourceException("Não foi possível encontrar esse aluno");
        }
    }

    public Aluno atualizarAluno(Long id, AtualizarAlunoDTO atualizarAlunoDTO) throws NotFoundResourceException {
        Optional<Aluno> aluno = alunoRepository.findById(id);

        if(aluno.isPresent()){
            aluno.get().atualizarAluno(atualizarAlunoDTO);
            alunoRepository.save(aluno.get());
            return aluno.get();
        }else{
            throw new NotFoundResourceException("Não foi possível encontrar esse aluno");
        }
    }

    public void deletarAluno(Long id) throws NotFoundResourceException {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if(aluno.isPresent()){
            alunoRepository.deleteById(id);
        }else{
            throw new NotFoundResourceException("Não foi possível encontrar esse aluno");
        }
    }

}
