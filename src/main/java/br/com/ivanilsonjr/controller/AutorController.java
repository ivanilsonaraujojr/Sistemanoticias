package br.com.ivanilsonjr.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.ivanilsonjr.model.Autor;
import br.com.ivanilsonjr.repository.AutorRepository;

@Controller
public class AutorController {
	@Autowired
	AutorRepository ar;
	
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
			autor.setDataCadastro(new Date());
			ar.save(autor);
			attributes.addFlashAttribute("mensagem", "Autor cadastrado com sucesso!");
				return "redirect:/cadastrarautor";
		}
	}
}
