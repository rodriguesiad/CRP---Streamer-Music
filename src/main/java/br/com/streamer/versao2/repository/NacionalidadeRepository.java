package br.com.streamer.versao2.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.streamer.versao2.orm.Nacionalidade;

@Repository
public interface NacionalidadeRepository extends PagingAndSortingRepository<Nacionalidade, Integer>{

}
