package modelo;

import java.util.Objects;

public class Nacionalidade {
	private String nome;
	private String siglaPais;
	
	public Nacionalidade(String nome, String siglaPais) {
		super();
		this.nome = nome;
		this.siglaPais = siglaPais;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSiglaPais() {
		return siglaPais;
	}
	public void setSiglaPais(String siglaPais) {
		this.siglaPais = siglaPais;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, siglaPais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nacionalidade other = (Nacionalidade) obj;
		return Objects.equals(nome, other.nome) && Objects.equals(siglaPais, other.siglaPais);
	}

	@Override
	public String toString() {
		return "Nacionalidade: " + nome;
	}
	
	
	
}
