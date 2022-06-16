package com.pacote.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistRequest;

public class GetItemFromSimplifiedType {
	
	public List<Playlist> getFromDifferentType (PlaylistSimplified[] listaDePlaylistsSimpl) {
		List<Playlist> listaDePlaylists = new ArrayList<>();
		try {
		for(PlaylistSimplified playlist : listaDePlaylistsSimpl) {
			GetPlaylistRequest getPlaylistRequest = ComunicadorDoSpotify.getSpotifyapi().getPlaylist(playlist.getId()).build();
			listaDePlaylists.add(getPlaylistRequest.execute());
		}
		
		} catch (ParseException | SpotifyWebApiException | IOException e) {
				System.out.println("Não foi possível encontrar a Playlist: " + e);
	    }
		return listaDePlaylists;
	}
	
	public List<Album> getFromDifferentType (AlbumSimplified[] listaDeAlbunsSimpl){
		List<Album> listaDeAlbuns = new ArrayList<>();
		try {
			for(AlbumSimplified album : listaDeAlbunsSimpl) {
				GetAlbumRequest getAlbumRequest = ComunicadorDoSpotify.getSpotifyapi().getAlbum(album.getId()).build();
				listaDeAlbuns.add(getAlbumRequest.execute());
			}
			
			} catch (ParseException | SpotifyWebApiException | IOException e) {
					System.out.println("Não foi possível encontrar o Album: " + e);
		    }		
		return listaDeAlbuns;		
		
	}
	
	public List<Track> getFromDifferentType(PlaylistTrack[] listaDeMusicas){
		List<Track> listaConvertida = new ArrayList<>();
		for(PlaylistTrack musica : listaDeMusicas) {
			listaConvertida.add((Track) musica.getTrack());	
		}
		return listaConvertida;
	}

}
