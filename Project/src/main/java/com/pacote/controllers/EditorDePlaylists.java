package com.pacote.controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.playlists.ChangePlaylistsDetailsRequest;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.follow.legacy.UnfollowPlaylistRequest;



public class EditorDePlaylists {
	
	public List<Playlist> getUsersPlaylists(){
		GetItemFromSimplifiedType conversor = new GetItemFromSimplifiedType();
		List<Playlist> playlistsDoUsuário = new ArrayList<>();
		
		GetListOfCurrentUsersPlaylistsRequest getListOfCurrentUsersPlaylistsRequest = ComunicadorDoSpotify.spotifyApi
			    .getListOfCurrentUsersPlaylists().build();
		try {
			playlistsDoUsuário = conversor.getFromSimplified(getListOfCurrentUsersPlaylistsRequest.execute().getItems());
		} catch (ParseException | SpotifyWebApiException | IOException e) {
			System.out.println("Playlist não encontrada: " + e);
		}
		return playlistsDoUsuário;
		
	}
	
	public void deleteUsersPlaylist(String playlistID) {
		try {
		    final ClientCredentials nossasCredenciais = ComunicadorDoSpotify.RequestDasNossasCredenciais.execute();
		    UnfollowPlaylistRequest.Builder construtor = new UnfollowPlaylistRequest.Builder(nossasCredenciais.getAccessToken());
		    construtor.owner_id(ComunicadorDoSpotify.spotifyApi.getClientId());
		    construtor.playlist_id(playlistID);
		    UnfollowPlaylistRequest removePlaylist = construtor.build();
		    removePlaylist.execute();		    
		  } catch (IOException | SpotifyWebApiException | ParseException e) {
		   System.out.println("Error: " + e.getMessage());
		  }		
	}
	
	public void createUsersPlaylist(String playlistName) {
		CreatePlaylistRequest createPlaylistRequest = ComunicadorDoSpotify.spotifyApi.createPlaylist(ComunicadorDoSpotify.spotifyApi.getClientId(), playlistName).build();
		 try {
		      createPlaylistRequest.execute();
		    } catch (IOException | SpotifyWebApiException | ParseException e) {
		      System.out.println("Error: " + e.getMessage());
		    }	
	}
	
	public void changePlaylistName(String newName, String playlistID) {
		ChangePlaylistsDetailsRequest changePlaylistsDetailsRequest = ComunicadorDoSpotify.spotifyApi
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
		GetItemFromSimplifiedType conversor = new GetItemFromSimplifiedType();
		System.out.println("Playlist: " + lista.getName());
		if(lista.getTracks().getItems().length == 0) {
			System.out.println("Playlist Vazia");
			return;
		}
		for(Track musica : conversor.getFromSimplified(lista.getTracks().getItems())) {
			System.out.println("Nome: " + musica.getName() + ". Primeiro Artista: " + musica.getArtists()[0]);
		}
		
	}

}
