package controller;

import java.util.ArrayList;
import java.util.List;

import modelo.Album;

public class AlbumController {
	public List<Album> albuns = new ArrayList<Album>();

	public boolean inserir(Album album) {
		if (albuns.add(album))
			return true;
		return false;
	}

	public boolean alterar(Album albumAntigo, Album albumNovo) {
		int indice = albuns.indexOf(albumAntigo);
		if (albuns.contains(albumAntigo)) {
			albuns.remove(indice);
			albuns.add(albumNovo);
			return true;
		}
		return false;
	}

	public boolean delete(Album album) {
		if (albuns.remove(album))
			return true;
		return false;
	}

	public void read() {
		ordenarPorNome();
		this.albuns.forEach(album -> album.imprimir());
	}

	public void encontrarNome(String nome){
		albuns.stream().filter( album -> album.getNome().equals(nome)).forEach(album -> album.imprimir());
	}

	public void ordenarPorNome() {
		albuns.sort((o1, o2) -> o1.getNome().compareToIgnoreCase(o2.getNome()));
	}
	
	public void ordenarPorAno() {
		albuns.stream().sorted((o1, o2) -> o1.getAno().compareTo(o2.getAno())).forEach(album -> album.imprimir());
	}
	
	public void ordenarPorDuracao() {
		albuns.stream().sorted((o1, o2) -> o1.getDuracaoTotal().compareTo(o2.getDuracaoTotal())).forEach(album -> album.imprimir());
	}

	public List<Album> getAlbuns() {
		return albuns;
	}
}
