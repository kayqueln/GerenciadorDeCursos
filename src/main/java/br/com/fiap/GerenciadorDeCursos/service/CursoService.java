package br.com.fiap.GerenciadorDeCursos.service;

import br.com.fiap.GerenciadorDeCursos.dto.curso.AtualizarCursoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.curso.CadastroCursoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.materia.CadastroMateriasDTO;
import br.com.fiap.GerenciadorDeCursos.dto.professor.CadastroProfessorDTO;
import br.com.fiap.GerenciadorDeCursos.exceptions.NotFoundResourceException;
import br.com.fiap.GerenciadorDeCursos.model.Curso;
import br.com.fiap.GerenciadorDeCursos.model.Materia;
import br.com.fiap.GerenciadorDeCursos.model.Professor;
import br.com.fiap.GerenciadorDeCursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public Curso salvarCurso(CadastroCursoDTO cadastroCursoDTO){
        Curso novoCurso = new Curso(cadastroCursoDTO);

        List<Materia> materias = new ArrayList<>();
        for (CadastroMateriasDTO cadastroMateriasDTO : cadastroCursoDTO.materias()){
            Materia materia = new Materia();
            materia.setNome(cadastroMateriasDTO.nome());
            materia.setDescricao(cadastroMateriasDTO.descricao());
            materia.setCurso(novoCurso);
            materias.add(materia);
        }
        novoCurso.setMaterias(materias);

        List<Professor> professores = new ArrayList<>();
        for (CadastroProfessorDTO cadastroProfessorDTO : cadastroCursoDTO.professores()){
            Professor professor = new Professor();
            professor.setNome(cadastroProfessorDTO.nome());
            professor.setEspecialidade(cadastroProfessorDTO.especialidade());
            professores.add(professor);
        }
        novoCurso.setProfessores(professores);

        cursoRepository.save(novoCurso);
        return novoCurso;
    }

    public List<Curso> buscarTodosCursos(){
        return cursoRepository.findAll();
    }

    public Curso buscarCursoPorId(Long id) throws NotFoundResourceException {
        Optional<Curso> Curso = cursoRepository.findById(id);
        if(Curso.isPresent()){
            return Curso.get();
        }else{
            throw new NotFoundResourceException("Não foi possível encontrar esse Curso");
        }
    }

    public Curso atualizarCurso(Long id, AtualizarCursoDTO atualizarCursoDTO) throws NotFoundResourceException {
        Optional<Curso> Curso = cursoRepository.findById(id);

        if(Curso.isPresent()){
            Curso.get().atualizarCurso(atualizarCursoDTO);
            cursoRepository.save(Curso.get());
            return Curso.get();
        }else{
            throw new NotFoundResourceException("Não foi possível encontrar esse Curso");
        }
    }

    public void deletarCurso(Long id) throws NotFoundResourceException {
        Optional<Curso> Curso = cursoRepository.findById(id);
        if(Curso.isPresent()){
            cursoRepository.deleteById(id);
        }else{
            throw new NotFoundResourceException("Não foi possível encontrar esse Curso");
        }
    }
}
