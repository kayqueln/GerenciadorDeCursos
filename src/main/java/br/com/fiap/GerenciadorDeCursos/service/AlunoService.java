package br.com.fiap.GerenciadorDeCursos.service;


import br.com.caelum.stella.validation.CPFValidator;
import br.com.fiap.GerenciadorDeCursos.dto.aluno.AtualizarAlunoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.aluno.CadastroAlunoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.aluno.DetalhamentoAlunoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.endereco.DetalhamentoCepDTO;
import br.com.fiap.GerenciadorDeCursos.exceptions.NotFoundResourceException;
import br.com.fiap.GerenciadorDeCursos.model.Aluno;
import br.com.fiap.GerenciadorDeCursos.model.Idiomas;
import br.com.fiap.GerenciadorDeCursos.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public DetalhamentoAlunoDTO salvarAluno(CadastroAlunoDTO cadastroAlunoDTO) throws Exception {
        Aluno novoAluno = new Aluno(cadastroAlunoDTO);

        //validadno a idade
        int idade = Period.between(novoAluno.getDataNascimento(), LocalDate.now()).getYears();
        if(idade < 18){
            throw new Exception("O Aluno deve ter mais de 18 anos de idade.");
        }

        //validando o endereço
        if(consultarCEP(novoAluno.getEndereco().getCep()) == null){
            throw new Exception("Digite um endereço válido.");
        }

        //validando CPF
        if(!validarCpf(novoAluno.getCpf())){
            throw new Exception("O CPF informado é inválido.");
        }

        //validando o tempo de formação
        int anosDeFormacao = Period.between(novoAluno.getDataDeConclusaoDaGraduacao(), LocalDate.now()).getYears();
        if(anosDeFormacao < 5){
            throw new Exception("O aluno deve ter concluído a graduação há pelo menos 5 anos.");
        }

        //validando a fluência do aluno
        if(!novoAluno.getIdiomas().contains(Idiomas.PORTUGUES) || !novoAluno.getIdiomas().contains(Idiomas.INGLES)){
            throw new Exception("O aluno deve possuir fluência em inglês e português");
        }

        //validando o tempo de experiência
        int tempoDeExperiencia;
        if(novoAluno.getDataFimDaExperiencia() != null){
            tempoDeExperiencia = Period.between(novoAluno.getDataInicioDaExperiencia(), novoAluno.getDataFimDaExperiencia()).getYears();
        }else{
            tempoDeExperiencia = Period.between(novoAluno.getDataInicioDaExperiencia(), LocalDate.now()).getYears();
        }
        if(tempoDeExperiencia < 10){
            throw new Exception("O aluno deve possuir no mínimo 10 anos de experiência com tecnologia");
        }


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

    public Aluno atualizarAluno(Long id, AtualizarAlunoDTO atualizarAlunoDTO) throws NotFoundResourceException, Exception {
        Optional<Aluno> aluno = alunoRepository.findById(id);

        if(!aluno.isPresent()){
            throw new NotFoundResourceException("Não foi possível encontrar esse aluno");
        }

        //validadno a idade
        int idade = Period.between(aluno.get().getDataNascimento(), LocalDate.now()).getYears();
        if(idade < 18){
            throw new Exception("O Aluno deve ter mais de 18 anos de idade.");
        }

        //validando CPF
        if(!validarCpf(aluno.get().getCpf())){
            throw new Exception("O CPF informado é inválido.");
        }

        //validando o tempo de formação
        int anosDeFormacao = Period.between(aluno.get().getDataDeConclusaoDaGraduacao(), LocalDate.now()).getYears();
        if(anosDeFormacao < 5){
            throw new Exception("O aluno deve ter concluído a graduação há pelo menos 5 anos.");
        }

        //validando a fluência do aluno
        if(!aluno.get().getIdiomas().contains(Idiomas.PORTUGUES) || !aluno.get().getIdiomas().contains(Idiomas.INGLES)){
            throw new Exception("O aluno deve possuir fluência em inglês e português");
        }

        //validando o tempo de experiência
        int tempoDeExperiencia;
        if(aluno.get().getDataFimDaExperiencia() != null){
            tempoDeExperiencia = Period.between(aluno.get().getDataInicioDaExperiencia(), aluno.get().getDataFimDaExperiencia()).getYears();
        }else{
            tempoDeExperiencia = Period.between(aluno.get().getDataInicioDaExperiencia(), LocalDate.now()).getYears();
        }
        if(tempoDeExperiencia < 10){
            throw new Exception("O aluno deve possuir no mínimo 10 anos de experiência com tecnologia");
        }

        aluno.get().atualizarAluno(atualizarAlunoDTO);
        alunoRepository.save(aluno.get());
        return aluno.get();
    }

    public void deletarAluno(Long id) throws NotFoundResourceException {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if(aluno.isPresent()){
            alunoRepository.deleteById(id);
        }else{
            throw new NotFoundResourceException("Não foi possível encontrar esse aluno");
        }
    }

    private DetalhamentoCepDTO consultarCEP(String cep){
        String uri = "https://viacep.com.br/ws/" + cep + "/json/";
        try {
            RestTemplate restTemplate = new RestTemplate();
            DetalhamentoCepDTO result = restTemplate.getForObject(uri, DetalhamentoCepDTO.class);
            return result;
        } catch (HttpClientErrorException.BadRequest e) {
            return null;
        }
    }

    public static boolean validarCpf(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        try{
            cpfValidator.assertValid(cpf);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
