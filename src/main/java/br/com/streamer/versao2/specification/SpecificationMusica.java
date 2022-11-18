package br.com.streamer.versao2.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.streamer.versao2.orm.Musica;

public class SpecificationMusica {
	public static Specification<Musica> nome(String nome) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.like(root.get("nome"), "%"+nome+"%");
	}
}
