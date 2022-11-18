package br.com.streamer.versao2.orm;

public enum Sexo {
MASCULINO(1, "Masculino"), FEMININO(2, "Feminino"), OUTROS(3, "Outros");
	
	private Integer indice;
	private String valor;
	
	private Sexo(Integer indice, String valor) {
		this.indice = indice;
		this.valor = valor;
	}
	
	public Integer getIndice() {
		return indice;
	}
	
	public String getValor() {
		return valor;
	}
	
	public static Sexo valueOf(Integer indice) {
		for(Sexo tipo : Sexo.values()) {
			if(tipo.indice == indice) {
				return tipo;
			}
		}
		return null;
	}
}
