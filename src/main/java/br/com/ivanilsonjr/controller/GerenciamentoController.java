package br.com.ivanilsonjr.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ivanilsonjr.model.Noticia;
import br.com.ivanilsonjr.repository.NoticiaRepository;

@Controller
public class GerenciamentoController {
	@Autowired
	NoticiaRepository nr;
	
	@GetMapping(value="/gerenciamento")
	public String paginaPrincipal() {
		return "gerenciamento/index";
	}

	@GetMapping(value="/gerenciamento/noticias")
	public ModelAndView gerenciarNoticias() {
		List<Noticia> noticia = nr.findAll();
		Collections.sort(noticia);
		ModelAndView mv;
		mv = new ModelAndView("/gerenciamento/noticias");
		mv.addObject("noticia", noticia);
		return mv;
	}

}
