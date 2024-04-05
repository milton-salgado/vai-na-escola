package br.com.vainaweb.vainaescola.model;

import br.com.vainaweb.vainaescola.dto.DadosAlunoAtualizados;
import br.com.vainaweb.vainaescola.dto.EnderecoDTO;
import br.com.vainaweb.vainaescola.enums.Curso;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Table(name = "tb_colaboradores")
@Entity // A classe é uma entidade
@Getter
@Setter
@NoArgsConstructor // Indica para que o Spring implemente um construtor completo
@AllArgsConstructor // Indica para que o Spring implemente um construtor vazio
public class AlunoModel {
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

    private Curso curso;

    @Pattern(regexp = "^\\(?(?:[14689][1-9]|2[12478]|3[1234578]|5[1345]|7[134579])\\)? ?(?:[2-8]|9[0-9])[0-9]{3}\\-?[0-9]{4}$")
    private String telefone;

    @Embedded // Incorpora a classe na entidade (OS ATRIBUTOS DESSA CLASSE SERÃO PARTE DA MINHA TABELA)
    private Endereco endereco;

    public AlunoModel(String nome, String email, String cpf, Curso curso, String telefone, EnderecoDTO endereco) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.curso = Curso.valueOf(curso.toString());
        this.telefone = telefone;
        this.endereco = new Endereco(endereco.cep(), endereco.logradouro(), endereco.bairro(), endereco.cidade(),
                endereco.uf(), endereco.complemento(), endereco.numero());
    }

    //|------------------------------------------- MÉTODOS --------------------------------|
    public void atualizarInfo(@Valid DadosAlunoAtualizados dados) {
        this.nome = dados.nome() != null ? dados.nome() : this.nome;
        this.email = dados.telefone() != null ? dados.telefone() : this.telefone;
        this.endereco = dados.endereco() != null ? dados.endereco() : this.endereco;
        this.curso = dados.curso() != null ? dados.curso() : this.curso;
    }

    @Override
    public String toString() {
        return
                ", nome = '" + nome + '\'' +
                        ", email = '" + email + '\'' +
                        ", curso = " + curso;
    }
}
