package br.com.vainaweb.vainaescola.dto;

import br.com.vainaweb.vainaescola.enums.Curso;
import br.com.vainaweb.vainaescola.model.Endereco;

public record DadosAlunoAtualizados(String nome, String telefone, Endereco endereco, Curso curso) {
}
