package br.com.ivanilsonjr.controller;

import java.util.List;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ivanilsonjr.model.Autor;
import br.com.ivanilsonjr.model.Noticia;
import br.com.ivanilsonjr.repository.AutorRepository;
import br.com.ivanilsonjr.repository.NoticiaRepository;

@Controller
public class AutorController {
	@Autowired
	AutorRepository ar;
	@Autowired 
	NoticiaRepository nr;
	
	@GetMapping("/cadastrarautor")
	public String cadastrarAutor(Autor autor){
		return "autor/cadastrarautor";
	}
	
	@PostMapping("/cadastrarautor")
	 public String form(@Valid Autor autor, BindingResult result, RedirectAttributes attributes ) {
		if (result.hasErrors()) {
				return "autor/cadastrarautor";
		}
		if(ar.findByCpf(autor.getCpf()) != null) {
			attributes.addFlashAttribute("mensagemErro", "CPF ja cadastrado na base!");
			return "redirect:/cadastrarautor";
		}else {
			autor.setIndVisivel(true);
			autor.setDataCadastro(new Date());
			ar.save(autor);
			attributes.addFlashAttribute("mensagem", "Autor cadastrado com sucesso!");
				return "redirect:/cadastrarautor";
		}
	}
	
	@RequestMapping(value="/gerenciamento/deletarautor/{id}")
	public String deletarAutor(@PathVariable Long id,RedirectAttributes attributes) {
		Optional<Autor> autorF =  ar.findById(id);
		Autor autor = autorF.get();
		List<Noticia> autorNoticias = nr.findByAutor(autor);
		if(autor != null) {
			if(autorNoticias != null) {
				for (Noticia noticia : autorNoticias) {
					noticia.setAutor(ar.findByCpf("00000000000"));
				}
			}
			ar.delete(autor);
			attributes.addFlashAttribute("mensagem", "Autor deletado com sucesso!");
			return "redirect:/gerenciamento/autores";
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
}
