package br.com.streamer.versao2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.streamer.versao2.orm.Artista;

@Repository
public interface ArtistaRepository extends PagingAndSortingRepository<Artista, Integer>, JpaSpecificationExecutor<Artista>{
	@Query("SELECT a FROM Artista a WHERE a.nacionalidade.nome = :nacionalidade")
	List<Artista> findArtistaByNacionalidade(String nacionalidade);
}
