package com.pacote.controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.core5.http.ParseException;

import com.google.gson.JsonArray;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.ChangePlaylistsDetailsRequest;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.playlists.RemoveItemsFromPlaylistRequest;
import se.michaelthelin.spotify.requests.data.follow.UnfollowPlaylistRequest;



public class EditorDePlaylists {
	
	public List<Playlist> getUsersPlaylists(){
		CoversorDeTipo conversor = new CoversorDeTipo();
		List<Playlist> playlistsDoUsuário = new ArrayList<>();
		
		GetListOfCurrentUsersPlaylistsRequest getListOfCurrentUsersPlaylistsRequest = ComunicadorDoSpotify.getSpotifyapi()
			    .getListOfCurrentUsersPlaylists().build();
		try {
			playlistsDoUsuário = conversor.getFromDifferentType(getListOfCurrentUsersPlaylistsRequest.execute().getItems());
		} catch (ParseException | SpotifyWebApiException | IOException e) {
			System.out.println("Playlist não encontrada: " + e);
		}
		return playlistsDoUsuário;
	}
	
	public void deleteUsersPlaylist(String playlistID) {
		try {
		    final ClientCredentials nossasCredenciais = ComunicadorDoSpotify.RequestDasNossasCredenciais.execute();
		    UnfollowPlaylistRequest.Builder construtor = new UnfollowPlaylistRequest.Builder(nossasCredenciais.getAccessToken());
		    construtor.playlist_id(playlistID);
		    UnfollowPlaylistRequest removePlaylist = construtor.build();
		    removePlaylist.execute();		    
		  } catch (IOException | SpotifyWebApiException | ParseException e) {
		   System.out.println("Error: " + e.getMessage());
		  }		
	}
	
	public void createUsersPlaylist(String playlistName, String userID) {
		CreatePlaylistRequest createPlaylistRequest = ComunicadorDoSpotify.getSpotifyapi().createPlaylist(userID, playlistName).build();
		 try {
		      createPlaylistRequest.execute();
		    } catch (IOException | SpotifyWebApiException | ParseException e) {
		      System.out.println("Error: " + e.getMessage());
		    }	
	}
	
	public void changePlaylistName(String newName, String playlistID) {
		ChangePlaylistsDetailsRequest changePlaylistsDetailsRequest = ComunicadorDoSpotify.getSpotifyapi()
			    .changePlaylistsDetails(playlistID).name(newName).build();
		try {
		      changePlaylistsDetailsRequest.execute();
		    } catch (IOException | SpotifyWebApiException | ParseException e) {
		      System.out.println("Error: " + e.getMessage());
		    }
		
	}
	
	public void visualizaPlaylists(List<Playlist> playlistsDoUsuário) {
		if(playlistsDoUsuário.isEmpty()) {
			System.out.println("Sem Playlists Salvas!");
		}
		for(Playlist lista : playlistsDoUsuário) {
			System.out.println("Nome: " + lista.getName() + ".");
		}
		
	}
	
	public void vizualizaPlaylist(Playlist lista) {
		CoversorDeTipo conversor = new CoversorDeTipo();
		System.out.println("Playlist: " + lista.getName());
		if(lista.getTracks().getItems().length == 0) {
			System.out.println("Playlist Vazia");
			return;
		}
		for(Track musica : conversor.getFromDifferentType(lista.getTracks().getItems())) {
			System.out.println("Nome: " + musica.getName() + ". Primeiro Artista: " + musica.getArtists()[0]);
		}
		
	}
	
	public void removeMusica(Playlist lista, JsonArray tracks) {	
		RemoveItemsFromPlaylistRequest removeItemsFromPlaylistRequest = ComunicadorDoSpotify.getSpotifyapi()
			    .removeItemsFromPlaylist(lista.getId(), tracks)
			    .build();
		
		try {
		      removeItemsFromPlaylistRequest.execute();
		    } catch (IOException | SpotifyWebApiException | ParseException e) {
		      System.out.println("Não foi possível remover a música: " + e);
		    }
		
	}
	
	public void adicionaMusica(Playlist lista, String[] musicas) {
		AddItemsToPlaylistRequest addItemsToPlaylistRequest = ComunicadorDoSpotify.getSpotifyapi()
			    .addItemsToPlaylist(lista.getId(), musicas).build();
		
		 try {
		      addItemsToPlaylistRequest.execute();
		    } catch (IOException | SpotifyWebApiException | ParseException e) {
			  System.out.println("Não foi possível adicionar a música: " + e);		    }
	}

}
