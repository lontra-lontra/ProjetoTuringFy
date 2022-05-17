package com.pacote.controllers;
import se.michaelthelin.spotify.model_objects.specification.Track;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BibliotecaDePlaylists {
	List<PlaylistInterna> biblioteca;
	
	public PlaylistInterna criaPlaylist(String nome) {
		PlaylistInterna novaPlaylist = new PlaylistInterna();
		novaPlaylist.nome = nome;
		novaPlaylist.playlist = new ArrayList<Track>();
		return novaPlaylist;
	}
	
	public void removePlaylist(String nome) {
		List<PlaylistInterna> listaDeRemovíveis = new ArrayList<>();
		listaDeRemovíveis = achaPlaylistsCompatíveisComBusca(nome);
		System.out.println("Insira os índices das Playlists a serem removidas, termine a sequência com 0.");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		while( n != 0) {
			this.biblioteca.remove(listaDeRemovíveis.get(n - 1));
			n = sc.nextInt();
		}
		sc.close();
	}

	private List<PlaylistInterna> achaPlaylistsCompatíveisComBusca(String nome) {
		List<PlaylistInterna> listaDeCompatíveis;
		listaDeCompatíveis = this.biblioteca.stream().filter(playlist-> (playlist.nome.contains(nome))).collect(Collectors.toList());
		System.out.println("Resultados Compatíveis: ");
		for (int i = 0; i < listaDeCompatíveis.size(); i ++)
			System.out.println((i + 1) + " - " + listaDeCompatíveis.get(i).nome);
		return listaDeCompatíveis;
	}
	
	
	public PlaylistInterna AdicionaMusicaAPlaylist(String playlist, String musica, Track[] listaDeMusicas){
		List<PlaylistInterna> recebeMusica = this.achaPlaylistsCompatíveisComBusca(playlist);
		System.out.println("Insira o índice da Playlist a receber a música");
		Scanner sc = new Scanner(System.in);
		int indiceDaPlaylistRecebeMusica = sc.nextInt();
		int[] indiceDaMusicaASerAdicionada = this.buscaMusicaEmVetorTrackPorNome(musica, listaDeMusicas);
		for(int i = 0; i < indiceDaMusicaASerAdicionada.length; i ++ )
			recebeMusica.get(indiceDaPlaylistRecebeMusica - 1).adicionaMusica(listaDeMusicas[indiceDaMusicaASerAdicionada[i]]);
		sc.close();
		return recebeMusica.get(indiceDaPlaylistRecebeMusica - 1);
	}
	

	private int[] buscaMusicaEmVetorTrackPorNome(String nome, Track[] listaDeMusicas) {
		int i, n = 0;
		System.out.println("Músicas Compatíveis com o Termo de Busca: ");
		for(i = 0; i < listaDeMusicas.length; i++, n++)
			if(listaDeMusicas[i].getName().contains(nome))
				System.out.println((i + 1) + " - " + listaDeMusicas[i].getName());
		int [] indices = new int[n];
		System.out.println("Insira os índices das Músicas desejadas, terminados por 0");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int j = sc.nextInt();
		for(i = 0; i < n && j != 0; i++) {
			indices[i] = j;
			j = sc.nextInt();
		}
		sc.close();
		return indices;	
	}
	
	public void visualizaPlaylist(String nome) {
		List<PlaylistInterna> lista = this.achaPlaylistsCompatíveisComBusca(nome);
		System.out.println("Insira o índice da Playlist desejada");
		Scanner sc = new Scanner(System.in);
		int indiceDaPlaylistDesejada = sc.nextInt();
		lista.get(indiceDaPlaylistDesejada - 1).imprimePlaylist();
		sc.close();
	}

}
