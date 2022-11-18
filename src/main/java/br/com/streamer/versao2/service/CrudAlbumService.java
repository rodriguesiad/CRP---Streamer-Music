package br.com.streamer.versao2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.streamer.versao2.orm.Album;
import br.com.streamer.versao2.orm.Artista;
import br.com.streamer.versao2.orm.Musica;
import br.com.streamer.versao2.orm.TipoArtista;
import br.com.streamer.versao2.repository.AlbumRepository;
import br.com.streamer.versao2.repository.ArtistaRepository;
import br.com.streamer.versao2.repository.MusicaRepository;

@Service
@Transactional
public class CrudAlbumService {
	private Boolean system = true;
	
	private List<Album> lixeira = new ArrayList<Album>();
	
	private final AlbumRepository albumRepository;
	private final MusicaRepository musicaRepository;
	private final ArtistaRepository artistaRepository;
	
	public CrudAlbumService(AlbumRepository albumRepository, MusicaRepository musicaRepository, ArtistaRepository artistaRepository) {
		this.albumRepository = albumRepository;
		this.artistaRepository = artistaRepository;
		this.musicaRepository = musicaRepository;
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
				salvar("2021", 2022, scan);
				break;
			}
			case 2: {
				atualizar(2, "Antigas", 2021, scan);
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
	
	private List<Artista>  artistas(Scanner scan) {
		Boolean isTrue = true;
        List<Artista> artistas = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o artista:\n(Para sair digite 0)");
            Integer op = scan.nextInt();

            if(op != 0) {
                Optional<Artista> artista = artistaRepository.findById(op);
                if(artista.get().getTipo().contains(TipoArtista.INTERPRETE))
                	artistas.add(artista.get());
                else
                	System.out.println("O artista deve ser intérprete!");
            } else {
                isTrue = false;
            }
        }
        return artistas;
	}
	
	private List<Musica>  musicas(Scanner scan) {
		Boolean isTrue = true;
        List<Musica> musicas = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite a música:\n(Para sair digite 0)");
            Integer op = scan.nextInt();

            if(op != 0) {
                Optional<Musica> musica = musicaRepository.findById(op);
                musicas.add(musica.get());
            } else {
                isTrue = false;
            }
        }
        return musicas;
	}
	
	private void salvar(String nome, Integer ano, Scanner scan) {
		Album a = new Album();
		List<Artista> artistas = artistas(scan);
		List<Musica> musicas = musicas(scan);
		a.setNome(nome);
		a.setAno(ano);
		a.setArtistas(artistas);
		a.setMusicas(musicas);
		a.setDuracaoTotal();
		albumRepository.save(a);
		artistas.forEach(artista -> artista.getAlbuns().add(a));
		System.out.println("Álbum Salvo!!\n");
	}
	
	private void atualizar(Integer id, String nome, Integer ano, Scanner scan) {
		Album a = new Album();
		List<Artista> artistas = artistas(scan);
		List<Musica> musicas = musicas(scan);
		a.setId(id);
		a.setNome(nome);
		a.setAno(ano);
		a.setArtistas(artistas);
		a.setMusicas(musicas);
		a.setDuracaoTotal();
		albumRepository.save(a);
		System.out.println("Álbum Atualizado!!\n");
	}
	
	public void selecionar(Scanner scan) {
		System.out.println("\nDigite o id do álbum que deseja selecionar:\n(Para sair digite 0)");
		Integer id = scan.nextInt();
		
		if (id != 0) {
			Optional<Album> a = albumRepository.findById(id);
			a.get().visualizar();
		}
	}
	
	private void visualizar(Scanner scan) {
		Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "nome"));
		
		System.out.println("Ordenar por:");
		System.out.println("1 - Nome");
		System.out.println("2 - Tempo de Duração");
		System.out.println("3 - Ano");
		int op = scan.nextInt();
		
		if(op == 2) 
			pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "duracaoTotal"));
		if(op == 3)
			pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "ano"));
		
		Iterable<Album> albuns = albumRepository.findAll(pageable);
		//albuns.getNumber() - retorna a página atual
		albuns.forEach(System.out::println);
		
		selecionar(scan);
	}
	
	private void deletar(Scanner scan) {
		System.out.println("Id: ");
		Integer id = scan.nextInt();
		lixeira.add(albumRepository.findById(id).get());
		albumRepository.deleteById(id);
		System.out.println("Álbum Deletado!!\n");
	}
}
