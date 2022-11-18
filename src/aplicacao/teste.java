package aplicacao;

import java.time.LocalDate;
import java.time.LocalTime;

import modelo.Album;
import modelo.Artista;
import modelo.Musica;

public class teste {
	public static void main(String[] args) {
		Album album = new Album();
		Artista artista = new Artista("Nome", "br", LocalDate.of(2010, 11, 12));
		Musica musica = new Musica("Nome", LocalTime.of(0, 2, 30));
		Artista artista2 = new Artista("Nome2", "br", LocalDate.of(2010, 11, 12));
		Musica musica2 = new Musica("Nome2", LocalTime.of(0, 2, 30));
		
		album.setNome("CryBaby");
		album.setAno(2016);
		album.getArtistas().add(artista);
		album.getArtistas().add(artista2);
		album.getMusicas().add(musica);
		album.getMusicas().add(musica2);
		
		album.imprimir();
		
		
		musica.getArtistas().add(artista);
		musica.getArtistas().add(artista2);
		
		musica.imprimir();
		
		
		artista.getAlbuns().add(album);
		
		artista.imprimir();
		
		
		
		
	}
}
