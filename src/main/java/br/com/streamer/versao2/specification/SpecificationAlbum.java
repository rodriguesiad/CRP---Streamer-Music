package br.com.streamer.versao2.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.streamer.versao2.orm.Album;

public class SpecificationAlbum {
	public static Specification<Album> nome(String nome) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.like(root.get("nome"), "%"+nome+"%");
	}
}
