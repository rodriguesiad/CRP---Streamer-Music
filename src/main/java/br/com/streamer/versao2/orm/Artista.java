package br.com.streamer.versao2.orm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Table(name = "artista")
@Transactional
public class Artista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private LocalDate dataNascimento;
	private String email;
	
	@ManyToOne
	private Nacionalidade nacionalidade;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@ElementCollection(fetch = FetchType.LAZY, targetClass = TipoArtista.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "TipoArtista", length = 30)
    @JoinTable(name = "Artista_TipoArtista")
	private List<TipoArtista> tipo = new ArrayList<>();
	@ManyToMany
	private List<Album> albuns = new ArrayList<>();

	public void visualizar() {
		System.out.println(this);
		this.tipo.forEach((tipo) -> System.out.println("  "+tipo));
		this.albuns.forEach((album) -> System.out.println("  "+ album));
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

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
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
	public String toString() {
		return "Artista\n  Id: " + id + "\n  Nome: " + nome + "\n  Data de Nascimento: " + dataNascimento + "\n  Email: " + email
				+ "\n  Nacionalidade: " + nacionalidade.getNome() + "\n  Sexo: " + sexo.getValor();
	}
}
