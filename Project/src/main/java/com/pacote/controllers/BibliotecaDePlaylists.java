package com.pacote.controllers;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import se.michaelthelin.spotify.model_objects.specification.Track;

public class BibliotecaDePlaylists {
	List<PlaylistInterna> biblioteca;
	
	public BibliotecaDePlaylists() {
		this.biblioteca = new ArrayList<PlaylistInterna>();
	}
	
	public PlaylistInterna criaPlaylist(String nome) {
		PlaylistInterna novaPlaylist = new PlaylistInterna("nome");
		return novaPlaylist;
	}
	
	public boolean removePlaylist(String nome) {
		List<PlaylistInterna> listaDeRemovíveis = new ArrayList<>();
		listaDeRemovíveis = achaPlaylistsCompatíveisComBusca(nome);
		if(listaDeRemovíveis.isEmpty()) {
			System.out.println("Nenhuma playlist encontrada, cancelando operação.");
			return false;
		}
		System.out.println("Insira os índices das Playlists a serem removidas, termine a sequência com 0.");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		while( n != 0) {
			this.biblioteca.remove(listaDeRemovíveis.get(n - 1));
			n = sc.nextInt();
		}
		return true;
	}

	public List<PlaylistInterna> achaPlaylistsCompatíveisComBusca(String nome) {
		List<PlaylistInterna> listaDeCompatíveis;
		listaDeCompatíveis = this.biblioteca.stream().filter(playlist-> (playlist.nome.contains(nome))).collect(Collectors.toList());
		if(!listaDeCompatíveis.isEmpty()) {
			System.out.println("Resultados Compatíveis: ");
			for (int i = 0; i < listaDeCompatíveis.size(); i ++)
				System.out.println((i + 1) + " - " + listaDeCompatíveis.get(i).nome);
		}
		return listaDeCompatíveis;
	}
	
	
	public PlaylistInterna AdicionaMusicaAPlaylist(String playlist, String musica, PlaylistInterna listaDeMusicas){
		List<PlaylistInterna> recebeMusica = this.achaPlaylistsCompatíveisComBusca(playlist);
		if(recebeMusica.isEmpty()) {
			System.out.println("Nenhuma playlist encontrada, cancelando operação");
			return null;
		}
		System.out.println("Insira o índice da Playlist a receber a música");
		Scanner sc = new Scanner(System.in);
		int indiceDaPlaylistRecebeMusica = sc.nextInt();
		return escolheMusicaDoResultadoDeBusca(listaDeMusicas, recebeMusica, indiceDaPlaylistRecebeMusica);
	}

	private PlaylistInterna escolheMusicaDoResultadoDeBusca(PlaylistInterna listaDeMusicas, List<PlaylistInterna> recebeMusica, int indiceDaPlaylistRecebeMusica) {
		int[] indiceDaMusicaASerAdicionada = new int[listaDeMusicas.playlist.size()];
		for(int i = 0; i < listaDeMusicas.playlist.size(); i++)
			System.out.println((i + 1) + " - " + listaDeMusicas.playlist.get(i).getName() + " de " + listaDeMusicas.playlist.get(i).getArtists()[0].getName());
		indiceDaMusicaASerAdicionada = this.escolheIndicesDeMusica(listaDeMusicas.playlist.size(), indiceDaMusicaASerAdicionada);
		for(int i = 0; i < indiceDaMusicaASerAdicionada.length && indiceDaMusicaASerAdicionada[i] != -2; i ++ )
			recebeMusica.get(indiceDaPlaylistRecebeMusica - 1).adicionaMusica(listaDeMusicas.playlist.get(indiceDaMusicaASerAdicionada[i]));
		return recebeMusica.get(indiceDaPlaylistRecebeMusica - 1);
	}
	
	public void RemoveMusicaDePlaylist(String musica, PlaylistInterna listaDeMusicas){
		int[] indiceDaMusicaASerRemovida = this.buscaMusicaEmPlaylist(musica, listaDeMusicas);
		for(int i = 0; i < indiceDaMusicaASerRemovida.length && indiceDaMusicaASerRemovida[i] != -2; i ++)
			listaDeMusicas.playlist.remove(indiceDaMusicaASerRemovida[i]);		
		return;
	}
	

	public int[] buscaMusicaEmPlaylist(String nome, PlaylistInterna listaDeMusicas) {
		int i, n = 0;
		System.out.println("Músicas Compatíveis com o Termo de Busca: ");
		for(i = 0; i < listaDeMusicas.playlist.size(); i++, n++)
			if(listaDeMusicas.playlist.get(i).getName().contains(nome))
				System.out.println((i + 1) + " - " + listaDeMusicas.playlist.get(i).getName() + " de " + listaDeMusicas.playlist.get(i).getArtists()[0].getName());
		int [] indices = new int[n];
		indices = escolheIndicesDeMusica(n, indices);
		return indices;	
	}

	private int[] escolheIndicesDeMusica(int n, int[] indices) {
		int i;
		System.out.println("Insira os índices das Músicas desejadas, terminados por 0");
		Scanner sc = new Scanner(System.in);
		int j = sc.nextInt();
		for(i = 0; i < n && j != 0; i++) {
			indices[i] = j - 1;
			j = sc.nextInt();
		}
		if(indices.length > i)
			indices[i] = -2;
		return indices;
	}
	
	public PlaylistInterna visualizaPlaylist(String nome) {
		List<PlaylistInterna> lista = this.achaPlaylistsCompatíveisComBusca(nome);
		if(lista.isEmpty()) {
			System.out.println("Nenhuma playlist encontrada, cancelando operação");
			return null;
		}
		System.out.println("Insira o índice da Playlist desejada");
		Scanner sc = new Scanner(System.in);
		int indiceDaPlaylistDesejada = sc.nextInt();
		lista.get(indiceDaPlaylistDesejada - 1).imprimePlaylist();
		return lista.get(indiceDaPlaylistDesejada - 1);
	}

}
