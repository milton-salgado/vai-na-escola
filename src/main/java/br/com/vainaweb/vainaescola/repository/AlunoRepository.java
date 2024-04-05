package br.com.vainaweb.vainaescola.repository;

import br.com.vainaweb.vainaescola.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<AlunoModel, Long> {
    Optional<AlunoModel> findByCpf(String cpf);
}
