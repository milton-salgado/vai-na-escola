package br.com.vainaweb.vainaescola.controller;

import br.com.vainaweb.vainaescola.dto.DadosColaboradorAtualizados;
import br.com.vainaweb.vainaescola.dto.DadosColaborador;
import br.com.vainaweb.vainaescola.repository.ColaboradorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.vainaweb.vainaescola.model.ColaboradorModel;
import br.com.vainaweb.vainaescola.service.ColaboradorService;

import java.util.List;
import java.util.Optional;

@RestController /* Indica para o Spring que é uma controller */
@RequestMapping("/colaborador")/* Indica o end-point do projeto */
// Classe responsavel por receber requisições e retornar respostas
public class ColaboradorController {
    @Autowired // Injeção de dependência da service
    private ColaboradorService service;

    @Autowired // Injeção de dependência da service
    private ColaboradorRepository repository;

    @GetMapping
    public List<ColaboradorModel> listarTodosColaboradores() {
        return service.encontrarTodos();
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping // Verbo HTTP POST
    public ResponseEntity<String> cadastrarColaborador(@RequestBody DadosColaborador dados) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dados));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorModel> listarPorId(@PathVariable Long id) {
        Optional<ColaboradorModel> colaboradorOptional = repository.findById(id);

        return colaboradorOptional.map(colaborador ->
                        ResponseEntity.status(HttpStatus.OK).body(colaborador))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody @Valid DadosColaboradorAtualizados dados) {
        var colaborador = repository.getReferenceById(id);
        colaborador.atualizarInfo(dados);
        repository.save(colaborador);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        repository.deleteById(id);
        return "Deletado";
    }
}