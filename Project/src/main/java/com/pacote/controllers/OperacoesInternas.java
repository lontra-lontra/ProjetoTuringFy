package com.pacote.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.User;

public class OperacoesInternas {
	
	private static User user;

	public static void setUser() {
		OperacoesInternas.user = BuscadorDoSpotify.getCurrentUsersProfile();
	}

	private static EditorDePlaylists editor = new EditorDePlaylists();
	private static ConversorDeTipo conversor = new ConversorDeTipo();
	
	public void imprimeListaDeMusicas(List<Track> listaDeMusicas) {		
		List<AudioFeatures> listaDeFt = editor.getAudioFeatures(conversor.getAttributeArray(listaDeMusicas, "id"));
		for (int i = 0; i < listaDeMusicas.size(); i ++) {
			System.out.println( (i + 1) + ". Musica: " + listaDeMusicas.get(i).getName() + ". Primeiro Artista: " + listaDeMusicas.get(i).getArtists()[0]);
			System.out.println("Álbum: " + listaDeMusicas.get(i).getAlbum().getName() + ". Duração: " + listaDeMusicas.get(i).getDurationMs() + " ms.");
			System.out.println("Acousticness: " + listaDeFt.get(i).getAcousticness() + ". Danceability: " + listaDeFt.get(i).getDanceability());
			System.out.println("Energy: " + listaDeFt.get(i).getEnergy() + ". Instrumentalness: " + listaDeFt.get(i).getInstrumentalness());
			System.out.println("Key: " + listaDeFt.get(i).getKey() + ". Liveness: " + listaDeFt.get(i).getLiveness());
			System.out.println("Loudness: " + listaDeFt.get(i).getLoudness() + ". Mode: " + listaDeFt.get(i).getMode());		
			System.out.println("Speechiness: " + listaDeFt.get(i).getSpeechiness() + ". Tempo: " + listaDeFt.get(i).getTempo());
			System.out.println("Positivity: " + listaDeFt.get(i).getValence());
			System.out.println(" ");			
		}
	}
	
	private int[] escolheIndicesDeMusica(int n, int[] indices, Scanner sc) {
		int i;
		System.out.println("Insira os índices das Músicas desejadas, terminados por 0");
		int j = sc.nextInt();
		for(i = 0; i < n && j != 0; i++) {
			indices[i] = j - 1;
			j = sc.nextInt();
		}
		if(indices.length > i)
			indices[i] = -2;
		return indices;
	}

	public Playlist selecionaPlaylist(String nomeDaPlaylist, Scanner sc) {
		Playlist selecionada = null;
		List<Playlist> possiveis = conversor.getFromDifferentType(BuscadorDoSpotify.getListOfUsersPlaylists(user.getId())).stream().filter(playlist -> (playlist.getName().contains(nomeDaPlaylist))).collect(Collectors.toList());
		this.imprimePlaylists(possiveis);
		System.out.println("Insira o índice da Playlist desejada: ");
		selecionada = possiveis.get(sc.nextInt() - 1);		
		return selecionada;
	}

	private void imprimePlaylists(List<Playlist> possiveis) {
		int i = 1;
		System.out.println("Playlists Encontradas: ");
		for(Playlist lista : possiveis) {
			System.out.println(i + ". " + lista.getName());
			i++;
		}
	}

	public List<Track> selecionaMusicas(List<Track> lista, String nomeMusica, Scanner sc) {
		List<Track> soEscolhidas = new ArrayList<>();
		this.imprimeListaDeMusicas(lista.stream().filter(musica -> musica.getName().contains(nomeMusica)).collect(Collectors.toList()));
		int tam = lista.stream().filter(musica -> musica.getName().contains(nomeMusica)).collect(Collectors.toList()).size();
		int[] indicesDesejados = new int[tam];
		indicesDesejados = this.escolheIndicesDeMusica(tam, indicesDesejados, sc);
		soEscolhidas = this.separaMusicasDeLista(lista, indicesDesejados);		
		return soEscolhidas;
	}

	private List<Track> separaMusicasDeLista(List<Track> lista, int[] indicesDesejados) {
		List<Track> auxiliar = new ArrayList<Track>();
		for(int i = 0; i < indicesDesejados.length && indicesDesejados[i] != -2; i++) {
			auxiliar.add(lista.get(indicesDesejados[i]));
		}
		return auxiliar;
	}

	public void adicionaMusicaAPlaylist(List<Track> musicasParaAdicionar, Playlist listaDoUsuario) {
		editor.adicionaMusica(listaDoUsuario, conversor.getAttributeArray(musicasParaAdicionar, "uri"));		
	}

	public void imprimePlaylistsDoUsuario() {
		List<Playlist> listasDoUsuario = conversor.getFromDifferentType(BuscadorDoSpotify.getListOfUsersPlaylists(user.getId()));
		this.imprimePlaylists(listasDoUsuario);		
	}

	public void alteraNomeDePlaylist(Playlist listaDoUsuario, String nextLine) {
		editor.changePlaylistName(nextLine, listaDoUsuario.getId());
	}

	public void criaPlaylistDoUsuario(String nomeDaPlaylist) {
		editor.createUsersPlaylist(nomeDaPlaylist, user.getId());
	}

	public void deletaPlaylistDoUsuario(Playlist listaDoUsuario) {
		editor.deleteUsersPlaylist(listaDoUsuario.getId());
	}

	public void removeMusicaDaPlaylist(Playlist playlistDesejada, String nomeDaMusica, Scanner sc) {
		List<Track> escolhidas = this.selecionaMusicas(conversor.getFromDifferentType(playlistDesejada.getTracks().getItems()), nomeDaMusica, sc);
		JsonArray musicasURI = new JsonArray();
		for (Track musica : escolhidas) {
			JsonElement novo = JsonParser.parseString(musica.getUri());
			musicasURI.add(novo);
		}
		editor.removeMusica(playlistDesejada, musicasURI);
	}


}
