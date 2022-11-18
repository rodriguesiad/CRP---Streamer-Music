package br.com.streamer.versao2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.streamer.versao2.orm.Album;

@Repository
public interface AlbumRepository extends PagingAndSortingRepository<Album, Integer>, JpaSpecificationExecutor<Album>{
	@Query(value = "SELECT * FROM album a JOIN album_artistas aa ON aa.album_id = a.id JOIN artista art ON art.id = aa.artistas_id WHERE art.nome = :nome", nativeQuery = true )
	List<Album> findByArtista(String nome);
}
