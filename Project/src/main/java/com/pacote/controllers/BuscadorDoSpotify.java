package com.pacote.controllers;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.EpisodeSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.model_objects.specification.ShowSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchEpisodesRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchShowsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import se.michaelthelin.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

public class BuscadorDoSpotify {
	
	public static Track[] pesquisaMusicas(String textoDePesquisa) {
		   SearchTracksRequest RequestDasMusicasPesquisadas = ComunicadorDoSpotify.getSpotifyapi().searchTracks(textoDePesquisa)
		   .build();
		  try {
		      final Paging<Track> MusicasPesquisadas = RequestDasMusicasPesquisadas.execute();
		      return MusicasPesquisadas.getItems();
		    } catch (IOException | SpotifyWebApiException | ParseException e) {
		      System.out.println("Error: " + e.getMessage());
		    }
		  	return null;
		}

		public static AlbumSimplified[] pesquisaAlbuns(String nomeDoAlbum) {
			  SearchAlbumsRequest RequestDosAlbunsPesquisados = ComunicadorDoSpotify.getSpotifyapi().searchAlbums(nomeDoAlbum)
			   .build();
			  try {
			      final Paging<AlbumSimplified> AlbunsPesquisados = RequestDosAlbunsPesquisados.execute();

			      System.out.println("Total: " + AlbunsPesquisados.getTotal());
			      return AlbunsPesquisados.getItems();
			    } catch (IOException | SpotifyWebApiException | ParseException e) {
			      System.out.println("Error: " + e.getMessage());
			    }
			  	return null;  
			}

		public static Artist[] pesquisaArtistas(String nomeDoArtista) {
			 SearchArtistsRequest RequestDosArtistasPesquisados = ComunicadorDoSpotify.getSpotifyapi().searchArtists(nomeDoArtista)
			   .build();
			  try {
			      final Paging<Artist> ArtistasPesquisados = RequestDosArtistasPesquisados.execute();

			      System.out.println("Total: " + ArtistasPesquisados.getTotal());
			      return ArtistasPesquisados.getItems();
			    } catch (IOException | SpotifyWebApiException | ParseException e) {
			      System.out.println("Error: " + e.getMessage());
			    }
			  	return null;
			}

		public static PlaylistSimplified[] pesquisaPlaylists(String nomeDaPlaylist) {
			  SearchPlaylistsRequest RequestDasPlaylistsPesquisadas = ComunicadorDoSpotify.getSpotifyapi().searchPlaylists(nomeDaPlaylist)
			   .build();
			  try {
			      final Paging<PlaylistSimplified> PlaylistsPesquisadas = RequestDasPlaylistsPesquisadas.execute();

			      System.out.println("Total: " + PlaylistsPesquisadas.getTotal());
			      return PlaylistsPesquisadas.getItems();
			    } catch (IOException | SpotifyWebApiException | ParseException e) {
			      System.out.println("Error: " + e.getMessage());
			    }
			  	return null;
			}

		public static EpisodeSimplified[] pesquisaEpisodios(String nomeDoEpisodio) {
			  SearchEpisodesRequest RequestDosEpisodiosPesquisados = ComunicadorDoSpotify.getSpotifyapi().searchEpisodes(nomeDoEpisodio)
			   .build();
			  try {
			      final Paging<EpisodeSimplified> EpisodiosPesquisados = RequestDosEpisodiosPesquisados.execute();

			      System.out.println("Total: " + EpisodiosPesquisados.getTotal());
			      return EpisodiosPesquisados.getItems();
			    } catch (IOException | SpotifyWebApiException | ParseException e) {
			      System.out.println("Error: " + e.getMessage());
			    }
			  	return null;
			}

		public static ShowSimplified[] pesquisaShows(String nomeDoShow) {
			  SearchShowsRequest RequestDosShowsPesquisados = ComunicadorDoSpotify.getSpotifyapi().searchShows(nomeDoShow)
			   .build();  
			  try {
			      final Paging<ShowSimplified> ShowsPesquisados = RequestDosShowsPesquisados.execute();

			      System.out.println("Total: " + ShowsPesquisados.getTotal());
			      return ShowsPesquisados.getItems();
			    } catch (IOException | SpotifyWebApiException | ParseException e) {
			      System.out.println("Error: " + e.getMessage());
			    }
			  	return null;
			}

		public static SearchResult pesquisaItens(String chaveDePesquisa, String tipo) {
			   SearchItemRequest RequestDosItensPesquisados = ComunicadorDoSpotify.getSpotifyapi().searchItem(chaveDePesquisa, tipo)
			   .build();
			  try {
			      final SearchResult ItensPesquisados = RequestDosItensPesquisados.execute();
			      return ItensPesquisados;
			    } catch (IOException | SpotifyWebApiException | ParseException e) {
			      System.out.println("Error: " + e.getMessage());
			    }
			  	return null;  
			}
		
		
		 public static PlaylistSimplified[] getListOfUsersPlaylists(String userId) {
			 GetListOfUsersPlaylistsRequest getListOfUsersPlaylistsRequest = ComunicadorDoSpotify.getSpotifyapi()
					    .getListOfUsersPlaylists(userId)
					    .build();
			 
			 Paging<PlaylistSimplified> playlistSimplifiedPaging = null;
			 try {
			      playlistSimplifiedPaging = getListOfUsersPlaylistsRequest.execute();

			      System.out.println("Total: " + playlistSimplifiedPaging.getTotal());
			    } catch (IOException | SpotifyWebApiException | ParseException e) {
			      System.out.println("Error: " + e.getMessage());
			    }
			 return playlistSimplifiedPaging.getItems();
		 }
		 
		 public static User getCurrentUsersProfile() {
			 GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = ComunicadorDoSpotify.getSpotifyapi().getCurrentUsersProfile()
					    .build();
			 User user = null;
			  try {
			      user = getCurrentUsersProfileRequest.execute();

			      System.out.println("Display name: " + user.getDisplayName());
			    } catch (IOException | SpotifyWebApiException | ParseException e) {
			      System.out.println("Error: " + e.getMessage());
			    }
			  return user;
		 }
}
