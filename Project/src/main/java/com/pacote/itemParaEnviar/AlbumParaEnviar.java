package com.pacote.itemParaEnviar;
import se.michaelthelin.spotify.model_objects.specification.Album;
public class AlbumParaEnviar {
	private String nome;
	private String autor;
	private String id;
	public String getNome() {
		return nome;
	}
	
	public AlbumParaEnviar(Album album) {
		this.nome = album.getName();
		this.autor = album.getArtists()[0].getId();
		this.id = album.getId();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAutor() {
		return autor;
	}

	public String getId() {
		return id;
	}
}
