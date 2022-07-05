package com.pacote.controllers;

import se.michaelthelin.spotify.model_objects.specification.Artist;

public class ArtistaParaEnviar {
	private String nome;
	private double followers; 
	private String id;
	
	public String getNome() {
		return nome;
	}
	
	public ArtistaParaEnviar(Artist artista) {
		this.id = artista.getId();
		this.nome = artista.getName();
		this.followers = artista.getFollowers().getTotal();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getFollowers() {
		return followers;
	}

	public String getId() {
		return id;
	}

}
