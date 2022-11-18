package br.com.streamer.versao2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.streamer.versao2.orm.Musica;

@Repository
public interface MusicaRepository extends PagingAndSortingRepository<Musica, Integer>, JpaSpecificationExecutor<Musica>{
	
	@Query(value = "SELECT * FROM musica m JOIN musica_artistas ma ON ma.musica_id = m.id JOIN artista a ON a.id = ma.artistas_id WHERE a.nome = :nome", nativeQuery = true )
	List<Musica> findByArtista(String nome);
}
