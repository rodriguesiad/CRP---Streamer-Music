package br.com.streamer.versao2;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.streamer.versao2.service.CrudAlbumService;
import br.com.streamer.versao2.service.CrudArtistaService;
import br.com.streamer.versao2.service.CrudGeneroService;
import br.com.streamer.versao2.service.CrudMusicaService;
import br.com.streamer.versao2.service.CrudNacionalidadeService;
import br.com.streamer.versao2.service.RelatorioAlbumService;
import br.com.streamer.versao2.service.RelatorioArtistaService;
import br.com.streamer.versao2.service.RelatorioMusicaService;

//CommandLineRunner obriga a execução de comandos após a inicialização da aplicação
//através do override do método run

@SpringBootApplication
public class Versao2Application implements CommandLineRunner{
	
	private Boolean system = true;
	private Boolean system2 = true;
	
	//injeção de dependência 1/2
	private final CrudNacionalidadeService nacionalidadeService;
	private final CrudGeneroService generoService;
	private final CrudMusicaService musicaService;
	private final CrudArtistaService artistaService;
	private final CrudAlbumService albumService;
	private final RelatorioAlbumService relatorioAlbumService;
	private final RelatorioArtistaService relatorioArtistaService;
	private final RelatorioMusicaService relatorioMusicaService;
	
	//injeção de dependência 2/2
	public Versao2Application (CrudNacionalidadeService nacionalidadeService, CrudGeneroService generoService, 
			CrudMusicaService musicaService, CrudArtistaService artistaService, 
			CrudAlbumService albumService, RelatorioAlbumService relatorioAlbumService,
			RelatorioArtistaService relatorioArtistaService,
			RelatorioMusicaService relatorioMusicaService) {
		this.nacionalidadeService = nacionalidadeService;
		this.generoService = generoService;
		this.musicaService = musicaService;
		this.artistaService = artistaService;
		this.albumService = albumService;
		this.relatorioAlbumService = relatorioAlbumService;
		this.relatorioArtistaService = relatorioArtistaService;
		this.relatorioMusicaService = relatorioMusicaService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Versao2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scan = new Scanner(System.in);
		
		while(system) {
			System.out.println("0 - Sair");
			System.out.println("1 - Nacionalidade");
			System.out.println("2 - Gênero");
			System.out.println("3 - Música");
			System.out.println("4 - Artista");
			System.out.println("5 - Álbum");
			System.out.println("6 - Relatórios");
			
			int option = scan.nextInt();
			
			switch (option) {
			case 1: {
				nacionalidadeService.inicial(scan);
				break;
			}
			case 2: {
				generoService.inicial(scan);
				break;
			}
			case 3: {
				musicaService.inicial(scan);
				break;
			}
			case 4: {
				artistaService.inicial(scan);
				break;
			}
			case 5: {
				albumService.inicial(scan);
				break;
			}
			case 6: {
				while(system2) {
					System.out.println("0 - Sair");
					System.out.println("1 - Relatório Artista");
					System.out.println("2 - Relatório Música");
					System.out.println("3 - Relatório Álbum");
					int op = scan.nextInt();
					
					switch (op) {
					case 1: {
						relatorioArtistaService.inicial(scan);
						break;
					}
					case 2: {
						relatorioMusicaService.inicial(scan);
						break;
					}
					case 3: {
						relatorioAlbumService.inicial(scan);
						break;
					}
					default:
						system2 = false;
						break;
					}
				}
				break;
			}
			default:
				system = false;
				break;
			}			
		}
		scan.close();
	}
}
