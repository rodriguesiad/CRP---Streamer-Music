package br.com.streamer.versao2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.streamer.versao2.orm.Genero;
import br.com.streamer.versao2.repository.GeneroRepository;

@Service
public class CrudGeneroService {
	private Boolean system = true;
	
	private List<Genero> lixeira = new ArrayList<Genero>();
	
	private final GeneroRepository generoRepository;
	
	public CrudGeneroService(GeneroRepository generoRepository) {
		this.generoRepository = generoRepository;
	}
	
	public void inicial(Scanner scan) {
		while(system) {
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			
			int option = scan.nextInt();
			
			switch (option) {
			case 1: {
				salvar("Rock");
				salvar("MPB");
				salvar("Pop");
				break;
			}
			case 2: {
				atualizar(3, "Sertanejo");
				break;
			}
			case 3: {
				visualizar();
				break;
			}
			case 4: {
				deletar(scan);
				break;
			}
			default:
				system = false;
				break;
			}
		}
	}
	
	private void salvar(String nome) {
		Genero g = new Genero();
		g.setNome(nome);
		generoRepository.save(g);
		System.out.println("Gênero Salvo!!\n");
	}
		
	private void atualizar(Integer id, String nome) {
		Genero g = new Genero();
		g.setId(id);
		g.setNome(nome);
		generoRepository.save(g);	
		System.out.println("Gênero Atualizado!!\n");
	}
	
	private void visualizar() {
		Iterable<Genero> generos = generoRepository.findAll();
		generos.forEach(System.out::println);
	}
	
	private void deletar(Scanner scan) {
		System.out.println("Id: ");
		Integer id = scan.nextInt();
		generoRepository.deleteById(id);
		lixeira.add(generoRepository.findById(id).get());
		System.out.println("Gênero Deletado!!\n");
	}
}
