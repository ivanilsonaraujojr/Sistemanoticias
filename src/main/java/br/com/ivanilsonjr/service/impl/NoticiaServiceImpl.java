package br.com.ivanilsonjr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import br.com.ivanilsonjr.service.NoticiaService;

public class NoticiaServiceImpl implements NoticiaService{

	@Override
	public List<String> validarCampos(BindingResult result) {
		System.out.println("aaa");
		List<String> errosEncontrados = new ArrayList<>();
		String erro = "O campo: ";
		for (FieldError resulterrors : result.getFieldErrors()) {
			switch(resulterrors.getField()) {
			case "tituloNoticia": erro.concat("titulo da notícia "); break;
			case "linkImagem": erro.concat("link da imagem "); break;
			case "corpoNoticia": erro.concat("Corpo da notícia "); break;
			}
			erro.concat(resulterrors.getDefaultMessage());
			errosEncontrados.add(erro);
		}
		return errosEncontrados;
	}

}
