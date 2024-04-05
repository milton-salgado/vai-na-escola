package br.com.vainaweb.vainaescola.service;

import br.com.vainaweb.vainaescola.dto.DadosAluno;
import br.com.vainaweb.vainaescola.model.AlunoModel;
import br.com.vainaweb.vainaescola.model.ColaboradorModel;
import br.com.vainaweb.vainaescola.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    public List<AlunoModel> encontrarTodos() {
        // Método da Repository que faz a query -> SELECT * FROM nome_da_tabela
        return repository.findAll();
    }

    public String cadastrar(DadosAluno dados) {
        var aluno = repository.findByCpf(dados.cpf());

        if (aluno.isPresent())
            return "CPF Já cadastrado.";
        else {
            repository.save(new AlunoModel(dados.nome(), dados.email(), dados.cpf(), dados.curso(), dados.telefone(), dados.endereco())); //INSERT
            return "Cadastro efetuado com sucesso.";
        }
    }
}
