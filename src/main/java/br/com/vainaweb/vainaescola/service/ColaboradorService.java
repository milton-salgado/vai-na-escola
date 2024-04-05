package br.com.vainaweb.vainaescola.service;

import br.com.vainaweb.vainaescola.dto.DadosColaborador;
import br.com.vainaweb.vainaescola.model.ColaboradorModel;
import br.com.vainaweb.vainaescola.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorService {
    @Autowired
    private ColaboradorRepository repository;

    public List<ColaboradorModel> encontrarTodos() {
        // Método da Repository que faz a query -> SELECT * FROM nome_da_tabela
        return repository.findAll();
    }

    public String cadastrar(DadosColaborador dados) {
        var colaborador = repository.findByCpf(dados.cpf());

        if (colaborador.isPresent())
            return "CPF Já cadastrado.";
        else {
            repository.save(new ColaboradorModel(dados.nome(), dados.email(), dados.cpf(), dados.cargo(), dados.endereco())); //INSERT
            return "Cadastro efetuado com sucesso.";
        }
    }
}
