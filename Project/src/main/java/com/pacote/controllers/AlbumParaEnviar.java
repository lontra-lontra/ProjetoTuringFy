package com.pacote.controllers;
import se.michaelthelin.spotify.model_objects.specification.Album;
public class AlbumParaEnviar {
	public String nome;
	public String autor;
	public String getNome() {
		return nome;
	}
	
	public AlbumParaEnviar(Album album) {
		super();
		this.nome = album.getName();
		this.autor = album.getArtists()[0].getId();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
