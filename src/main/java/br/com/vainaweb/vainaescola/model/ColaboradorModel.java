package br.com.vainaweb.vainaescola.model;

import br.com.vainaweb.vainaescola.dto.DadosColaboradorAtualizados;
import br.com.vainaweb.vainaescola.dto.EnderecoDTO;
import br.com.vainaweb.vainaescola.enums.Cargo;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Table(name = "tb_colaboradores")
@Entity // A classe é uma entidade
@Getter
@Setter
@NoArgsConstructor // Indica para que o Spring implemente um construtor completo
@AllArgsConstructor // Indica para que o Spring implemente um construtor vazio
public class ColaboradorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Chave primária

    private String nome;

    @Column(unique = true) //Formata as colunas
    @Email // Valida como e-mail
    private String email;

    @Column(unique = true) //Formata as colunas
    @CPF // Valida como cpf
    private String cpf;

    private Cargo cargo;

    @Embedded // Incorpora a classe na entidade (OS ATRIBUTOS DESSA CLASSE SERÃO PARTE DA MINHA TABELA)
    private Endereco endereco;

    // |------------------------------------------CONSTRUTORES--------------------------------------|
    public ColaboradorModel(String nome, String email, String cpf, Cargo cargo, @Valid EnderecoDTO endereco) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.cargo = Cargo.valueOf(cargo.toString());
        this.endereco = new Endereco(endereco.cep(), endereco.logradouro(), endereco.bairro(), endereco.cidade(),
                endereco.uf(), endereco.complemento(), endereco.numero());
    }

    //|------------------------------------------- MÉTODOS --------------------------------|
    public void atualizarInfo(@Valid DadosColaboradorAtualizados dados) {
        this.nome = dados.nome() != null ? dados.nome() : this.nome;
        this.email = dados.email() != null ? dados.email() : this.email;
    }

    @Override
    public String toString() {
        return
                "id = " + id +
                        ", nome = '" + nome + '\'' +
                        ", email = '" + email + '\'' +
                        ", cargo = " + cargo;
    }
}
