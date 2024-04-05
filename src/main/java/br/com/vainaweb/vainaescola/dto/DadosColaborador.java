package br.com.vainaweb.vainaescola.dto;

import br.com.vainaweb.vainaescola.enums.Cargo;
import br.com.vainaweb.vainaescola.model.Endereco;

public record DadosColaborador(String nome, String email, String cpf, Cargo cargo, EnderecoDTO endereco) {

}