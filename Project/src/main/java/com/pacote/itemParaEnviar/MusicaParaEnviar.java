package com.pacote.itemParaEnviar;
import se.michaelthelin.spotify.model_objects.specification.Track;
public class MusicaParaEnviar {

	private String nome;
	private String autor;
	private String album;
	private String id;
	private String URL;

	public float[] parametros = new float[8]; 
	public String getNome() {
		return nome;
	}
	
	public MusicaParaEnviar(Track track) {
		this.nome = track.getName();
		this.id = track.getId();
		this.autor = track.getArtists()[0].getId();
		this.album = track.getAlbum().getId();
		this.URL = track.getPreviewUrl();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAutor() {
		return autor;
	}

	public String getAlbum() {
		return album;
	}

	public String getId() {
		return id;
	}

	public String getURL() {
		return URL;
	}
	

}
