package com.pacote.controllers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.pacote.customComparator.CustomComparatorAscending;
import com.pacote.customComparator.CustomComparatorDescending;

import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.Track;

public class OperacoesDoUsuario {
	
	private static OperacoesInternas executor = new OperacoesInternas();
	private static ConversorDeTipo conversor = new ConversorDeTipo();
 

    public void pesquisarMusicaPorNome(Scanner sc){
        String nomeMusica = this.nomeDesejado("Música", sc);
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
        this.decisorAposBuscaDeMusicas(sc, listaDeBusca, nomeMusica);
    	return;
    }
    
    public void pesquisarMusicaPorAlbum(Scanner sc){
        String nomeMusica = this.nomeDesejado("o Album", sc);
        Track[] itens_pesquisados = BuscadorDoSpotify.pesquisaMusicas(nomeMusica);
    	System.out.println(" ");
        if(itens_pesquisados.length == 0) {
        	System.out.println("Nenhum resultado encontrado, cancelando operação.");
        	return;
        }
        List<Track> listaDeBusca = Arrays.asList(itens_pesquisados);
        executor.imprimeListaDeMusicas(listaDeBusca.stream().filter(musica -> (musica.getAlbum().getName().contains(nomeMusica))).collect(Collectors.toList()));
        this.decisorAposBuscaDeMusicas(sc, listaDeBusca, nomeMusica);
    	return;
    }
    
    public void pesquisarMusicaPorArtista(Scanner sc){
        String nomeMusica = this.nomeDesejado("o primeiro Artista", sc);
        Track[] itens_pesquisados = BuscadorDoSpotify.pesquisaMusicas(nomeMusica);
    	System.out.println(" ");
        if(itens_pesquisados.length == 0) {
        	System.out.println("Nenhum resultado encontrado, cancelando operação.");
        	return;
        }
        List<Track> listaDeBusca = Arrays.asList(itens_pesquisados);
        executor.imprimeListaDeMusicas(listaDeBusca.stream().filter(musica -> (musica.getArtists()[0].getName().contains(nomeMusica))).collect(Collectors.toList()));
        this.decisorAposBuscaDeMusicas(sc, listaDeBusca, nomeMusica);
    	return;
    }
    
    public void pesquisarMusicaPorPlaylist(Scanner sc){
        String nomeMusica = this.nomeDesejado("o primeiro Artista", sc);
        List<Playlist> itens_pesquisados = conversor.getFromDifferentType(BuscadorDoSpotify.pesquisaPlaylists(nomeMusica));
    	System.out.println(" ");
        if(itens_pesquisados.isEmpty()) {
        	System.out.println("Nenhum resultado encontrado, cancelando operação.");
        	return;
        }
        List<Track> listaDeBusca = new ArrayList<>();
        for(Playlist lista : itens_pesquisados) {
        	listaDeBusca.addAll(conversor.getFromDifferentType(lista.getTracks().getItems()));
        }
        executor.imprimeListaDeMusicas(listaDeBusca.stream().filter(musica -> (musica.getArtists()[0].getName().contains(nomeMusica))).collect(Collectors.toList()));
        this.decisorAposBuscaDeMusicas(sc, listaDeBusca, nomeMusica);
    	return;
    }
    
	private void decisorAposBuscaDeMusicas(Scanner sc, List<Track> listaDeBusca, String nomeMusica) {
		System.out.println("Quer adicionar música(1) ou voltar para o Menu Principal(2)");
    	if(sc.nextInt() == 1)
    		this.adicionarMusicaAPlaylist(listaDeBusca, nomeMusica, sc);
    	return;
    }
    
    private void adicionarMusicaAPlaylist(List<Track> listaDeBusca, String nomeMusica, Scanner sc){   	
    	String nomeDaPlaylist = this.nomeDesejado("a Playlist", sc); 
    	Playlist listaDoUsuario = executor.selecionaPlaylist(nomeDaPlaylist, sc);
    	List<Track> musicasParaAdicionar = executor.selecionaMusicas(listaDeBusca, nomeMusica, sc);
    	executor.adicionaMusicaAPlaylist(musicasParaAdicionar, listaDoUsuario);    	
    }

    public void visualizarPlaylists(Scanner sc){
    		executor.imprimePlaylistsDoUsuario();
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
    
}
