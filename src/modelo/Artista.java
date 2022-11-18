package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Artista implements Comparable<Artista> {
	public static Scanner scan = new Scanner(System.in);
	private String nome;
	private String nacionalidade;
	private LocalDate dataNascimento;
	private List<Album> albuns = new ArrayList<>();

	public Artista(String nome, String nacionalidade, LocalDate dataNascimento) {
		super();
		this.nome = nome;
		this.nacionalidade = nacionalidade;
		this.dataNascimento = dataNascimento;
	}

	public Artista() {

	}

	// Create
	public static Artista cadastrarArtista(Artista artista) {

		artista = new Artista();

		System.out.println("\nArtista\n ");

		System.out.print("Nome: ");
		artista.setNome(scan.next());

		System.out.print("Nacionalidade: ");
		artista.setNacionalidade(scan.next());

		System.out.println("Data de nascimento ");

		System.out.print("Dia: ");
		int dia = scan.nextInt();

		System.out.print("Mês: ");
		int mes = scan.nextInt();

		System.out.print("Ano: ");
		int ano = scan.nextInt();

		artista.setDataNascimento(LocalDate.of(ano, mes, dia));

		return artista;
	}

	// Update
	public static void alterar(Artista artista) {
		int opcao = 0;
		System.out.print(
				"\nQual campo deseja alterar?\n1 - Nome\n2 - Nascionalidade\n3 - Data de nascimento\nDigite o número: ");
		opcao = scan.nextInt();

		if (opcao == 1) {
			System.out.print("\nDigite o novo nome: ");
			artista.setNome(scan.next());
		} else if (opcao == 2) {
			System.out.print("\nDigite a nova nacionalidade: ");
			artista.setNacionalidade(scan.next());

		} else if (opcao == 3) {

			System.out.println("Digite a nova data de nascimento ");

			System.out.print("Dia: ");
			int dia = scan.nextInt();

			System.out.print("Mês: ");
			int mes = scan.nextInt();

			System.out.print("Ano: ");
			int ano = scan.nextInt();

			artista.setDataNascimento(LocalDate.of(ano, mes, dia));
		}
		else
			System.out.print("\nEntrada inválida");

	}

	public void imprimir() {
		System.out.println(this);
		if (this.albuns.isEmpty() == true)
			System.out.println("Álbuns: ");
		else {
			System.out.println("\nÁlbuns: ");
			imprimirAlbuns();
		}
	}

	private void imprimirAlbuns() {
		for (int i = 0; i < albuns.size(); i++) {
			System.out.println(i + 1 + " - ");
			albuns.get(i).imprimir();
		}
	}

	@Override
	public String toString() {
		return "Nome do artista: " + this.nome + "\nNacionalidade: " + this.nacionalidade + "\nData de nascimento: "
				+ this.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	@Override
	public int compareTo(Artista a) {
		return this.nome.compareTo(a.getNome());
	}

	@Override
	public int hashCode() {
		return Objects.hash(albuns, dataNascimento, nacionalidade, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artista other = (Artista) obj;
		return Objects.equals(albuns, other.albuns) && Objects.equals(dataNascimento, other.dataNascimento)
				&& Objects.equals(nacionalidade, other.nacionalidade) && Objects.equals(nome, other.nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Album> getAlbuns() {
		return albuns;
	}

	public void setAlbuns(List<Album> albuns) {
		this.albuns = albuns;
	}

}
