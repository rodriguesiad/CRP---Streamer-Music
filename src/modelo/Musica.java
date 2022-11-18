package modelo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Musica {
	private String nome;
	private LocalTime tempoDuracao;
	private List<Genero> generos = new ArrayList<>();
	private List<Artista> artistas = new ArrayList<>();
	
	
	
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
	public int hashCode() {
		return Objects.hash(artistas, generos, nome, tempoDuracao);
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
		return Objects.equals(artistas, other.artistas) && Objects.equals(generos, other.generos)
				&& Objects.equals(nome, other.nome) && Objects.equals(tempoDuracao, other.tempoDuracao);
	}
	
	public Musica(String nome, LocalTime tempoDuracao, List<Genero> generos, List<Artista> artistas) {
		super();
		this.nome = nome;
		this.tempoDuracao = tempoDuracao;
		this.generos = generos;
		this.artistas = artistas;
	}
	
	@Override
	public String toString() {
		return "\nMusica \n Nome: " + nome + "\n Tempo de duracao: " + tempoDuracao;
	}
	
	public void imprimir() {
		System.out.println(this);
		
		if (this.generos.isEmpty() == true)
			System.out.println("Generos: ");
		else {
			System.out.println("Generos: ");
			imprimirGeneros();
		}
		
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
	
	private void imprimirGeneros() {
		this.generos.forEach((genero) -> System.out.println(genero + " "));
	}

	
	
	
}
