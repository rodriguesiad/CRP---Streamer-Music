package br.com.streamer.versao2.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.streamer.versao2.orm.Musica;
import br.com.streamer.versao2.repository.MusicaRepository;
import br.com.streamer.versao2.specification.SpecificationMusica;

@Service
public class RelatorioMusicaService {
	private Boolean system = true;
	private final MusicaRepository musicaRepository;
	private final CrudMusicaService musicaService;
	
	public RelatorioMusicaService(MusicaRepository musicaRepository, CrudMusicaService musicaService) {
		this.musicaRepository = musicaRepository;
		this.musicaService = musicaService;
	}
	
	public void inicial(Scanner scan) {
		while(system) {
			System.out.println("0 - Sair");
			System.out.println("1 - Buscar música por nome");
			System.out.println("2 - Buscar música por artista");
			
			int option = scan.nextInt();
			
			switch (option) {
			case 1: {
				buscaNome(scan);
				break;
			}
			case 2: {
				buscaArtista(scan);
				break;
			}
			default:
				system = false;
				break;
			}
		}
	}
	
	public void selecionarEvisualizar(Scanner scan) {
		musicaService.selecionar(scan);
	}
	
	public void buscaNome(Scanner scan) {
		System.out.print("Digite o nome da música: ");
		String nome = scan.next();
		
		List<Musica> musicas = musicaRepository.findAll(Specification.where(SpecificationMusica.nome(nome)));
		musicas.forEach(System.out::println);
		
		selecionarEvisualizar(scan);
	}	
	
	public void buscaArtista(Scanner scan) {
		System.out.print("Digite o nome do artista: ");
		String nome = scan.next();
		
		List<Musica> musicas = musicaRepository.findByArtista(nome);
		musicas.forEach(System.out::println);
		
		selecionarEvisualizar(scan);
	}
}
