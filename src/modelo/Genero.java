package modelo;

import java.util.Objects;

public class Genero {
	private String nome;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public Genero(String nome) {
		super();
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genero other = (Genero) obj;
		return Objects.equals(nome, other.nome);
	}
	
	
	
	
}
