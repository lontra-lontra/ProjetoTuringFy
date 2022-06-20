package com.pacote.controllers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.pacote.customComparator.CustomComparatorAscending;
import com.pacote.customComparator.CustomComparatorDescending;

import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.Track;

public class OperacoesDoUsuario {
	
	private static OperacoesInternas executor = new OperacoesInternas();
	private static ConversorDeTipo conversor = new ConversorDeTipo();
 

    public void pesquisarMusicaPorNome(Scanner sc){
        String nomeMusica = this.nomeDesejado("a Música", sc);
        Track[] itens_pesquisados = BuscadorDoSpotify.pesquisaMusicas(nomeMusica);
        System.out.println("Track size: " + itens_pesquisados.length);
    	System.out.println(" ");
        if(itens_pesquisados.length == 0) {
        	System.out.println("Nenhum resultado encontrado, cancelando operação.");
        	return;
        }
        List<Track> listaDeBusca = Arrays.asList(itens_pesquisados);
        System.out.println("Array size: " + listaDeBusca.size());
        List<Track> listaPesquisa = listaDeBusca.stream().filter(musica -> (musica.getName().contains(nomeMusica))).collect(Collectors.toList());
        System.out.println("Filtrada : " + listaPesquisa.size());
        executor.imprimeListaDeMusicas(listaPesquisa);
        this.decisorAposBuscaDeMusicas(sc, listaDeBusca);
    	return;
    }
    
    public void pesquisarMusicaPorAlbum(Scanner sc){
        String nomeAlbum = this.nomeDesejado("o Album", sc);
        List<Album> itens_pesquisados = conversor.getFromDifferentType(BuscadorDoSpotify.pesquisaAlbuns(nomeAlbum));
    	System.out.println(" ");
        if(itens_pesquisados.isEmpty()) {
        	System.out.println("Nenhum resultado encontrado, cancelando operação.");
        	return;
        }
        executor.imprimeListaDeAlbuns(itens_pesquisados);
        this.decisorAposBuscaDeAlbuns(sc, itens_pesquisados);
    	return;
    }
    
    private void decisorAposBuscaDeAlbuns(Scanner sc, List<Album> itens_pesquisados) {
    		System.out.println("Quer visualizar Album(1) ou voltar ao menu principal(2)?");
    		if(sc.nextInt() == 1) {
    	        executor.imprimeListaDeAlbuns(itens_pesquisados);
    	        System.out.println("Insira o indice do Album desejado: ");
    	        int indice = sc.nextInt();
    	        if(indice - 1 >= itens_pesquisados.size() || indice - 1 < 0) {
    	        	System.out.println("Índice inválido, cancelando operação.");
    	        	return;
    	        }
    	        System.out.println("Album: " + itens_pesquisados.get(indice - 1).getName());
    			executor.imprimeListaDeMusicas(conversor.getFromDifferentType(itens_pesquisados.get(indice - 1).getTracks().getItems()));
    			this.decisorAposBuscaDeMusicas(sc, conversor.getFromDifferentType(itens_pesquisados.get(indice - 1).getTracks().getItems()));
    		}
	}

	public void pesquisarMusicaPorArtista(Scanner sc){
        String nomeArtista = this.nomeDesejado("o primeiro Artista", sc);
        Artist[] itens_pesquisados = BuscadorDoSpotify.pesquisaArtistas(nomeArtista);
    	System.out.println(" ");
        if(itens_pesquisados.length == 0) {
        	System.out.println("Nenhum resultado encontrado, cancelando operação.");
        	return;
        }
        List<Artist> listaDeBusca = Arrays.asList(itens_pesquisados);
        executor.imprimeListaDeArtistas(itens_pesquisados);
        this.decisorAposBuscaDeArtistas(sc, listaDeBusca);
    	return;
    }
    
    private void decisorAposBuscaDeArtistas(Scanner sc, List<Artist> listaDeBusca) {
    	System.out.println("Gostaria de vizualizar as Musicas Mais Populares de um artista(1), os Álbuns de um artista(2) ou voltar ao menu principal(3)?");
    	int escolha = sc.nextInt();
    	if(escolha == 1) {
    		System.out.println("Insira o indice do Artista desejado: ");
    		int indice = sc.nextInt();
    		executor.imprimeListaDeMusicas(executor.getArtistsMusics(listaDeBusca.get(indice - 1)));
    		this.decisorAposBuscaDeMusicas(sc, executor.getArtistsMusics(listaDeBusca.get(indice)));
    	}
    	else if(escolha == 2) {
    		System.out.println("Insira o indice do Artista desejado: ");
    		int indice = sc.nextInt();
    		executor.imprimeListaDeAlbuns(executor.getArtistsAlbuns(listaDeBusca.get(indice - 1)));
    		this.decisorAposBuscaDeAlbuns(sc, executor.getArtistsAlbuns(listaDeBusca.get(indice - 1)));
    	}
	}

	public void pesquisarMusicaPorPlaylist(Scanner sc){
        String nomeMusica = this.nomeDesejado("o primeiro Artista", sc);
        List<Playlist> itens_pesquisados = conversor.getFromDifferentType(BuscadorDoSpotify.pesquisaPlaylists(nomeMusica));
    	System.out.println(" ");
        if(itens_pesquisados.isEmpty()) {
        	System.out.println("Nenhum resultado encontrado, cancelando operação.");
        	return;
        }
        executor.imprimePlaylists(itens_pesquisados);
        this.decisorAposBuscaDePlaylist(sc, itens_pesquisados);
    	return;
    }
    
	private void decisorAposBuscaDePlaylist(Scanner sc, List<Playlist> itens_pesquisados) {
		System.out.println("Gostaria de vizualizar as músicas de uma playlist(1) ou voltar ao menu principal(2)?");
		if(sc.nextInt() == 1) {
			System.out.println("Insira o indice da Playlist desejada: ");
			int indice = sc.nextInt();
			System.out.println("Playlist: " + itens_pesquisados.get(indice).getName());
			executor.imprimeListaDeMusicas(conversor.getFromDifferentType(itens_pesquisados.get(indice).getTracks().getItems()));
			this.decisorAposBuscaDeMusicas(sc, conversor.getFromDifferentType(itens_pesquisados.get(indice).getTracks().getItems()));
		}
	}

	private void decisorAposBuscaDeMusicas(Scanner sc, List<Track> listaDeBusca) {
		System.out.println("Quer adicionar música(1) ou voltar para o Menu Principal(2)");
    	if(sc.nextInt() == 1)
    		this.adicionarMusicaAPlaylist(listaDeBusca, sc);
    	return;
    }
    
    private void adicionarMusicaAPlaylist(List<Track> listaDeBusca, Scanner sc){   	
    	String nomeDaPlaylist = this.nomeDesejado("a Playlist", sc); 
    	Playlist listaDoUsuario = executor.selecionaPlaylist(nomeDaPlaylist, sc);
    	List<Track> musicasParaAdicionar = executor.selecionaMusicas(listaDeBusca, null, sc);
    	executor.adicionaMusicaAPlaylist(musicasParaAdicionar, listaDoUsuario);    	
    }

    public void visualizarPlaylists(Scanner sc){
    		boolean funcionou = executor.imprimePlaylistsDoUsuario();
    		if(!funcionou) {
    			return;
    		}
  	        System.out.println("Deseja ver as músicas de uma playlist(1), criar uma playlist(2), alterar o nome de uma playlist(3) ou voltar ao Menu Pricipal(4)?");
	        int escolha = sc.nextInt();
	        if(escolha == 1)
	        	this.visualizarMúsicasDePlaylist(sc);
	        else if (escolha == 2)
	        	this.criarPlaylist(sc);
	        else if (escolha == 3)
	        	this.mudarNomeDePlaylist(sc);
       return; 
    }
    
    private void mudarNomeDePlaylist(Scanner sc) {
    	String nomeDaPlaylist = this.nomeDesejado("a Playlist", sc);
    	Playlist listaDoUsuario = executor.selecionaPlaylist(nomeDaPlaylist, sc);
    	if(listaDoUsuario == null) {
    		System.out.println("Não há playlist compatível, cancelando operação");
    		return;
    	}
		System.out.println("Insira o novo nome:");
		sc.nextLine();
		executor.alteraNomeDePlaylist(listaDoUsuario, sc.nextLine());
    }

    public void criarPlaylist(Scanner sc){
    	String nomeDaPlaylist = this.nomeDesejado("a Playlist", sc);
    	executor.criaPlaylistDoUsuario(nomeDaPlaylist);
    }

    public void deletarPlaylist(Scanner sc){
    	String nomeDaPlaylist = this.nomeDesejado("a Playlist", sc);
    	Playlist listaDoUsuario = executor.selecionaPlaylist(nomeDaPlaylist, sc);
    	executor.deletaPlaylistDoUsuario(listaDoUsuario);
    	return ;
    }

    public void visualizarMúsicasDePlaylist(Scanner sc) {
    	String nomeDaPlaylist = this.nomeDesejado("a Playlist", sc); 	
    	Playlist playlistDesejada = executor.selecionaPlaylist(nomeDaPlaylist, sc);
    	if(playlistDesejada == null) {
    		System.out.println("Não há playlist compatível, cancelando operação!");
    		return;
    	}
    	if(playlistDesejada.getTracks().getItems().length == 0) {
    		System.out.println("Playlist vazia!");
    		return;
    	}
    	List<Track> listaDesejada = conversor.getFromDifferentType(playlistDesejada.getTracks().getItems());
    	this.sortListaDeMusica(listaDesejada, "Asc", "Energy");
    	executor.imprimeListaDeMusicas(listaDesejada);
    	System.out.println("Gostaria de Remover uma música(1) ou retornar ao Menu(2)?");
    	if(sc.nextInt() == 1)
	    	this.removerMusica(playlistDesejada, sc);
    	return;
    }
    
    private void removerMusica(Playlist playlistDesejada, Scanner sc) {
    	String nomeDaMusica = this.nomeDesejado("a Música", sc);
    	executor.removeMusicaDaPlaylist(playlistDesejada, nomeDaMusica, sc);
    }
    
    public void pesquisarEmPlaylist(Scanner sc) {
    	String nomeDaPlaylist = this.nomeDesejado("a Playlist", sc);
    	Playlist playlistDesejada = executor.selecionaPlaylist(nomeDaPlaylist, sc);
    	if(playlistDesejada == null) {
    		System.out.println("Não há playlist compatível, cancelando operação!");
    		return;
    	}
    	String nomeDaMusica = this.nomeDesejado("a Música", sc);
    	List<Track> resultadoDeBusca = executor.selecionaMusicas(conversor.getFromDifferentType(playlistDesejada.getTracks().getItems()), nomeDaMusica, sc);
    	executor.imprimeListaDeMusicas(resultadoDeBusca);
    	System.out.println("Gostaria de Remover uma música(1) ou retornar ao Menu(2)?");
    	if(sc.nextInt() == 1)
	    	this.removerMusica(playlistDesejada, sc);
    	return;    	
    }
    
    private String nomeDesejado(String tipo, Scanner sc) {
    	sc.nextLine();
    	System.out.println("Insira o nome d" + tipo);
    	String nome = sc.nextLine();
    	return nome;
    }
    
    public void sortListaDeMusica (List<Track> listaDeMusicas, String ascOrDesc, String filter) {
    	if(ascOrDesc.contains("Asc")) {
    		CustomComparatorAscending comparatorA = new CustomComparatorAscending();
    		int filtro = Arrays.asList(comparatorA.types).indexOf(filter);
    		listaDeMusicas.sort(comparatorA.compara.get(filtro));
    	}
    	else {
    		CustomComparatorDescending comparatorD = new CustomComparatorDescending();
    		int filtro = Arrays.asList(comparatorD.types).indexOf(filter);
    		listaDeMusicas.sort(comparatorD.compara.get(filtro));
    	}
    		
    
    }

	public void pesquisarMusica(Scanner entrada) {
		System.out.println("Métodos De Pesquisa Disponíveis: ");
		System.out.println("1. Por Nome ");
		System.out.println("2. Por Artista ");
		System.out.println("3. Por Álbum ");
		System.out.println("4. Por Playlist ");
		System.out.println("Qual o método escolhido?");
		int escolha = entrada.nextInt();
		if(escolha == 1) {
			this.pesquisarMusicaPorNome(entrada);
		}
		else if(escolha == 2) {
			this.pesquisarMusicaPorArtista(entrada);
		}
		else if(escolha == 3) {
			this.pesquisarMusicaPorAlbum(entrada);
		}
		else if(escolha == 4) {
			this.pesquisarMusicaPorPlaylist(entrada);
		}
		System.out.println("Opção inválida, cancelando operação...");
		
	}
    
}
