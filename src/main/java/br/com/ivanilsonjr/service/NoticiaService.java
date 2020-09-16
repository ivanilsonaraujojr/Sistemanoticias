package br.com.ivanilsonjr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public interface NoticiaService {

	List<String> validarCampos(BindingResult result);
}
