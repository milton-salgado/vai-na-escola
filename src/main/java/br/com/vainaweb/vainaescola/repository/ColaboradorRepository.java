package br.com.vainaweb.vainaescola.repository;

import br.com.vainaweb.vainaescola.model.ColaboradorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColaboradorRepository extends JpaRepository<ColaboradorModel, Long>{
    Optional<ColaboradorModel> findByCpf(String cpf);
}
