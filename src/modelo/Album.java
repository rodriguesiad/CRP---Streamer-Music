package modelo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Album implements Comparable<Album> {
	public static Scanner scan = new Scanner(System.in);
	private String nome;
	private int ano;
	private LocalTime duracaoTotal = LocalTime.of(0, 0, 0);
	private List<Artista> artistas = new ArrayList<>();
	private List<Musica> musicas = new ArrayList<>();

	// Create
	public static Album cadastrarAlbum(Album album) {
		System.out.println("\nÁlbum \n");
		System.out.print("Nome: ");
		album.setNome(scan.next());
		System.out.print("Ano: ");
		album.setAno(scan.nextInt());

		return album;
	}

	public Album(String nome, int ano, List<Artista> artistas, List<Musica> musicas) {
		super();
		this.nome = nome;
		this.ano = ano;
		this.artistas = artistas;
		this.musicas = musicas;
	}

	public Album() {
		// default
	}

	public Album(String nome, int ano) {
		this.nome = nome;
		this.ano = ano;
	}

	// Update
	public static void alterar(Album album) {
		int opcao = 0;
		System.out.print("\nQual campo deseja alterar?\n1 - Nome\n2 - Ano\nDigite o número: ");
		opcao = scan.nextInt();

		if (opcao == 1) {
			System.out.print("\nDigite o novo nome: ");
			album.setNome(scan.next());

		} else if (opcao == 2) {
			System.out.print("\nDigite o novo ano: ");
			album.setAno(scan.nextInt());
		}
		else
			System.out.print("\nEntrada inválida");

	}

	@Override
	public int compareTo(Album a) {
		return this.nome.compareTo(a.getNome());
	}

	public void imprimir() {
		System.out.println(this);
		Collections.sort(this.artistas);
		if (this.artistas.isEmpty() == true)
			System.out.println("Artista: ");
		else {
			System.out.println("Artistas: ");
			this.imprimirArtistas();
		}
	}

	public void imprimirCompleto() {
		imprimir();
		Collections.sort(this.musicas);
		if (this.musicas.isEmpty() == true)
			System.out.println("Músicas: ");
		else {
			System.out.println("Músicas: ");
			imprimirMusicas();
		}
	}

	public void imprimirMusicas() {
		this.musicas.forEach((musica) -> musica.imprimir());
	}

	public void imprimirArtistas() {
		this.artistas.forEach((artista) -> System.out.println(artista.getNome()));
	}

	@Override
	public String toString() {
		return "Album " + nome + "\nAno: " + ano + "\nTempo de Duração: " + duracaoTotal + "";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public LocalTime getDuracaoTotal() {
		return duracaoTotal;
	}

	public void setDuracaoTotal(LocalTime duracaoTotal) {
		this.duracaoTotal = this.duracaoTotal.plusHours(duracaoTotal.getHour()).plusMinutes(duracaoTotal.getMinute())
				.plusSeconds(duracaoTotal.getSecond());
	}

	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

}
