package com.pacote.controllers;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
//import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.Track;

public class AcoesDoUsuario {
 

    public void pesquisarMusica(BibliotecaDePlaylists bibliotecaMaster, Scanner sc){

        String nomeMusica = this.nomeDesejado("Música", sc);
        Track[] itens_pesquisados = ComunicadorDoSpotify.PesquisaMusicas(nomeMusica);
    	System.out.println(" ");
        if(itens_pesquisados.length == 0) {
        	System.out.println("Nenhum resultado encontrado, cancelando operação.");
        	return;
        }
        PlaylistInterna listaDeBusca = new PlaylistInterna("Resultado De Busca");
        listaDeBusca.setPlaylist(Arrays.asList(itens_pesquisados));
        listaDeBusca.imprimePlaylist();
    	decisorAposBuscaDeMusicas(sc, listaDeBusca, bibliotecaMaster, nomeMusica);
    	return;
    }

	public void decisorAposBuscaDeMusicas(Scanner sc, PlaylistInterna listaDeBusca, BibliotecaDePlaylists bibliotecaMaster, String nomeMusica) {
		System.out.println("Quer adicionar música(1) ou voltar para o Menu Principal(2)");
    	if(sc.nextInt() == 1)
    		this.adicionarMusicaAPlaylist(listaDeBusca, bibliotecaMaster, nomeMusica, sc);
    	return;
    }
    
    private void adicionarMusicaAPlaylist(PlaylistInterna listaDeBusca, BibliotecaDePlaylists bibliotecaMaster, String nomeMusica, Scanner sc){   	
    	String nomeDaPlaylist = this.nomeDesejado("Playlist", sc);    	
    }

    public void visualizarPlaylists(BibliotecaDePlaylists bibliotecaMaster, Scanner sc){
        if(bibliotecaMaster.biblioteca.isEmpty()){
            System.out.println("Não há nenhuma playlist salva");
        }
        else {       
	        System.out.println("Playlists Salvas: ");
	        for(PlaylistInterna listaDeMusicas : bibliotecaMaster.biblioteca){
	            System.out.println(listaDeMusicas.nome);
	        }
	        System.out.println("Deseja ver as músicas de uma playlist(1), criar uma playlist(2), alterar o nome de uma playlist(3) ou voltar ao Menu Pricipal(4)?");
	        
	        int escolha = sc.nextInt();
	        if(escolha == 1)
	        	this.visualizarMúsicasDePlaylist(bibliotecaMaster, sc);
	        else if (escolha == 2)
	        	bibliotecaMaster = this.criarPlaylist(bibliotecaMaster, sc);
	        else if (escolha == 3)
	        	this.mudarNomeDePlaylist(bibliotecaMaster, sc);
        }
       return; 
    }
    
    private void mudarNomeDePlaylist(BibliotecaDePlaylists bibliotecaMaster, Scanner sc) {
    	String nomeDaPlaylist = this.nomeDesejado("Playlist", sc);
    	List<PlaylistInterna> listaComNome = bibliotecaMaster.achaPlaylistsCompatíveisComBusca(nomeDaPlaylist);
    	if(listaComNome.isEmpty()) {
    		System.out.println("Não há playlist compatível, cancelando operação");
    		return;
    	}
    	System.out.println("Insira o índice da Playlist");
		
		int indiceDaPlaylist = sc.nextInt();
		System.out.println("Insira o novo nome:");
		sc.nextLine();
		listaComNome.get(indiceDaPlaylist - 1).nome = sc.nextLine();
		System.out.println("Nome alterado com sucesso!");
    	
    }

    public BibliotecaDePlaylists criarPlaylist(BibliotecaDePlaylists bibliotecaMaster, Scanner sc){
    	String nomeDaPlaylist = this.nomeDesejado("Playlist", sc);
    	PlaylistInterna novaPlaylist = new PlaylistInterna(nomeDaPlaylist);
    	bibliotecaMaster.biblioteca.add(novaPlaylist);
    	System.out.println("Playlist " + nomeDaPlaylist + " criada com sucesso!");
    	return bibliotecaMaster;
    }

    public BibliotecaDePlaylists deletarPlaylist(BibliotecaDePlaylists bibliotecaMaster, Scanner sc){
    	String nomeDaPlaylist = this.nomeDesejado("Playlist", sc);
    	boolean removeu = bibliotecaMaster.removePlaylist(nomeDaPlaylist, sc);
    	if (removeu) {
        	System.out.println("Playlist removida com sucesso!");
    	}
    	return bibliotecaMaster;
    }

    public void visualizarMúsicasDePlaylist(BibliotecaDePlaylists bibliotecaMaster, Scanner sc) {
    	String nomeDaPlaylist = this.nomeDesejado("Playlist", sc);
    	
    	PlaylistInterna playlistDesejada = bibliotecaMaster.visualizaPlaylist(nomeDaPlaylist, sc);
    	if(playlistDesejada == null)
    		return;
    	if(playlistDesejada.playlist.isEmpty()) {
    		System.out.println("Playlist vazia!");
    		return;
    	}
    	System.out.println("Gostaria de Remover uma música(1) ou retornar ao Menu(2)?");
    	if(sc.nextInt() == 1)
	    	this.acaoRemoveMusica(playlistDesejada, bibliotecaMaster, sc);
    	return;
    }
    
    private void acaoRemoveMusica(PlaylistInterna playlistDesejada, BibliotecaDePlaylists bibliotecaMaster, Scanner sc) {
    	String nomeDaMusica = this.nomeDesejado("Música", sc);
    	playlistDesejada.RemoveMusicaDePlaylist(nomeDaMusica, sc);
    }
    
    public void pesquisarEmPlaylist(BibliotecaDePlaylists bibliotecaMaster, Scanner sc) {
    	
    	String nomeDaPlaylist = this.nomeDesejado("Playlist", sc);
    	PlaylistInterna playlistDesejada = bibliotecaMaster.visualizaPlaylist(nomeDaPlaylist, sc);
    	if(playlistDesejada == null)
    		return;
    	String nomeDaMusica = this.nomeDesejado("Música", sc);
    	List<Track> resultadoDeBusca = playlistDesejada.BuscaPorNome(nomeDaMusica);
    	playlistDesejada
    	for(int i = 0; listaDeIndices[i] != -2; i++)
    		playlistDesejada.imprimeTrack(playlistDesejada.playlist.get(listaDeIndices[i]));
    	System.out.println(" ");
    	System.out.println("Gostaria de Remover uma música(1) ou retornar ao Menu(2)?");
    	if(sc.nextInt() == 1)
	    	this.acaoRemoveMusica(playlistDesejada, bibliotecaMaster, sc);
    	return;    	
    }
    
    private String nomeDesejado(String tipo, Scanner sc) {
    	sc.nextLine();
    	System.out.println("Insira o nome da " + tipo);
    	String nome = sc.nextLine();
    	return nome;
    }
    
}
