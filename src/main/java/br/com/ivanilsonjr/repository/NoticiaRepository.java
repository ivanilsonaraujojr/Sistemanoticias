package br.com.ivanilsonjr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ivanilsonjr.model.Autor;
import br.com.ivanilsonjr.model.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Long>{

	Noticia findByCodigo(Long codigo);
	List<Noticia> findByAutor(Autor autor);
}
