package br.com.streamer.versao2.orm;

public enum TipoArtista {
	COMPOSITOR(1, "Compositor"), INTERPRETE(2, "Interprete"), LETRISTA(3, "Letrista");
	
	private Integer indice;
	private String valor;
	
	private TipoArtista(Integer indice, String valor) {
		this.indice = indice;
		this.valor = valor;
	}
	
	public Integer getIndice() {
		return indice;
	}
	
	public String getValor() {
		return valor;
	}
	
	public static TipoArtista valueOf(Integer indice) {
		for(TipoArtista tipo : TipoArtista.values()) {
			if(tipo.indice == indice) {
				return tipo;
			}
		}
		return null;
	}
}
