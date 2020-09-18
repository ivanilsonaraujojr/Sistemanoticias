package br.com.ivanilsonjr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ivanilsonjr.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{
	Autor findByCpf(String cpf);
	List<Autor> findAllByIndVisivelTrue();
}
