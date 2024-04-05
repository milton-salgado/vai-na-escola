package br.com.vainaweb.vainaescola.controller;

import br.com.vainaweb.vainaescola.dto.DadosAluno;
import br.com.vainaweb.vainaescola.dto.DadosAlunoAtualizados;
import br.com.vainaweb.vainaescola.model.AlunoModel;
import br.com.vainaweb.vainaescola.repository.AlunoRepository;
import br.com.vainaweb.vainaescola.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController /* Indica para o Spring que é uma controller */
@RequestMapping("/aluno")/* Indica o end-point do projeto */
public class AlunoController {
    // Classe responsavel por receber requisições e retornar respostas
    @Autowired // Injeção de dependência da service
    private AlunoService service;

    @Autowired // Injeção de dependência da service
    private AlunoRepository repository;

    @GetMapping
    public List<AlunoModel> listarTodosAlunos() {
        return service.encontrarTodos();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping // Verbo HTTP POST
    public ResponseEntity<String> cadastrarAluno(@RequestBody DadosAluno dados) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dados));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> listarPorId(@PathVariable Long id) {
        Optional<AlunoModel> alunoOptional = repository.findById(id);

        return alunoOptional.map(aluno ->
                        ResponseEntity.status(HttpStatus.OK).body(aluno.toString()))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAlunoAtualizados dados) {
        var aluno = repository.getReferenceById(id);
        aluno.atualizarInfo(dados);
        repository.save(aluno);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return "Deletado";
    }
}