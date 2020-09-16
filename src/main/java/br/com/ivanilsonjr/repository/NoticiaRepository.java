package br.com.ivanilsonjr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ivanilsonjr.model.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Long>{

	Noticia findByCodigo(Long codigo);

}
