package com.pacote.controllers;

import se.michaelthelin.spotify.model_objects.specification.Image;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.Track;

public class PlaylistParaEnviar {
	private String nome;
	private Image[] images;
	private String id;
	private Track[] musicasDaPlaylist;
	
	public Image[] getImages() {
		return images;
	}
	public Track[] getMusicasDaPlaylist() {
		return musicasDaPlaylist;
	}
	public String getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	
	public PlaylistParaEnviar(Playlist lista) {
		this.nome = lista.getName();
		this.images = lista.getImages();
		this.id = lista.getId();
		ConversorDeTipo conversor = new ConversorDeTipo();
		this.musicasDaPlaylist = new Track[lista.getTracks().getItems().length];
		this.musicasDaPlaylist = conversor.getFromDifferentType(lista.getTracks().getItems()).toArray(this.musicasDaPlaylist);
	}
	
	
	

}
