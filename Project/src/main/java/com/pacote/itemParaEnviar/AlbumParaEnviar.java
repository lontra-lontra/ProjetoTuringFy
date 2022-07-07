package com.pacote.itemParaEnviar;
import se.michaelthelin.spotify.model_objects.specification.Album;
public class AlbumParaEnviar {
	public String nome;
	public String autor;
	public String id;
	public String getNome() {
		return nome;
	}
	
	public AlbumParaEnviar(Album album) {
		this.id = album.getId();
		this.nome = album.getName();
		this.autor = album.getArtists()[0].getId();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
