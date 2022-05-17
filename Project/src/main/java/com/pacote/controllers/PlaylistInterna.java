package com.pacote.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.Track;

public class PlaylistInterna {
	List<Track> playlist;
	String nome;
	
	public void adicionaMusica(Track musica) {
		this.playlist.add(musica);
	}
	
	public void removeMusica(Track musica) {
		this.playlist.remove(musica);
	}
	
	public PlaylistInterna alteraNome(String novoNome) {
		this.nome = novoNome;
		return this;
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
	
	public void imprimeTrack(Track musica) {
		System.out.printf("Musica: %s, Album: %s \n Duração: %d ms, Primeiro Artista: %s", musica.getName(), musica.getAlbum().getName(), musica.getDurationMs(), musica.getArtists()[0].getName());		
	}

}
