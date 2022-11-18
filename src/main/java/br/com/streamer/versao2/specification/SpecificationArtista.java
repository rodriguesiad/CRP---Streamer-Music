package br.com.streamer.versao2.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.streamer.versao2.orm.Artista;

public class SpecificationArtista {
	public static Specification<Artista> nome(String nome) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.like(root.get("nome"), "%"+nome+"%");
	}
	
	public static Specification<Artista> nacionalidade(String nacionalidade) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.like(root.get("nacionalidade.nome"), "%"+nacionalidade+"%");
	}
}
