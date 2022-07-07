package com.pacote.operacoesTerminal;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.hc.core5.http.ParseException;

import com.neovisionaries.i18n.CountryCode;

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
import se.michaelthelin.spotify.requests.data.artists.GetArtistsAlbumsRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsTopTracksRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchEpisodesRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchShowsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;
import se.michaelthelin.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

public class BuscadorDoSpotify {
	
	public static Track[] pesquisaMusicas(String textoDePesquisa) {
		   SearchTracksRequest RequestDasMusicasPesquisadas = ComunicadorDoSpotify.getSpotifyapi().searchTracks(textoDePesquisa)
				   .limit(20)
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
					  .limit(20)
					  .build();
			  try {
			      final Paging<AlbumSimplified> AlbunsPesquisados = RequestDosAlbunsPesquisados.execute();

			      return AlbunsPesquisados.getItems();
			    } catch (IOException | SpotifyWebApiException | ParseException e) {
			      System.out.println("Error: " + e.getMessage());
			    }
			  	return null;  
			}

		public static Artist[] pesquisaArtistas(String nomeDoArtista) {
			 SearchArtistsRequest RequestDosArtistasPesquisados = ComunicadorDoSpotify.getSpotifyapi().searchArtists(nomeDoArtista)
					 .limit(20)
					 .build();
			  try {
			      final Paging<Artist> ArtistasPesquisados = RequestDosArtistasPesquisados.execute();

			      return ArtistasPesquisados.getItems();
			    } catch (IOException | SpotifyWebApiException | ParseException e) {
			      System.out.println("Error: " + e.getMessage());
			    }
			  	return null;
			}

		public static PlaylistSimplified[] pesquisaPlaylists(String nomeDaPlaylist) {
			  SearchPlaylistsRequest RequestDasPlaylistsPesquisadas = ComunicadorDoSpotify.getSpotifyapi().searchPlaylists(nomeDaPlaylist)
					  .limit(20)
					  .build();
			  try {
			      final Paging<PlaylistSimplified> PlaylistsPesquisadas = RequestDasPlaylistsPesquisadas.execute();

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
					    .limit(20)
					    .build();
			 
			 Paging<PlaylistSimplified> playlistSimplifiedPaging = null;
			 try {
			      playlistSimplifiedPaging = getListOfUsersPlaylistsRequest.execute();
			    } catch (IOException | SpotifyWebApiException | ParseException e) {
			    	if(!e.getMessage().equals("Invalid limit"))
			    		System.out.println("Error: " + e.getMessage());
			    }
			 if(playlistSimplifiedPaging == null) {
				 System.out.println("Não há Playlists salvas!");
				 return null;
			 }
			 return playlistSimplifiedPaging.getItems();
		 }
		 
		 public static User getCurrentUsersProfile() {
			 System.out.println("chegou1");
			 GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = ComunicadorDoSpotify.getSpotifyapi().getCurrentUsersProfile()
					    .build();
			 System.out.println("chegou2");
			 User user = null;
			  try {
			      user = getCurrentUsersProfileRequest.execute();
			      System.out.println("Display name: " + user.getDisplayName());
			    } catch (IOException | SpotifyWebApiException | ParseException e) {
			      System.out.println("Error: " + e.getMessage());
			    }

			  System.out.println("chegou3");
			  return user;
		 }

		public static Track getMusica(String id) {
			GetTrackRequest getTrackRequest = ComunicadorDoSpotify.getSpotifyapi().getTrack(id)
		    .build();
			try {
			      final Track track = getTrackRequest.execute();
			      return track;
			    } catch (IOException | SpotifyWebApiException | ParseException e) {
			      System.out.println("Error: " + e.getMessage());
			    }
			return null;
		}
		
		public static List<Track> getArtistTopTracks(Artist artist){
			GetArtistsTopTracksRequest getArtistsTopTracksRequest = ComunicadorDoSpotify.getSpotifyapi()
				    .getArtistsTopTracks(artist.getId(), CountryCode.BR)
				    .build();
			  try {
			      Track[] tracks = getArtistsTopTracksRequest.execute();
			      return Arrays.asList(tracks);
			    } catch (IOException | SpotifyWebApiException | ParseException e) {
			      System.out.println("Error: " + e.getMessage());
			    }
			  
			  return null;
		}

		public static AlbumSimplified[] getArtistAlbuns(Artist artist) {
			GetArtistsAlbumsRequest getArtistsAlbumsRequest = ComunicadorDoSpotify.getSpotifyapi().getArtistsAlbums(artist.getId())
		          .limit(20)
		    .build();
			try {
			      final Paging<AlbumSimplified> albumSimplifiedPaging = getArtistsAlbumsRequest.execute();
			      return albumSimplifiedPaging.getItems();
			    } catch (IOException | SpotifyWebApiException | ParseException e) {
			      System.out.println("Error: " + e.getMessage());
			    }			
			return null;
		}
		 
}
