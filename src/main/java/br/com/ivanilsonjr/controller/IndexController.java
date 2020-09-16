package br.com.ivanilsonjr.controller;


import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ivanilsonjr.model.Noticia;
import br.com.ivanilsonjr.repository.AutorRepository;
import br.com.ivanilsonjr.repository.NoticiaRepository;

@Controller
public class IndexController {
	@Autowired
	NoticiaRepository nr;
	@Autowired
	AutorRepository ar;
	
	@GetMapping(value="/")
	public ModelAndView index() {
		List<Noticia> noticia = nr.findAll();
		Collections.sort(noticia);
		ModelAndView mv;
		mv = new ModelAndView("index");
		mv.addObject("noticia", noticia);
		return mv;
	}
}
