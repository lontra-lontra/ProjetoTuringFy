package com.pacote.controllers;
import se.michaelthelin.spotify.model_objects.specification.Track;
public class MusicaParaEnviar {
	public String nome;
	public String autor;
	public String album;
	public String getNome() {
		return nome;
	}
	
	public MusicaParaEnviar(Track track) {
		super();
		this.nome = track.getName();
		this.autor = track.getArtists()[0].getId();
		this.album = track.getAlbum().getId();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
