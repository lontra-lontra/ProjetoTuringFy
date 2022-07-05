package com.pacote.controllers;
import se.michaelthelin.spotify.model_objects.specification.Track;


import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;
public class MusicaParaEnviar {

	public String nome;
	public String autor;
	public String album;
	public String id;

	public float dancabilidade;
	public float energia;
	public float andamento;
	public float forca;
	public float fala;
	public float instrumental;
	public float aovivo;
	public float acustico;
	public float[] parametros = new float[8]; 
	public String getNome() {
		return nome;
	}
	
	public MusicaParaEnviar(Track track) {
		super();
		this.nome = track.getName();
		this.id = track.getId();
		this.autor = track.getArtists()[0].getId();
		this.album = track.getAlbum().getId();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
