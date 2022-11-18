package br.com.streamer.versao2.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.streamer.versao2.orm.Genero;

@Repository
public interface GeneroRepository extends PagingAndSortingRepository<Genero, Integer>{

}
