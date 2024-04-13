package br.com.fiap.GerenciadorDeCursos.service;

import br.com.fiap.GerenciadorDeCursos.dto.aluno.AtualizarAlunoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.aluno.CadastroAlunoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.aluno.DetalhamentoAlunoDTO;
import br.com.fiap.GerenciadorDeCursos.dto.endereco.CepResponse;
import br.com.fiap.GerenciadorDeCursos.exceptions.NotFoundResourceException;
import br.com.fiap.GerenciadorDeCursos.model.Aluno;
import br.com.fiap.GerenciadorDeCursos.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            throw new Exception("O Aluno deve ter mais de 18 anos de idade");
        }

        //validando o endereço
        if(consultarCEP(novoAluno.getEndereco().getCep()) == null){
            throw new Exception("Digite um endereço válido");
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

    private CepResponse consultarCEP(String cep){
        String uri = "https://viacep.com.br/ws/" + cep + "/json/";
        try {
            RestTemplate restTemplate = new RestTemplate();
            CepResponse result = restTemplate.getForObject(uri, CepResponse.class);
            return result;
        } catch (HttpClientErrorException.BadRequest e) {
            return null;
        }

    }
}
