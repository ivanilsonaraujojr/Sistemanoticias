package br.com.ivanilsonjr.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "noticias")
public class Noticia implements Comparable<Noticia>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Size(min=8, message="Titulo invalido")
	private String tituloNoticia;
	
	@NotEmpty(message="Insira um link de uma imagem")
	@URL(message="Verifique o link")
	private String linkImagem;
	
	@Size(min=50, message="Corpo da notícia invalido")
	private String corpoNoticia;
	
	private Date dataPostagem;
	
	@NotNull(message="Autor não selecionado")
	@OneToOne
	private Autor autor;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long id) {
		this.codigo = id;
	}

	public String getTituloNoticia() {
		return tituloNoticia;
	}

	public void setTituloNoticia(String tituloNoticia) {
		this.tituloNoticia = tituloNoticia;
	}

	public String getCorpoNoticia() {
		return corpoNoticia;
	}

	public void setCorpoNoticia(String corpoNoticia) {
		this.corpoNoticia = corpoNoticia;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getLinkImagem() {
		return linkImagem;
	}

	public void setLinkImagem(String linkImagem) {
		this.linkImagem = linkImagem;
	}

	@Override
	public int compareTo(Noticia o) {
		if (o.getDataPostagem().getTime() > this.dataPostagem.getTime()) {
			return 1;
		} else {
			return -1;
		}
	}

}
