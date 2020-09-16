 package br.com.ivanilsonjr.controller;

import java.util.Date;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ivanilsonjr.model.Noticia;
import br.com.ivanilsonjr.repository.AutorRepository;
import br.com.ivanilsonjr.repository.NoticiaRepository;

@Controller
public class NoticiaController {
	@Autowired
	NoticiaRepository nr;
	@Autowired
	AutorRepository ar;
	
	
	@GetMapping(value="/noticia/{id}")
	public ModelAndView mostrarNoticia(@PathVariable("id") Long id) {
		Noticia noticia = nr.findByCodigo(id);
		ModelAndView mv;
		if(noticia != null) {
			mv = new ModelAndView("noticia/noticia");
			mv.addObject("noticia", noticia);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return mv;
	}
	
	@GetMapping("/cadastrarnoticia")
	public String cadastrarNoticia(Noticia noticia, Model model){
		model.addAttribute("autores", ar.findAll());
		return "noticia/cadastrarnoticia";
	}
	
	@PostMapping("/cadastrarnoticia")
	public String form(@Valid Noticia noticia, BindingResult result, RedirectAttributes attributes, Model model) {
		if (result.hasErrors()) {
		model.addAttribute("autores", ar.findAll());
			return "noticia/cadastrarnoticia";
		}else {
		noticia.setDataPostagem(new Date());
		nr.save(noticia);
		attributes.addFlashAttribute("mensagem", "Notícia adicionada com sucesso!");
			return "redirect:/cadastrarnoticia";
		}
	}
	
}
