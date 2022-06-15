package com.pacote.controllers;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BibliotecaDePlaylists {
	private List<PlaylistInterna> biblioteca;
	
	public BibliotecaDePlaylists() {
		this.biblioteca = new ArrayList<PlaylistInterna>();
	}
	
	public PlaylistInterna criaPlaylist(String nome) {
		PlaylistInterna novaPlaylist = new PlaylistInterna("nome");
		return novaPlaylist;
	}
	
	public boolean removePlaylist(String nome, Scanner sc) {
		List<PlaylistInterna> listaDeRemovíveis = new ArrayList<>();
		listaDeRemovíveis = achaPlaylistsCompatíveisComBusca(nome);
		if(this.enviaMensagemSeVazia(listaDeRemovíveis)) 
			return false;
		this.recolheIndicesDePlaylists(sc, listaDeRemovíveis);
		return true;
	}

	private void recolheIndicesDePlaylists(Scanner sc, List<PlaylistInterna> listaDeRemovíveis) {
		System.out.println("Insira os índices das Playlists a serem removidas, termine a sequência com 0.");
		int n = sc.nextInt();
		while( n != 0) {
			this.biblioteca.remove(listaDeRemovíveis.get(n - 1));
			n = sc.nextInt();
		}
	}

	public List<PlaylistInterna> achaPlaylistsCompatíveisComBusca(String nome) {
		List<PlaylistInterna> listaDeCompatíveis;
		listaDeCompatíveis = this.biblioteca.stream().filter(playlist-> (playlist.getName().contains(nome))).collect(Collectors.toList());
		if(!listaDeCompatíveis.isEmpty()) {
			System.out.println("Resultados Compatíveis: ");
			for (int i = 0; i < listaDeCompatíveis.size(); i ++)
				System.out.println((i + 1) + " - " + listaDeCompatíveis.get(i).getName());
		}
		return listaDeCompatíveis;
	}
	
	public PlaylistInterna visualizaPlaylist(String nome, Scanner sc) {
		List<PlaylistInterna> lista = this.achaPlaylistsCompatíveisComBusca(nome);
		if(this.enviaMensagemSeVazia(lista))
			return null;
		System.out.println("Insira o índice da Playlist desejada");
		int indiceDaPlaylistDesejada = sc.nextInt();
		lista.get(indiceDaPlaylistDesejada - 1).imprimePlaylist();
		return lista.get(indiceDaPlaylistDesejada - 1);
	}
	
	public boolean enviaMensagemSeVazia(List<PlaylistInterna> lista) {
		if(lista.isEmpty()) {
			System.out.println("Nenhuma playlist encontrada, cancelando operação.");
			return true;
		}
		return false;
	}

}
