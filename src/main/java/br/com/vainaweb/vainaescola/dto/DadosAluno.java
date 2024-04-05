package br.com.vainaweb.vainaescola.dto;

import br.com.vainaweb.vainaescola.enums.Curso;

public record DadosAluno(String nome, String email, String cpf, Curso curso, String telefone,
                         EnderecoDTO endereco) {
}
