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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((corpoNoticia == null) ? 0 : corpoNoticia.hashCode());
		result = prime * result + ((linkImagem == null) ? 0 : linkImagem.hashCode());
		result = prime * result + ((tituloNoticia == null) ? 0 : tituloNoticia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Noticia other = (Noticia) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (corpoNoticia == null) {
			if (other.corpoNoticia != null)
				return false;
		} else if (!corpoNoticia.equals(other.corpoNoticia))
			return false;
		if (linkImagem == null) {
			if (other.linkImagem != null)
				return false;
		} else if (!linkImagem.equals(other.linkImagem))
			return false;
		if (tituloNoticia == null) {
			if (other.tituloNoticia != null)
				return false;
		} else if (!tituloNoticia.equals(other.tituloNoticia))
			return false;
		return true;
	}

}
