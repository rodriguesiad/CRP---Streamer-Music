package br.com.streamer.versao2.orm;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.transaction.Transactional;

@Entity
@Transactional
public class Musica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private LocalTime tempoDuracao;
	@ManyToMany
	private List<Genero> generos = new ArrayList<>();
	@ManyToMany
	private List<Artista> artistas = new ArrayList<>();
	
	public void visualizar() {
		System.out.println(this);	
		System.out.println("Artistas da Música");
		this.artistas.forEach((artista) -> System.out.println("  "+artista.getNome()));
		System.out.println("Gêneros da Música");
		this.generos.forEach((genero) -> System.out.println("  "+genero.getNome()));
	}
	
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
	public LocalTime getTempoDuracao() {
		return tempoDuracao;
	}
	public void setTempoDuracao(LocalTime tempoDuracao) {
		this.tempoDuracao = tempoDuracao;
	}
	public List<Genero> getGeneros() {
		return generos;
	}
	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}
	
	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}

	@Override
	public String toString() {
		return "Musica\n  Id: " + id + "\n  Nome: " + nome + "\n  Tempo de Duracao: " + tempoDuracao;
	}
}
