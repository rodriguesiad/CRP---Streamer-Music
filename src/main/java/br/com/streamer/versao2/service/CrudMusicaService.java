package br.com.streamer.versao2.service;

import java.time.LocalTime;
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
import br.com.streamer.versao2.orm.Genero;
import br.com.streamer.versao2.orm.Musica;
import br.com.streamer.versao2.repository.ArtistaRepository;
import br.com.streamer.versao2.repository.GeneroRepository;
import br.com.streamer.versao2.repository.MusicaRepository;

@Service
@Transactional
public class CrudMusicaService {
	private Boolean system = true;

	private List<Musica> lixeira = new ArrayList<Musica>();
	
	private final MusicaRepository musicaRepository;
	private final GeneroRepository generoRepository;
	private final ArtistaRepository artistaRepository;
	
	public CrudMusicaService(MusicaRepository musicaRepository, GeneroRepository generoRepository,
			 ArtistaRepository artistaRepository) {
		this.musicaRepository = musicaRepository;
		this.generoRepository = generoRepository;
		this.artistaRepository = artistaRepository;
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
				salvar("Útimo Romance", LocalTime.of(0, 2, 21), scan);
				break;
			}
			case 2: {
				atualizar(3, "Ela Partiu", LocalTime.of(0, 3, 21), scan);
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
	
	private List<Genero>  genero(Scanner scan) {
		Boolean isTrue = true;
        List<Genero> generos = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o gênero:\n(Para sair digite 0)");
            Integer genero = scan.nextInt();

            if(genero != 0) {
                Optional<Genero> unidade = generoRepository.findById(genero);
                generos.add(unidade.get());
            } else {
                isTrue = false;
            }
        }
        return generos;
	}
	
	private List<Artista>  artistas(Scanner scan) {
		Boolean isTrue = true;
        List<Artista> artistas = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o artista:\n(Para sair digite 0)");
            Integer op = scan.nextInt();

            if(op != 0) {
                Optional<Artista> artista = artistaRepository.findById(op);
                artistas.add(artista.get());
            } else {
                isTrue = false;
            }
        }
        return artistas;
	}
	
	private void salvar(String nome, LocalTime tempoDur, Scanner scan) {
		Musica m = new Musica();
		List<Genero> generos = genero(scan);
		List<Artista> artistas = artistas(scan);
		m.setNome(nome);
		m.setTempoDuracao(tempoDur);
		m.setGeneros(generos);
		m.setArtistas(artistas);
		System.out.println("Música Salva!!\n");
		musicaRepository.save(m);
	}
		
	private void atualizar(Integer id, String nome, LocalTime tempoDur, Scanner scan) {
		Musica m = new Musica();
		List<Genero> generos = genero(scan);
		List<Artista> artistas = artistas(scan);
		m.setId(id);
		m.setNome(nome);
		m.setTempoDuracao(tempoDur);
		m.setGeneros(generos);
		m.setArtistas(artistas);
		System.out.println("Música Alterada!!\n");
		
		musicaRepository.save(m);
	}
	
	public void selecionar(Scanner scan) {
		System.out.println("\nDigite o id da música que deseja selecionar:\n(Para sair digite 0)");
		Integer id = scan.nextInt();
		
		if (id != 0) {
			Optional<Musica> m = musicaRepository.findById(id);
			m.get().visualizar();
		}
	}
	
	private void visualizar(Scanner scan) {
		Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "nome"));
		Iterable<Musica> musicas = musicaRepository.findAll(pageable);
		musicas.forEach(System.out::println);
		
		selecionar(scan);
	}
	
	private void deletar(Scanner scan) {
		System.out.println("Id: ");
		Integer id = scan.nextInt();
		lixeira.add(musicaRepository.findById(id).get());
		musicaRepository.deleteById(id);
		System.out.println("Música Deletada!!\n");
	}
}
