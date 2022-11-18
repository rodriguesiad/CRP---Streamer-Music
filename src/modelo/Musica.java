package modelo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Musica implements Comparable<Musica> {
	public static Scanner scan = new Scanner(System.in);
	private String nome;
	private LocalTime tempoDuracao;
	private List<Artista> artistas = new ArrayList<>();

	// Create
	public static Musica cadastrarMusica(Musica m) {

		int tempoDurMin, tempoDurSeg;

		System.out.println("\nMúsica \n");
		System.out.print("Nome da música: ");
		m.setNome(scan.next());
		System.out.println("Tempo de duração\nMinutos:  ");
		tempoDurMin = scan.nextInt();
		System.out.println("Segundos:  ");
		tempoDurSeg = scan.nextInt();

		m.setTempoDuracao(LocalTime.of(0, tempoDurMin, tempoDurSeg));

		return m;
	}

	public Musica() {
	}

	// Update
	public static void alterar(Musica musica) {
		int opcao = 0;
		System.out
				.print("\nQual campo deseja alterar?\n1 - Nome\n2 - Tempo de duração\nDigite o número: ");
		opcao = scan.nextInt();

		if (opcao == 1) {
			System.out.print("\nDigite o novo nome: ");
			musica.setNome(scan.next());

		} else if (opcao == 2) {
			System.out.println("Digite o novo tempo de duração\nMinutos:  ");
			int tempoDurMin = scan.nextInt();
			System.out.println("Segundos:  ");
			int tempoDurSeg = scan.nextInt();
			musica.setTempoDuracao(LocalTime.of(0, tempoDurMin, tempoDurSeg));

		} else
			System.out.print("\nEntrada inválida");

	}

	@Override
	public int compareTo(Musica m) {
		return this.nome.compareTo(m.getNome());
	}

	public Musica(String nome, LocalTime tempoDuracao) {
		super();
		this.nome = nome;
		this.tempoDuracao = tempoDuracao;
	}

	public void imprimir() {
		System.out.println(this);
		if (this.artistas.isEmpty() == true)
			System.out.println("Artistas: ");
		else {
			System.out.println("Artistas: ");
			imprimirArtistas();
		}
	}

	private void imprimirArtistas() {
		this.artistas.forEach((artista) -> System.out.println(artista.getNome()));
	}

	@Override
	public String toString() {
		return "\nNome: " + this.nome + "\nTempo de duracao: " + this.tempoDuracao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Musica other = (Musica) obj;
		return Objects.equals(artistas, other.artistas) && Objects.equals(nome, other.nome)
				&& Objects.equals(tempoDuracao, other.tempoDuracao);
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

	public List<Artista> getArtistas() {
		return artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}

}
