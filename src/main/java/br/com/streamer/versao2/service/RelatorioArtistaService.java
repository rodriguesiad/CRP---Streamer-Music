package br.com.streamer.versao2.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.streamer.versao2.orm.Artista;
import br.com.streamer.versao2.repository.ArtistaRepository;
import br.com.streamer.versao2.specification.SpecificationArtista;

@Service
public class RelatorioArtistaService {
	private Boolean system = true;
	private final ArtistaRepository artistaRepository;
	private final CrudArtistaService artistaService;
	
	public RelatorioArtistaService(ArtistaRepository artistaRepository, CrudArtistaService artistaService) {
		this.artistaRepository = artistaRepository;
		this.artistaService = artistaService;
	}
	
	public void inicial(Scanner scan) {
		while(system) {
			System.out.println("0 - Sair");
			System.out.println("1 - Buscar artista por nome");
			System.out.println("2 - Buscar artista por nacionalidade");
			
			int option = scan.nextInt();
			
			switch (option) {
			case 1: {
				buscaNome(scan);
				break;
			}
			case 2: {
				buscaNacionalidade(scan);
				break;
			}
			default:
				system = false;
				break;
			}
		}
	}
	public void selecionarEvisualizar(Scanner scan) {
		artistaService.selecionar(scan);
	}
	
	public void buscaNome(Scanner scan) {
		System.out.print("Digite o nome do artista: ");
		String nome = scan.next();
		
		List<Artista> artistas = artistaRepository.findAll(Specification.where(SpecificationArtista.nome(nome)));
		artistas.forEach(System.out::println);
		
		selecionarEvisualizar(scan);
	}	
	
	public void buscaNacionalidade(Scanner scan) {
		System.out.print("Digite a nacionalidade do artista: ");
		String nacionalidade = scan.next();
		
		List<Artista> artistas = artistaRepository.findArtistaByNacionalidade(nacionalidade);
		artistas.forEach(System.out::println);
		
		selecionarEvisualizar(scan);
	}	
}
