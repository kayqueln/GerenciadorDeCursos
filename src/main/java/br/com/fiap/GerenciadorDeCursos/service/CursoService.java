package br.com.fiap.GerenciadorDeCursos.service;

import br.com.fiap.GerenciadorDeCursos.dto.curso.AtualizarCursoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.curso.CadastroCursoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.curso.PropriedadesCursoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.materia.CadastroMateriasDTO;
import br.com.fiap.GerenciadorDeCursos.dto.professor.CadastroProfessorDTO;
import br.com.fiap.GerenciadorDeCursos.exceptions.NotFoundResourceException;
import br.com.fiap.GerenciadorDeCursos.model.*;
import br.com.fiap.GerenciadorDeCursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
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
        Optional<Curso> curso = cursoRepository.findById(id);
        if(curso.isPresent()){
            return curso.get();
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

    public List<PropriedadesCursoDTO> buscarTodosCursosDetalhados() {
        List<Curso> cursos = cursoRepository.findAll();
        List<PropriedadesCursoDTO> cursosDetalhados = new ArrayList<>();
        for (Curso curso : cursos){
            PropriedadesCursoDTO propriedadesCursoDTO = new PropriedadesCursoDTO(curso);
            cursosDetalhados.add(propriedadesCursoDTO);
        }

        return cursosDetalhados;
    }

    public List<Professor> buscarProfessoresCurso(Long cursoId){
        Optional<Curso> curso = cursoRepository.findById(cursoId);
        return curso.get().getProfessores();
    }

    public List<Aluno> buscarAlunosCurso(Long cursoId) {
        List<Aluno> alunos = cursoRepository.findAlunoByCursoId(cursoId);
        System.out.println(alunos);
        return alunos;
    }
}
