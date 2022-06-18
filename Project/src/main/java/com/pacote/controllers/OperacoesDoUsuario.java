package com.pacote.controllers;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.Track;

public class OperacoesDoUsuario {
	
	private static OperacoesInternas executor = new OperacoesInternas();
	private static ConversorDeTipo conversor = new ConversorDeTipo();
 

    public void pesquisarMusica(Scanner sc){
        String nomeMusica = this.nomeDesejado("Música", sc);
        Track[] itens_pesquisados = BuscadorDoSpotify.pesquisaMusicas(nomeMusica);
    	System.out.println(" ");
        if(itens_pesquisados.length == 0) {
        	System.out.println("Nenhum resultado encontrado, cancelando operação.");
        	return;
        }
        List<Track> listaDeBusca = Arrays.asList(itens_pesquisados);
        executor.imprimeListaDeMusicas(listaDeBusca);
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
    	String nomeDaPlaylist = this.nomeDesejado("Playlist", sc); 
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
    	String nomeDaPlaylist = this.nomeDesejado("Playlist", sc);
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
    	String nomeDaPlaylist = this.nomeDesejado("Playlist", sc);
    	executor.criaPlaylistDoUsuario(nomeDaPlaylist);
    }

    public void deletarPlaylist(Scanner sc){
    	String nomeDaPlaylist = this.nomeDesejado("Playlist", sc);
    	Playlist listaDoUsuario = executor.selecionaPlaylist(nomeDaPlaylist, sc);
    	executor.deletaPlaylistDoUsuario(listaDoUsuario);
    	return ;
    }

    public void visualizarMúsicasDePlaylist(Scanner sc) {
    	String nomeDaPlaylist = this.nomeDesejado("Playlist", sc); 	
    	Playlist playlistDesejada = executor.selecionaPlaylist(nomeDaPlaylist, sc);
    	if(playlistDesejada == null) {
    		System.out.println("Não há playlist compatível, cancelando operação!");
    		return;
    	}
    	if(playlistDesejada.getTracks().getItems().length == 0) {
    		System.out.println("Playlist vazia!");
    		return;
    	}
    	executor.imprimeListaDeMusicas(conversor.getFromDifferentType(playlistDesejada.getTracks().getItems()));
    	System.out.println("Gostaria de Remover uma música(1) ou retornar ao Menu(2)?");
    	if(sc.nextInt() == 1)
	    	this.removerMusica(playlistDesejada, sc);
    	return;
    }
    
    private void removerMusica(Playlist playlistDesejada, Scanner sc) {
    	String nomeDaMusica = this.nomeDesejado("Música", sc);
    	executor.removeMusicaDaPlaylist(playlistDesejada, nomeDaMusica, sc);
    }
    
    public void pesquisarEmPlaylist(Scanner sc) {
    	String nomeDaPlaylist = this.nomeDesejado("Playlist", sc);
    	Playlist playlistDesejada = executor.selecionaPlaylist(nomeDaPlaylist, sc);
    	if(playlistDesejada == null) {
    		System.out.println("Não há playlist compatível, cancelando operação!");
    		return;
    	}
    	String nomeDaMusica = this.nomeDesejado("Música", sc);
    	List<Track> resultadoDeBusca = executor.selecionaMusicas(conversor.getFromDifferentType(playlistDesejada.getTracks().getItems()), nomeDaMusica, sc);
    	executor.imprimeListaDeMusicas(resultadoDeBusca);
    	System.out.println("Gostaria de Remover uma música(1) ou retornar ao Menu(2)?");
    	if(sc.nextInt() == 1)
	    	this.removerMusica(playlistDesejada, sc);
    	return;    	
    }
    
    private String nomeDesejado(String tipo, Scanner sc) {
    	sc.nextLine();
    	System.out.println("Insira o nome da " + tipo);
    	String nome = sc.nextLine();
    	return nome;
    }
    
}
