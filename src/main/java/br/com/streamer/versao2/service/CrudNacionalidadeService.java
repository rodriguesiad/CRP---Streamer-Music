package br.com.streamer.versao2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.streamer.versao2.orm.Nacionalidade;
import br.com.streamer.versao2.repository.NacionalidadeRepository;

@Service
public class CrudNacionalidadeService {
	private Boolean system = true;
	
	private List<Nacionalidade> lixeira = new ArrayList<Nacionalidade>();
	
	private final NacionalidadeRepository nacionalidadeRepository;
	
	public CrudNacionalidadeService (NacionalidadeRepository nacionalidadeRepository) {
		this.nacionalidadeRepository = nacionalidadeRepository;
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
				salvar("Brasileiro(a)", "BR");
				salvar("Estadunidense", "EUA");
				break;
			}
			case 2: {
				atualizar(2, "Estadunidense", "EUA");
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
	
	private void salvar(String nome, String sigla) {
		//aqui é onde receberia o scan como parâmetro e pegaria os dados do usário
		Nacionalidade n = new Nacionalidade();
		n.setNome(nome);
		n.setSiglaPais(sigla);
		nacionalidadeRepository.save(n);
		System.out.println("Nacionalidade Salva!!");
	}
	
	private void atualizar(Integer id, String nome, String sigla) {
		//aqui é onde receberia o scan como parâmetro e pegaria os dados do usário
		Nacionalidade n = new Nacionalidade();
		n.setId(id);
		n.setNome(nome);
		n.setSiglaPais(sigla);
		nacionalidadeRepository.save(n);
		System.out.println("Nacionalidade Atualizada!!");
	}
	
	private void visualizar() {
		Iterable<Nacionalidade> nacionalidades = nacionalidadeRepository.findAll();
		nacionalidades.forEach(System.out::println);
	}
	
	private void deletar(Scanner scan) {
		System.out.print("Id: ");
		Integer id = scan.nextInt();
		lixeira.add(nacionalidadeRepository.findById(id).get());
		nacionalidadeRepository.deleteById(id);
		System.out.println("Nacionalidade Removida!!");
	}
}
