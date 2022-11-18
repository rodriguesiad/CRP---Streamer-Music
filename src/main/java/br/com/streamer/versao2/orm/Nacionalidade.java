package br.com.streamer.versao2.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nacionalidades")
public class Nacionalidade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@Column(name = "sigla_pais")
	private String siglaPais;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSiglaPais() {
		return siglaPais;
	}
	public void setSiglaPais(String siglaPais) {
		this.siglaPais = siglaPais;
	}

	@Override
	public String toString() {
		return "Nacionalidade \n  Id: " + id + "\n  Nome: " + nome + "\n  Sigla do País: " + siglaPais;
	}
}
