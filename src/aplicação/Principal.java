package aplicação;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import controller.AlbumController;
import controller.ArtistaController;
import controller.MusicaController;
import modelo.Genero; 
import modelo.Musica; 
import modelo.Album; 
import modelo.Artista; 
import modelo.Nacionalidade; 
import modelo.TipoArtista;

public class Principal {
	public static int fat(int n) {
//		if (n>1)
//			return n*fat(n-1);
//		else
//			return 1;
		return n>1? n*fat(n-1): 1;
	}
	public static void main(String[] args) {
		
		Genero mpb = new Genero("MPB - Música Popular Brasileira");
		Genero pop = new Genero("POP");
		Genero sertanejo = new Genero("Sertanejo");
		
		Nacionalidade brasileiro = new Nacionalidade("Brasileiro", "BR");
		Nacionalidade estadunidense = new Nacionalidade("Estadunidense", "EUA");
		
		ArtistaController artistas = new ArtistaController();
		MusicaController musicas = new MusicaController();
		AlbumController albuns = new AlbumController();
		
		artistas.inserir(new Artista("Lana Del Rey", LocalDate.of(1999, 10, 10), "lanadelrey@gmail.com", estadunidense, new ArrayList<TipoArtista>(Arrays.asList (new TipoArtista[]{TipoArtista.INTERPRETE, TipoArtista.COMPOSITOR}))));
		artistas.inserir(new Artista("Manoel Gomes", LocalDate.of(1979, 9, 21), "manoelgomes@gmail.com", brasileiro, new ArrayList<TipoArtista>(Arrays.asList (new TipoArtista[]{TipoArtista.INTERPRETE, TipoArtista.LETRISTA, TipoArtista.COMPOSITOR}))));
		
		musicas.inserir(new Musica("Video Games", LocalTime.of(0, 2, 59), new ArrayList<Genero>(Arrays.asList (new Genero[]{pop})), new ArrayList<Artista>(Arrays.asList (new Artista[]{artistas.artistas.get(0)}))));
		musicas.inserir(new Musica("Caneta Azul", LocalTime.of(0, 3, 3), new ArrayList<Genero>(Arrays.asList (new Genero[]{sertanejo, mpb})), new ArrayList<Artista>(Arrays.asList (new Artista[]{artistas.artistas.get(1)}))));
		
		albuns.inserir(new Album("Hits", 2020, new ArrayList<Artista>(Arrays.asList (new Artista[]{artistas.artistas.get(0), artistas.artistas.get(1)})), new ArrayList<Musica>(Arrays.asList (new Musica[]{musicas.musicas.get(0), musicas.musicas.get(1)}))));
		
		
		
		System.out.println("-------- ALTERAÇÕES --------");
		artistas.alterar(artistas.artistas.get(0), new Artista("Lana Del Rey", LocalDate.of(1999, 9, 10), "lanadelrey@gmail.com", estadunidense, new ArrayList<TipoArtista>(Arrays.asList (new TipoArtista[]{TipoArtista.INTERPRETE, TipoArtista.LETRISTA}))));
		musicas.alterar(musicas.musicas.get(0), new Musica("Video Games", LocalTime.of(0, 3, 17), new ArrayList<Genero>(Arrays.asList (new Genero[]{pop})), new ArrayList<Artista>(Arrays.asList (new Artista[]{artistas.artistas.get(0)}))));
		albuns.alterar(albuns.albuns.get(0), new Album("Hits", 2022, new ArrayList<Artista>(Arrays.asList (new Artista[]{artistas.artistas.get(0), artistas.artistas.get(1)})), new ArrayList<Musica>(Arrays.asList (new Musica[]{musicas.musicas.get(0), musicas.musicas.get(1)}))));
	
//		System.out.println("-------- ARTISTAS --------");
//		artistas.read();
//		System.out.println("-------- ÁLBUNS --------");
//		albuns.read();
//		System.out.println("-------- MÚSICAS --------");
//		musicas.read();	
		
		System.out.println(artistas.artistas.get(0));
		System.out.println(artistas.artistas.get(1));
	}
}
