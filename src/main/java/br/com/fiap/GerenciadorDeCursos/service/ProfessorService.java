package br.com.fiap.GerenciadorDeCursos.service;

import br.com.fiap.GerenciadorDeCursos.dto.professor.AtualizarProfessorDTO;
import br.com.fiap.GerenciadorDeCursos.dto.professor.CadastroProfessorDTO;
import br.com.fiap.GerenciadorDeCursos.dto.professor.DetalhamentoProfessorDTO;
import br.com.fiap.GerenciadorDeCursos.exceptions.NotFoundResourceException;
import br.com.fiap.GerenciadorDeCursos.model.Professor;
import br.com.fiap.GerenciadorDeCursos.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public DetalhamentoProfessorDTO salvarProfessor(CadastroProfessorDTO cadastroProfessorDTO){
        Professor novoProfessor = new Professor(cadastroProfessorDTO);
        professorRepository.save(novoProfessor);

        return new DetalhamentoProfessorDTO(novoProfessor);
    }

    public List<Professor> buscarTodosProfessors(){
        return professorRepository.findAll();
    }

    public Professor buscarProfessorPorId(Long id) throws NotFoundResourceException {
        Optional<Professor> Professor = professorRepository.findById(id);
        if(Professor.isPresent()){
            return Professor.get();
        }else{
            throw new NotFoundResourceException("Não foi possível encontrar esse Professor");
        }
    }

    public Professor atualizarProfessor(Long id, AtualizarProfessorDTO atualizarProfessorDTO) throws NotFoundResourceException {
        Optional<Professor> Professor = professorRepository.findById(id);

        if(Professor.isPresent()){
            Professor.get().atualizarProfessor(atualizarProfessorDTO);
            professorRepository.save(Professor.get());
            return Professor.get();
        }else{
            throw new NotFoundResourceException("Não foi possível encontrar esse Professor");
        }
    }

    public void deletarProfessor(Long id) throws NotFoundResourceException {
        Optional<Professor> Professor = professorRepository.findById(id);
        if(Professor.isPresent()){
            professorRepository.deleteById(id);
        }else{
            throw new NotFoundResourceException("Não foi possível encontrar esse Professor");
        }
    }

}
