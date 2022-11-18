package aplicacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import modelo.Album;
import modelo.Artista;
import modelo.Musica;

public class Principal {

	public static Scanner scan = new Scanner(System.in);
	public static List<Artista> artistas = new ArrayList<>();
	public static List<Album> albuns = new ArrayList<>();
	public static List<Musica> musicas = new ArrayList<>();

	public static List<Artista> lixeiraArtistas = new ArrayList<>();
	public static List<Album> lixeiraAlbuns = new ArrayList<>();
	public static List<Musica> lixeiraMusicas = new ArrayList<>();

	public Musica cadastrarMusica() {
		int op;
		Musica m = Musica.cadastrarMusica(new Musica());

		do {
			System.out.println("--------- Artistas da música ---------");
			m.getArtistas().add(selecionarArtista());
			System.out.print("\nDeseja adicionar outro artista?\n1 - Sim\n2 - Não\nDigite: ");
			op = scan.nextInt();
		} while (op != 2);

		return m;
	}

	public Album cadastrarAlbum() {
		int op;
		Album album = Album.cadastrarAlbum(new Album());

		do {
			System.out.println("--------- Artistas ---------");
			album.getArtistas().add(selecionarArtista());

			System.out.print("\nDeseja adicionar outro artista?\n1 - Sim\n2 - Não\nDigite: ");
			op = scan.nextInt();
		} while (op != 2);

		do {
			album.getMusicas().add(selecionarMusica());
			System.out.print("\nDeseja adicionar outra música?\n1 - Sim\n2 - Não\nDigite: ");
			op = scan.nextInt();
		} while (op != 2);

		album.getMusicas().forEach((musica) -> album.setDuracaoTotal(musica.getTempoDuracao()));

		return album;
	}

	public Artista selecionarArtista() {
		int op;
		Artista a;
		imprimirArtistas();
		System.out.println(artistas.size() + 1 + " - Novo");
		System.out.print("Digite: ");
		op = scan.nextInt();
		if (op >= artistas.size() + 1) {
			a = Artista.cadastrarArtista(new Artista());
			Principal.artistas.add(a);
			return a;
		}

		else
			return artistas.get(op - 1);
	}

	public void ordenarAlbum(List<Album> album) {
		Collections.sort(album);
	}

	public Musica selecionarMusica() {
		int op;
		Musica m;
		System.out.println("--------- Músicas ---------");
		imprimirMusicas();
		System.out.println(Principal.musicas.size() + 1 + " - Novo");
		op = scan.nextInt();
		if (op >= musicas.size() + 1) {
			m = cadastrarMusica();
			Principal.musicas.add(m);
			return m;
		} else
			return Principal.musicas.get(op - 1);
	}

	public void imprimirArtistas() {
		Collections.sort(artistas);
		for (int i = 0; i < artistas.size(); i++) {
			System.out.println(i + 1 + " - " + artistas.get(i).getNome());
		}
	}

	public void imprimirAlbuns() {
		Collections.sort(albuns);
		if (Principal.albuns.isEmpty() == true)
			System.out.println("Não há álbuns cadastrados.");
		else {
			System.out.println("- ALBUNS -");
			for (int i = 0; i < Principal.albuns.size(); i++) {
				System.out.print("\n" + i + 1 + " - ");
				Principal.albuns.get(i).imprimir();
			}
		}

	}

	public void imprimirMusicas() {
		Collections.sort(musicas);
		if (Principal.musicas.isEmpty() == true)
			System.out.println("Não há músicas cadastrados.");
		else {
			System.out.println("- MÚSICAS -");
			for (int i = 0; i < Principal.musicas.size(); i++)
				System.out.println(i + 1 + " - " + Principal.musicas.get(i).getNome());
		}
	}

	public void buscarArtista(int parametro, String campo) {
		if (parametro == 2) {
			for (int i = 0; i < Principal.artistas.size(); i++) {
				if (artistas.get(i).getNome().equalsIgnoreCase(campo)) {
					System.out.print(i + 1 + " - " + artistas.get(i));
				}
			}
		}
		if (parametro == 3) {
			for (int i = 0; i < Principal.artistas.size(); i++) {
				if (artistas.get(i).getNacionalidade().equalsIgnoreCase(campo)) {
					System.out.println(i + 1 + " - " + artistas.get(i));
				}
			}
		}

		System.out.print("\nSelecionar um artista?\n1 - Sim\n2 - Não\nDigite: ");
		if (scan.nextInt() == 1) {
			System.out.print("\nDigite seu número: ");
			mostrarArtista(Principal.artistas.get(scan.nextInt() - 1));
		}

	}

	public void buscarMusica(String campo) {
		Principal.musicas.forEach((musica) -> {
			if (musica.getNome().equalsIgnoreCase(campo)) {
				System.out.println(musica);
			}
		});

	}

	public void mostrarArtista(Artista a) {
		a.imprimir();
		System.out.print("\nSelecionar um album?\n1 - Sim\n2 - Não\\nDigite: ");
		if (scan.nextInt() == 1) {
			System.out.print("\nDigite seu número: ");
			Album album = a.getAlbuns().get(scan.nextInt() - 1);
			album.imprimir();
			album.imprimirMusicas();
		}
	}

	public void deletarArtista() {
		imprimirArtistas();
		System.out.print("Digite o número do artista que deseja remover: ");
		int op = scan.nextInt() - 1;
		Principal.lixeiraArtistas.add(Principal.artistas.get(op));
		Principal.albuns.forEach((album) -> {
			if (album.getArtistas().contains(Principal.artistas.get(op)) == true) {
				album.getArtistas().remove(Principal.artistas.get(op));
			}
		});

		Principal.musicas.forEach((musica) -> {
			if (musica.getArtistas().contains(Principal.artistas.get(op)) == true) {
				musica.getArtistas().remove(Principal.artistas.get(op));
			}
		});
		Principal.artistas.remove(op);
	}

	public void deletarAlbum() {
		imprimirAlbuns();
		System.out.print("\nDigite o número do álbum que deseja remover: ");
		int op = scan.nextInt() - 1;
		Principal.lixeiraAlbuns.add(Principal.albuns.get(op));
		Principal.artistas.forEach((artista) -> {
			if (artista.getAlbuns().contains(Principal.albuns.get(op)) == true) {
				artista.getAlbuns().remove(Principal.albuns.get(op));
			}
		});
		Principal.albuns.remove(op);
	}

	public void deletarMusica() {
		imprimirMusicas();
		System.out.print("\nDigite o número da música que deseja remover: ");
		int op = scan.nextInt() - 1;
		Principal.lixeiraMusicas.add(Principal.musicas.get(op));
		Principal.albuns.forEach((album) -> {
			if (album.getMusicas().contains(Principal.musicas.get(op)) == true) {
				album.getMusicas().remove(Principal.musicas.get(op));
			}
		});
		Principal.musicas.remove(op);
	}

	public void menuCadastrar() {
		int opcao = 0;
		System.out.print("\n1 - Artista\n2 - Álbum\n3 - Música\nDigite: ");
		opcao = scan.nextInt();
		if (opcao == 1)
			Principal.artistas.add(Artista.cadastrarArtista(new Artista()));
		if (opcao == 2) {
			Album album = cadastrarAlbum();
			Principal.albuns.add(album);
			artistas.forEach((artista) -> {
				if (album.getArtistas().contains(artista))
					artista.getAlbuns().add(album);
			});
		}
		if (opcao == 3)
			Principal.musicas.add(cadastrarMusica());
	}

	public void menuAlterar() {
		int opcao = 0;
		System.out.print("\n1 - Artista\n2 - Álbum\n3 - Música\nDigite: ");
		opcao = scan.nextInt();
		if (opcao == 1) {
			imprimirArtistas();
			System.out.print("\nDigite o número do artista que deseja alterar: ");
			Artista.alterar(Principal.artistas.get(scan.nextInt() - 1));
		}
		if (opcao == 2) {
			imprimirAlbuns();
			System.out.print("\nDigite o número do álbum que deseja alterar: ");
			Album.alterar(Principal.albuns.get(scan.nextInt() - 1));
		}
		if (opcao == 3) {
			imprimirMusicas();
			System.out.print("\nDigite o número da música que deseja alterar: ");
			Musica.alterar(Principal.musicas.get(scan.nextInt() - 1));

		}
	}

	public void menuDeletar() {
		int opcao = 0;
		System.out.print("\n1 - Artista\n2 - Álbum\n3 - Música\nDigite: ");
		opcao = scan.nextInt();
		if (opcao == 1)
			deletarArtista();
		if (opcao == 2)
			deletarAlbum();
		if (opcao == 3)
			deletarMusica();
	}

	public void menuBuscar() {
		int opcao1 = 0, opcao2 = 0, opcao3 = 0, opcao4 = 0;
		System.out.print("\n1 - Artista\n2 - Música\nDigite: ");
		opcao1 = scan.nextInt();
		if (opcao1 == 1) {
			System.out.print("\nFiltrar por:  \n1 - Todos\n2 - Nome\n3 - Nacionalidade\nDigite: ");
			opcao2 = scan.nextInt();

			if (opcao2 == 1) {
				imprimirArtistas();
			} else if (opcao2 == 2) {
				System.out.print("\nDigite o nome do artista: ");
				buscarArtista(opcao2, scan.next());
			} else if (opcao2 == 3) {
				System.out.print("\nDigite a nacionalidade: ");
				buscarArtista(opcao2, scan.next());
			} else
				System.out.println("Entrada inválida.");
			System.out
					.println("\nDeseja selecionar um artista?\nDigite 0 para não\nDigite o número do artista para sim");
			opcao3 = scan.nextInt();
			if (opcao3 != 0) {
				ordenarAlbum(artistas.get(opcao3 - 1).getAlbuns());
				artistas.get(opcao3 - 1).imprimir();
				System.out
						.println("\nDeseja selecionar um álbum?\nDigite 0 para não\nDigite o número do álbum para sim");
				opcao4 = scan.nextInt();
				if (opcao4 != 0)
					artistas.get(opcao3 - 1).getAlbuns().get(opcao4 - 1).imprimirCompleto();
				;
			}
		}
		if (opcao1 == 2) {
			System.out.print("\nDigite o nome da música: ");
			buscarMusica(scan.next());
		}
	}

	public void menu() {
		int opcao = 0;
		while (true) {
			System.out.print(
					"\n--------- MENU ---------\n1 - Cadastrar\n2 - Buscar\n3 - Alterar\n4 - Remover\n5 - Sair\nDigite: ");
			opcao = scan.nextInt();
			if (opcao == 1)
				menuCadastrar();
			else if (opcao == 2)
				menuBuscar();
			else if (opcao == 3)
				menuAlterar();
			else if (opcao == 4)
				menuDeletar();
			else if (opcao == 5) {
				System.out.println("\nSaindo...\nFim");
				break;
			} else
				System.out.println("\nEntrada inválida");
		}
	}

	public static void main(String[] args) {
		Principal principal = new Principal();
		principal.menu();
	}
}
