 package br.com.ivanilsonjr.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ivanilsonjr.model.Autor;
import br.com.ivanilsonjr.model.Noticia;
import br.com.ivanilsonjr.repository.AutorRepository;
import br.com.ivanilsonjr.repository.NoticiaRepository;

@Controller
public class NoticiaController {
	@Autowired
	NoticiaRepository nr;
	@Autowired
	AutorRepository ar;
	
	
	@GetMapping(value="/noticia/{codigo}")
	public ModelAndView mostrarNoticia(@PathVariable("codigo") Long codigo) {
		Noticia noticia = nr.findByCodigo(codigo);
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
		model.addAttribute("autores", ar.findAll());
		if (result.hasErrors()) {
			return "noticia/cadastrarnoticia";
		}else if(nr.findAll().contains(noticia)) {
			attributes.addFlashAttribute("mensagemErro", "Esta notícia ja existe!");
			return "redirect:/cadastrarnoticia";
		}else {
		noticia.setDataPostagem(new Date());
		nr.save(noticia);
		attributes.addFlashAttribute("mensagem", "Notícia adicionada com sucesso!");
			return "redirect:/cadastrarnoticia";
		}
		
	}
	
	@RequestMapping(value="/gerenciamento/deletar/{codigo}")
	public String deletarNoticia(@PathVariable Long codigo,RedirectAttributes attributes) {
		Noticia noticia = nr.findByCodigo(codigo);
		if(noticia != null) {
			nr.delete(noticia);
			attributes.addFlashAttribute("mensagem", "Notícia deletada com sucesso!");
			return "redirect:/gerenciamento/noticias";
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping(value="/gerenciamento/editar/{codigo}")
	public ModelAndView editarNoticia(@PathVariable Long codigo) {
		Noticia noticia = nr.findByCodigo(codigo);
		ModelAndView mv;
		if(noticia != null) {
			mv = new ModelAndView("gerenciamento/editarnoticia");
			List<Autor> listaAutores = ar.findAll();
			if(listaAutores.contains(noticia.getAutor())) {
				listaAutores.remove(noticia.getAutor());
			}
			mv.addObject("autores", listaAutores);
			mv.addObject("noticia", noticia);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return mv;
	}
	
	@PostMapping("/gerenciamento/editar/{codigo}")
	public String formEdicaoNoticia(@Valid Noticia noticia, BindingResult result, RedirectAttributes attributes, Model model) {
		List<Autor> listaAutores = ar.findAll();
		if(listaAutores.contains(noticia.getAutor())) {
			listaAutores.remove(noticia.getAutor());
		}
		model.addAttribute("autores", listaAutores);
		if (result.hasErrors()) {
			return "gerenciamento/editarnoticia";
		}else if(nr.findAll().contains(noticia)) {
			attributes.addFlashAttribute("mensagemErro", "Erro: Nenhum dado atualizado!");
			return "redirect:/gerenciamento/editar/" + noticia.getCodigo();
		}else {
		noticia.setDataPostagem(new Date());
		nr.save(noticia);
		attributes.addFlashAttribute("mensagem", "Notícia atualizada com sucesso!");
			return "redirect:/gerenciamento/editar/" + noticia.getCodigo();
		}
		
	}
	
}
