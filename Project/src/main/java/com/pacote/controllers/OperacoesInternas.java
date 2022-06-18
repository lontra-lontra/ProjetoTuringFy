package com.pacote.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.User;

public class OperacoesInternas {
	
	private static User user = BuscadorDoSpotify.getCurrentUsersProfile();
	
	public String getName() {
		return this.nome;
	}
	
	public void setName(String novoNome) {
		this.nome = novoNome;
	}
	
	public List<Track> getPlaylist() {
		return this.playlist;
	}
	
	public void setPlaylist(List<Track> novaPlaylist) {
		this.playlist = novaPlaylist;
	}


	public void adicionaMusica(Track musica) {
		this.playlist.add(musica);
	}
	
	public void removeMusica(Track musica) {
		this.playlist.remove(musica);
	}
	
	public List<Track> BuscaPorNome(String nome) {
		List<Track> listaDeBusca = new ArrayList<>();
		listaDeBusca = this.playlist.stream().filter(musica-> musica.getName().contains(nome)).collect(Collectors.toList());
		return listaDeBusca;
	}
	
	public List<Track> BuscaPeloPrimeiroArtista(String nome, Playlist lista) {
		List<Track> listaDeBusca = new ArrayList<>();
		listaDeBusca = this.playlist.stream().filter(musica-> (musica.getArtists()[0].getName().contains(nome))).collect(Collectors.toList());
		return listaDeBusca;
	}
	
	public List<Track> BuscaPorAlbum(String nome, Playlist lista) {
		List<Track> listaDeBusca = new ArrayList<>();
		listaDeBusca = this.playlist.stream().filter(musica-> musica.getAlbum().getName().contains(nome)).collect(Collectors.toList());
		return listaDeBusca;
	}
	
	public void imprimePlaylist() {
		System.out.println("Playlist: " + this.nome);
		for(Track musica : this.playlist){
			this.imprimeTrack(musica);	
		}
	}
	
	private void imprimeTrack(Track musica) {
		System.out.printf("Musica: %s, Album: %s \nDuração: %d ms, Primeiro Artista: %s \n", musica.getName(), musica.getAlbum().getName(), musica.getDurationMs(), musica.getArtists()[0].getName());		
	}
	
	public OperacoesInternas selecionaPlaylist(String playlist, String musica, List<OperacoesInternas> recebeMusica, Scanner sc){
		if(recebeMusica.isEmpty()) {
			System.out.println("Nenhuma playlist encontrada, cancelando operação");
			return null;
		}
		System.out.println("Insira o índice da Playlist a receber a música");
		int indiceDaPlaylistRecebeMusica = sc.nextInt();
		return this.escolheMusicaDoResultadoDeBusca(recebeMusica, indiceDaPlaylistRecebeMusica, sc);
	}
	
	private OperacoesInternas escolheMusicaDoResultadoDeBusca(List<OperacoesInternas> recebeMusica, int indiceDaPlaylistRecebeMusica, Scanner sc) {
		int[] indiceDaMusicaASerAdicionada = new int[this.playlist.size()];
		for(int i = 0; i < this.playlist.size(); i++)
			System.out.println((i + 1) + " - " + this.playlist.get(i).getName() + " de " + this.playlist.get(i).getArtists()[0].getName());
		indiceDaMusicaASerAdicionada = this.escolheIndicesDeMusica(this.playlist.size(), indiceDaMusicaASerAdicionada, sc);
		for(int i = 0; i < indiceDaMusicaASerAdicionada.length && indiceDaMusicaASerAdicionada[i] != -2; i ++ )
			recebeMusica.get(indiceDaPlaylistRecebeMusica - 1).adicionaMusica(this.playlist.get(indiceDaMusicaASerAdicionada[i]));
		return recebeMusica.get(indiceDaPlaylistRecebeMusica - 1);
	}
	
	public void RemoveMusicaDePlaylist(String musica, Scanner sc){
		int[] indiceDaMusicaASerRemovida = this.buscaMusicaEmPlaylist(musica, sc);
		for(int i = 0; i < indiceDaMusicaASerRemovida.length && indiceDaMusicaASerRemovida[i] != -2; i ++)
			this.playlist.remove(indiceDaMusicaASerRemovida[i]);		
		return;
	}
	

	private int[] buscaMusicaEmPlaylist(String nome, Scanner sc) {
		int i, n = 0;
		System.out.println("Músicas Compatíveis com o Termo de Busca: ");
		for(i = 0; i < this.playlist.size(); i++, n++)
			if(this.playlist.get(i).getName().contains(nome))
				System.out.println((i + 1) + " - " + this.playlist.get(i).getName() + " de " + this.playlist.get(i).getArtists()[0].getName());
		int [] indices = new int[n];
		indices = escolheIndicesDeMusica(n, indices, sc);
		return indices;	
	}

	private int[] escolheIndicesDeMusica(int n, int[] indices, Scanner sc) {
		int i;
		System.out.println("Insira os índices das Músicas desejadas, terminados por 0");
		int j = sc.nextInt();
		for(i = 0; i < n && j != 0; i++) {
			indices[i] = j - 1;
			j = sc.nextInt();
		}
		if(indices.length > i)
			indices[i] = -2;
		return indices;
	}

}
