package com.pacote.itemParaEnviar;
import se.michaelthelin.spotify.model_objects.specification.Track;
public class MusicaParaEnviar {

	private String nome;
	private String autor;
	private String album;
	private String albumName;
	private String autorName;
	private String id;
	private String URL;
	private String uri;

	public float[] parametros = new float[11]; 
	public String getNome() {
		return nome;
	}
	
	public MusicaParaEnviar(Track track) {
		this.nome = track.getName();
		this.id = track.getId();
		this.autor = track.getArtists()[0].getId();
		this.album = track.getAlbum().getId();
		this.URL = track.getPreviewUrl();
		this.uri = track.getUri();
		this.autorName = track.getArtists()[0].getName();
		this.albumName = track.getAlbum().getName();
	}
	
	
	public void setAlbumName(String nome) {
		this.albumName = nome;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAutorName(String nome) {
		this.autorName = nome;
	}

	public String getAutorName() {
		return autorName;
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

	public String getUri() {
		return uri;
	}
	

}
