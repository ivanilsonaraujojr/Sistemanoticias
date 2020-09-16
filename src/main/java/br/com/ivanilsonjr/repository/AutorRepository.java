package br.com.ivanilsonjr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.ivanilsonjr.model.Autor;

@EnableJpaRepositories
public interface AutorRepository extends JpaRepository<Autor, Long>{
	Autor findByCpf(String cpf);
}
