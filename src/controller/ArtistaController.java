package controller;

import java.util.ArrayList;
import java.util.List;

import modelo.Album;
import modelo.Artista;
import modelo.Nacionalidade;

public class ArtistaController {

	public List<Artista> artistas = new ArrayList<>();

	public void inserir(Artista novoArtista) {
		this.artistas.add(novoArtista);
	}

	public void read() { 
		ordenar();
		this.artistas.forEach((artista) -> artista.imprimir());
	}
	
	public void ordenar() {
		this.artistas.sort((o1, o2) -> o1.getNome().compareToIgnoreCase(o2.getNome())); //Ordenando por ordem alfabética
	}
	
	public boolean alterar(Artista artista, Artista novoArtista) {
		if (this.artistas.contains(artista)) {
			this.artistas.add(this.artistas.indexOf(artista), novoArtista);
			this.artistas.remove(this.artistas.indexOf(artista));
			return true;
		} else
			return false;

	}
	
	public boolean inserirAlbum(Artista artista, Album album) {
		if (this.artistas.contains(artista)) {
			this.artistas.get(this.artistas.indexOf(artista)).getAlbuns().add(album);
			return true;
		} else
			return false;
	}

	public boolean remover(Artista artista) {
		if (this.artistas.contains(artista)) {
			this.artistas.remove(artista);
			return true;
		} else
			return false;
	}

	public void encontrarNome(String nome) {
		this.artistas.stream().filter(artista -> artista.getNome().equals(nome)).forEach(artista -> artista.imprimir());
	}

	public void encontrarNacionalidade(Nacionalidade nacionalidade) {
		this.artistas.stream().sorted((o1, o2) -> o1.getNome().compareToIgnoreCase(o2.getNome()))
				.filter(artista -> artista.getNacionalidade().equals(nacionalidade))
				.forEach(artista -> artista.imprimir());
	}

}
