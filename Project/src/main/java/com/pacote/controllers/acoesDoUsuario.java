package com.pacote.controllers;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.Track;

public class AcoesDoUsuario {
 

    public void pesquisarMusica(BibliotecaDePlaylists bibliotecaMaster){
        Scanner sc = new Scanner(System.in);
        String nomeMusica = this.nomeDesejado("Música");
        Track[] itens_pesquisados = ComunicadorDoSpotify.PesquisaMusicas(nomeMusica);
    	System.out.println(" ");
        if(itens_pesquisados.length == 0) {
        	System.out.println("Nenhum resultado encontrado, cancelando operação.");
        	return;
        }
        PlaylistInterna listaDeBusca = new PlaylistInterna("Resultado De Busca");
        listaDeBusca.playlist = Arrays.asList(itens_pesquisados);
        listaDeBusca.imprimePlaylist();
    	decisorAposBuscaDeMusicas(sc, listaDeBusca, bibliotecaMaster, nomeMusica);
    	return;
    }

	public void decisorAposBuscaDeMusicas(Scanner sc, PlaylistInterna listaDeBusca, BibliotecaDePlaylists bibliotecaMaster, String nomeMusica) {
		System.out.println("Quer adicionar música(1) ou voltar para o Menu Principal(2)");
    	if(sc.nextInt() == 1)
    		this.adicionarMusicaAPlaylist(listaDeBusca, bibliotecaMaster, nomeMusica);
    	return;
    }
    
    private void adicionarMusicaAPlaylist(PlaylistInterna listaDeBusca, BibliotecaDePlaylists bibliotecaMaster, String nomeMusica){   	
    	String nomeDaPlaylist = this.nomeDesejado("Playlist");
    	bibliotecaMaster.AdicionaMusicaAPlaylist(nomeDaPlaylist, nomeMusica, listaDeBusca);    	
    }

    public void visualizarPlaylists(BibliotecaDePlaylists bibliotecaMaster){
        if(bibliotecaMaster.biblioteca.isEmpty()){
            System.out.println("Não há nenhuma playlist salva");
        }
        else {       
	        System.out.println("Playlists Salvas: ");
	        for(PlaylistInterna listaDeMusicas : bibliotecaMaster.biblioteca){
	            System.out.println(listaDeMusicas.nome);
	        }
	        System.out.println("Deseja ver as músicas de uma playlist(1), criar uma playlist(2), alterar o nome de uma playlist(3) ou voltar ao Menu Pricipal(4)?");
	        Scanner sc = new Scanner(System.in);
	        int escolha = sc.nextInt();
	        if(escolha == 1)
	        	this.visualizarMúsicasDePlaylist(bibliotecaMaster);
	        else if (escolha == 2)
	        	bibliotecaMaster = this.criarPlaylist(bibliotecaMaster);
	        else if (escolha == 3)
	        	this.mudarNomeDePlaylist(bibliotecaMaster);
        }
       return; 
    }
    
    private void mudarNomeDePlaylist(BibliotecaDePlaylists bibliotecaMaster) {
    	String nomeDaPlaylist = this.nomeDesejado("Playlist");
    	List<PlaylistInterna> listaComNome = bibliotecaMaster.achaPlaylistsCompatíveisComBusca(nomeDaPlaylist);
    	if(listaComNome.isEmpty()) {
    		System.out.println("Não há playlist compatível, cancelando operação");
    		return;
    	}
    	System.out.println("Insira o índice da Playlist");
		Scanner sc = new Scanner(System.in);
		int indiceDaPlaylist = sc.nextInt();
		System.out.println("Insira o novo nome:");
		sc.nextLine();
		listaComNome.get(indiceDaPlaylist - 1).alteraNome(sc.nextLine());
		System.out.println("Nome alterado com sucesso!");
    	
    }

    public BibliotecaDePlaylists criarPlaylist(BibliotecaDePlaylists bibliotecaMaster){
    	String nomeDaPlaylist = this.nomeDesejado("Playlist");
    	PlaylistInterna novaPlaylist = new PlaylistInterna(nomeDaPlaylist);
    	bibliotecaMaster.biblioteca.add(novaPlaylist);
    	System.out.println("Playlist " + nomeDaPlaylist + " criada com sucesso!");
    	return bibliotecaMaster;
    }

    public BibliotecaDePlaylists deletarPlaylist(BibliotecaDePlaylists bibliotecaMaster){
    	String nomeDaPlaylist = this.nomeDesejado("Playlist");
    	boolean removeu = bibliotecaMaster.removePlaylist(nomeDaPlaylist);
    	if (removeu) {
        	System.out.println("Playlist removida com sucesso!");
    	}
    	return bibliotecaMaster;
    }

    public void visualizarMúsicasDePlaylist(BibliotecaDePlaylists bibliotecaMaster) {
    	String nomeDaPlaylist = this.nomeDesejado("Playlist");
    	Scanner sc = new Scanner(System.in);
    	PlaylistInterna playlistDesejada = bibliotecaMaster.visualizaPlaylist(nomeDaPlaylist);
    	if(playlistDesejada == null)
    		return;
    	if(playlistDesejada.playlist.isEmpty()) {
    		System.out.println("Playlist vazia!");
    		return;
    	}
    	System.out.println("Gostaria de Remover uma música(1) ou retornar ao Menu(2)?");
    	if(sc.nextInt() == 1)
	    	this.acaoRemoveMusica(playlistDesejada, bibliotecaMaster);
    	return;
    }
    
    private void acaoRemoveMusica(PlaylistInterna playlistDesejada, BibliotecaDePlaylists bibliotecaMaster) {
    	String nomeDaMusica = this.nomeDesejado("Música");
    	bibliotecaMaster.RemoveMusicaDePlaylist(nomeDaMusica, playlistDesejada);
    	
    }
    
    public void pesquisarEmPlaylist(BibliotecaDePlaylists bibliotecaMaster) {
    	Scanner sc = new Scanner(System.in);
    	String nomeDaPlaylist = this.nomeDesejado("Playlist");
    	PlaylistInterna playlistDesejada = bibliotecaMaster.visualizaPlaylist(nomeDaPlaylist);
    	if(playlistDesejada == null)
    		return;
    	String nomeDaMusica = this.nomeDesejado("Música");
    	int [] listaDeIndices = bibliotecaMaster.buscaMusicaEmPlaylist(nomeDaMusica, playlistDesejada);
    	for(int i = 0; listaDeIndices[i] != -2; i++)
    		playlistDesejada.imprimeTrack(playlistDesejada.playlist.get(listaDeIndices[i]));
    	System.out.println(" ");
    	System.out.println("Gostaria de Remover uma música(1) ou retornar ao Menu(2)?");
    	if(sc.nextInt() == 1)
	    	this.acaoRemoveMusica(playlistDesejada, bibliotecaMaster);
    	return;    	
    }
    
    private String nomeDesejado(String tipo) {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Insira o nome da " + tipo);
    	String nome = sc.nextLine();
    	return nome;
    }
    
}
