package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Artista {
	private String nome;
	private LocalDate dataNascimento;
	private String email;
	private Nacionalidade nacionalidade;
	private List<TipoArtista> tipo = new ArrayList<>();
	private List<Album> albuns = new ArrayList<>();

	public Artista(String nome, LocalDate dataNascimento, String email, Nacionalidade nacionalidade, List<TipoArtista> tipo) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.nacionalidade = nacionalidade;
		this.tipo = tipo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Nacionalidade getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(Nacionalidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	public List<TipoArtista> getTipo() {
		return tipo;
	}
	
	public void setTipo(List<TipoArtista> tipo) {
		this.tipo = tipo;
	}
	
	public List<Album> getAlbuns() {
		return albuns;
	}
	
	public void setAlbuns(List<Album> albuns) {
		this.albuns = albuns;
	}

	@Override
	public int hashCode() {
		return Objects.hash(albuns, dataNascimento, email, nacionalidade, nome, tipo);
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
				&& Objects.equals(email, other.email) && Objects.equals(nacionalidade, other.nacionalidade)
				&& Objects.equals(nome, other.nome) && Objects.equals(tipo, other.tipo);
	}

	@Override
	public String toString() {
		return "Artista \n Nome: " + nome + "\n Data de nascimento: " + dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n Email: " + email + "\n "
				+ nacionalidade;
	}
	
	public void imprimir() {
		System.out.println(this);
		imprimirTipos();

		if (this.albuns.isEmpty() == true)
			System.out.println("\nÁlbuns: ");
		else {
			System.out.println("\nÁlbuns: ");
			imprimirAlbuns();
		}
		System.out.println("\n------------------------");
	}

	private void imprimirAlbuns() {
		for (int i = 0; i < albuns.size(); i++) {
			System.out.println(i + 1 + " - ");
			albuns.get(i).imprimir();
		}
	}
	
	private void imprimirTipos() {
		this.tipo.forEach((tipo) -> System.out.println((tipo.getValor()+" ")));
	}

	
	
	

}
