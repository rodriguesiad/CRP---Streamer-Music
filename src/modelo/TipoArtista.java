package modelo;

public enum TipoArtista {
	COMPOSITOR(1, "Compositor"), INTERPRETE(2, "Interprete"), LETRISTA(3, "Letrista");
	
	private int indice;
	private String valor;
	
	private TipoArtista(int indice, String valor) {
		this.indice = indice;
		this.valor = valor;
	}
	
	public int getIndice() {
		return indice;
	}
	
	public String getValor() {
		return valor;
	}
	
	public TipoArtista valueOf(int indice) {
		for(TipoArtista tipo : TipoArtista.values()) {
			if(tipo.indice == indice) {
				return tipo;
			}
		}
		return null;
	}
}
