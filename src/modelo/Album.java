package modelo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Album {
	private String nome;
	private Integer ano;
	private LocalTime duracaoTotal = LocalTime.of(0, 0, 0);
	private List<Artista> artistas = new ArrayList<>();
	private List<Musica> musicas = new ArrayList<>();


	public Album(String nome, Integer ano, List<Artista> artistas, List<Musica> musicas) {
		super();
		this.nome = nome;
		this.ano = ano;
		this.artistas = artistas;
		this.musicas = musicas;
		this.setDuracaoTotal();
	}


	public void imprimir() {
		System.out.println(this);
		this.imprimirArtistas();
	}

	public void imprimirComMusicas() {
		imprimir();
		imprimirMusicas();
	}

	public void imprimirMusicas() {
		this.musicas.forEach((musica) -> musica.imprimir());
	}

	public void imprimirArtistas() {
		this.artistas.forEach((artista) -> System.out.println(artista.getNome()));
	}

	@Override
	public String toString() {
		return "Album\n Nome: " + nome + "\n Ano: " + ano + "\n Tempo de Duração: " + duracaoTotal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public LocalTime getDuracaoTotal() {
		return duracaoTotal;
	}

	public void setDuracaoTotal() {
        if (!this.musicas.isEmpty()) {
            this.musicas.forEach((musica) -> {
                this.duracaoTotal = this.duracaoTotal.plusHours(musica.getTempoDuracao().getHour())
                        .plusMinutes(musica.getTempoDuracao().getMinute())
                        .plusSeconds(musica.getTempoDuracao().getSecond());
            });
        }
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
