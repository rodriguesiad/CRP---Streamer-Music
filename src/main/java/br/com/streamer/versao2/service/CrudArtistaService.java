package br.com.streamer.versao2.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.streamer.versao2.orm.Artista;
import br.com.streamer.versao2.orm.Nacionalidade;
import br.com.streamer.versao2.orm.Sexo;
import br.com.streamer.versao2.orm.TipoArtista;
import br.com.streamer.versao2.repository.ArtistaRepository;
import br.com.streamer.versao2.repository.NacionalidadeRepository;

@Service
@Transactional
public class CrudArtistaService {
	private Boolean system = true;
	
	private List<Artista> lixeira = new ArrayList<Artista>();
	
	private final ArtistaRepository artistaRepository;
	private final NacionalidadeRepository nacionalidadeRepository;
	
	public CrudArtistaService(ArtistaRepository artistaRepository, NacionalidadeRepository nacionalidadeRepository) {
		this.artistaRepository = artistaRepository;
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
				salvar("Lana Del Rey", LocalDate.of(1969, 9, 3), "lanadelrey@gmail.com", 1, Sexo.FEMININO, scan);
				break;
			}
			case 2: {
				atualizar(2, "Tim Maia", LocalDate.of(1968, 9, 11), "timmaia@gmail.com", 1, Sexo.MASCULINO, scan);
				break;
			}
			case 3: {
				visualizar(scan);
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
	
	private List<TipoArtista>  tipoArtista(Scanner scan) {
		Boolean isTrue = true;
        List<TipoArtista> tipos = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o tipo:\n(Para sair digite 0)\n1 - Compositor\n2 - Interprete\n3 - Letrista");
            int tipo = scan.nextInt();

            if(tipo != 0) {
                tipos.add(TipoArtista.valueOf(tipo));
            } else {
                isTrue = false;
            }
        }
        return tipos;
	}
	
	private Nacionalidade nacionalidade(int op) {
		return nacionalidadeRepository.findById(1).get(); //deixei selecionado
	}
	
	private void salvar(String nome, LocalDate dataNascimento, String email, int nacionalidade, Sexo sexo, Scanner scan) {
		Artista a = new Artista();
		List<TipoArtista> tipos = tipoArtista(scan);
		a.setNome(nome);
		a.setDataNascimento(dataNascimento);
		a.setEmail(email);
		a.setNacionalidade(nacionalidade(nacionalidade));
		a.setSexo(sexo);
		a.setTipo(tipos);
		artistaRepository.save(a);
		System.out.println("Artista Salvo!!\n");
	}
		
	private void atualizar(Integer id, String nome, LocalDate dataNascimento, String email, int nacionalidade, Sexo sexo, Scanner scan) {
		Artista a = new Artista();
		List<TipoArtista> tipos = tipoArtista(scan);
		a.setId(id);
		a.setNome(nome);
		a.setDataNascimento(dataNascimento);
		a.setEmail(email);
		a.setNacionalidade(nacionalidade(nacionalidade));
		a.setSexo(sexo);
		a.setTipo(tipos);
		artistaRepository.save(a);
		System.out.println("Artista Atualizado!!\n");
	}
	
	public void selecionar(Scanner scan) {
		System.out.println("\nDigite o id do artista que deseja selecionar:\n(Para sair digite 0)");
		Integer id = scan.nextInt();
		
		if (id != 0) {
			Optional<Artista> a = artistaRepository.findById(id);
			a.get().visualizar();
		}
	}
	
	private void visualizar(Scanner scan) {
		Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "nome"));		
		Iterable<Artista> artistas = artistaRepository.findAll(pageable);
		artistas.forEach(System.out::println);
		
		selecionar(scan);
	}
	
	private void deletar(Scanner scan) {
		System.out.println("Id: ");
		Integer id = scan.nextInt();
		lixeira.add(artistaRepository.findById(id).get());
		artistaRepository.deleteById(id);
		System.out.println("Música Deletada!!\n");
	}
}
