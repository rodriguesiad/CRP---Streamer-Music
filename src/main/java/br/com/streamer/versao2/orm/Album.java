package br.com.streamer.versao2.orm;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Table(name = "album")
@Transactional
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Integer ano;
	private LocalTime duracaoTotal = LocalTime.of(0, 0, 0);
	@ManyToMany
	private List<Artista> artistas = new ArrayList<>();
	@ManyToMany
	private List<Musica> musicas = new ArrayList<>();
	
	public void visualizar() {
		System.out.println(this);
		this.musicas.forEach((musica) -> musica.visualizar());
		System.out.println("Artistas do Álbum");
		this.artistas.forEach((artista) -> System.out.println("  "+artista.getNome()));
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "--------Álbum--------\n  Id: " + id + "\n  Nome: " + nome + "\n  Ano: " + ano + "\n  Duração Total: " + duracaoTotal;
	}
}
