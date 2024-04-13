package br.com.fiap.GerenciadorDeCursos.service;

import br.com.fiap.GerenciadorDeCursos.exceptions.NotFoundResourceException;
import br.com.fiap.GerenciadorDeCursos.model.Endereco;
import br.com.fiap.GerenciadorDeCursos.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;


    public List<Endereco> buscarTodosEnderecos(){
        List<Endereco> enderecos = enderecoRepository.findAll();
        return enderecos;
    }

    public Endereco buscarPorId(Long id) throws NotFoundResourceException {
        Optional<Endereco> endereco = enderecoRepository.findById(id);

        if (!endereco.isPresent()) throw new NotFoundResourceException("Erro ao buscar endereço");

        return endereco.get();
    }

}
