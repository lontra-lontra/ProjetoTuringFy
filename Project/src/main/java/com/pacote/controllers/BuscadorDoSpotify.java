package com.pacote.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.EpisodeSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.model_objects.specification.ShowSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchEpisodesRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchShowsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

public class BuscadorDoSpotify {
	
	public static Track[] PesquisaMusicas(String textoDePesquisa) {
		  try {
		    final ClientCredentials nossasCredenciais = ComunicadorDoSpotify.RequestDasNossasCredenciais.execute();

		  
		    ComunicadorDoSpotify.spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
		    
		    
		  } catch (IOException | SpotifyWebApiException | ParseException e) {
		   System.out.println("Error: " + e.getMessage());
		  }
		   final SearchTracksRequest RequestDasMusicasPesquisadas = ComunicadorDoSpotify.spotifyApi.searchTracks(textoDePesquisa)
		         .limit(10)
		   .build();
		  
		  
		  try {
		      final Paging<Track> MusicasPesquisadas = RequestDasMusicasPesquisadas.execute();

		      System.out.println("Total: " + MusicasPesquisadas.getTotal());
		      return MusicasPesquisadas.getItems();
		    } catch (IOException | SpotifyWebApiException | ParseException e) {
		      System.out.println("Error: " + e.getMessage());
		    }
		  	return null;
		  
		  
		}

		public static AlbumSimplified[] PesquisAlbuns(String nomeDoAlbum) {
			  try {
			    final ClientCredentials nossasCredenciais = ComunicadorDoSpotify.RequestDasNossasCredenciais.execute();

			  
			    ComunicadorDoSpotify.spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
			    
			    
			  } catch (IOException | SpotifyWebApiException | ParseException e) {
			   System.out.println("Error: " + e.getMessage());
			  }
			   final SearchAlbumsRequest RequestDosAlbunsPesquisados = ComunicadorDoSpotify.spotifyApi.searchAlbums(nomeDoAlbum)
			         .limit(20)
			   .build();
			  
			  
			  try {//VER OQ EH MELHOR DEPOIS - ALBUMSIMPLIFIED OU ALBUM
			      final Paging<AlbumSimplified> AlbunsPesquisados = RequestDosAlbunsPesquisados.execute();

			      System.out.println("Total: " + AlbunsPesquisados.getTotal());
			      return AlbunsPesquisados.getItems();
			    } catch (IOException | SpotifyWebApiException | ParseException e) {
			      System.out.println("Error: " + e.getMessage());
			    }
			  	return null;
			  
			  
			}

		public static Artist[] PesquisaArtistas(String nomeDoArtista) {
			  try {
			    final ClientCredentials nossasCredenciais = ComunicadorDoSpotify.RequestDasNossasCredenciais.execute();

			  
			    ComunicadorDoSpotify.spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
			    
			    
			  } catch (IOException | SpotifyWebApiException | ParseException e) {
			   System.out.println("Error: " + e.getMessage());
			  }
			   final SearchArtistsRequest RequestDosArtistasPesquisados = ComunicadorDoSpotify.spotifyApi.searchArtists(nomeDoArtista)
			         .limit(20)
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

		public static PlaylistSimplified[] PesquisaPlaylists(String nomeDaPlaylist) {
			  try {
			    final ClientCredentials nossasCredenciais = ComunicadorDoSpotify.RequestDasNossasCredenciais.execute();

			  
			    ComunicadorDoSpotify.spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
			    
			    
			  } catch (IOException | SpotifyWebApiException | ParseException e) {
			   System.out.println("Error: " + e.getMessage());
			  }
			   final SearchPlaylistsRequest RequestDasPlaylistsPesquisadas = ComunicadorDoSpotify.spotifyApi.searchPlaylists(nomeDaPlaylist)
			         .limit(20)
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

		public static EpisodeSimplified[] PesquisaEpisodios(String nomeDoEpisodio) {
			  try {
			    final ClientCredentials nossasCredenciais = ComunicadorDoSpotify.RequestDasNossasCredenciais.execute();

			  
			    ComunicadorDoSpotify.spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
			    
			    
			  } catch (IOException | SpotifyWebApiException | ParseException e) {
			   System.out.println("Error: " + e.getMessage());
			  }
			   final SearchEpisodesRequest RequestDosEpisodiosPesquisados = ComunicadorDoSpotify.spotifyApi.searchEpisodes(nomeDoEpisodio)
			         .limit(20)
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

		public static ShowSimplified[] PesquisaShows(String nomeDoShow) {
			  try {
			    final ClientCredentials nossasCredenciais = ComunicadorDoSpotify.RequestDasNossasCredenciais.execute();

			  
			    ComunicadorDoSpotify.spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
			    
			    
			  } catch (IOException | SpotifyWebApiException | ParseException e) {
			   System.out.println("Error: " + e.getMessage());
			  }
			   final SearchShowsRequest RequestDosShowsPesquisados = ComunicadorDoSpotify.spotifyApi.searchShows(nomeDoShow)
			         .limit(20)
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

		public static SearchResult PesquisaItens(String chaveDePesquisa) {
			  try {
			    final ClientCredentials nossasCredenciais = ComunicadorDoSpotify.RequestDasNossasCredenciais.execute();

			  
			    ComunicadorDoSpotify.spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
			    
			    
			  } catch (IOException | SpotifyWebApiException | ParseException e) {
			   System.out.println("Error: " + e.getMessage());
			  }
			  final List <String> lista_tipo = new ArrayList <String> (Arrays.asList(ModelObjectType.ARTIST.getType(), ModelObjectType.ALBUM.getType(),
			                                      ModelObjectType.PLAYLIST.getType(), ModelObjectType.TRACK.getType(),
			                                      ModelObjectType.SHOW.getType(), ModelObjectType.EPISODE.getType()));
			  final String tipo = String.join(",", lista_tipo);
			   final SearchItemRequest RequestDosItensPesquisados = ComunicadorDoSpotify.spotifyApi.searchItem(chaveDePesquisa, tipo)
			         .limit(20)
			   .build();
			  
			  
			  try {
			      final SearchResult ItensPesquisados = RequestDosItensPesquisados.execute();

			      System.out.println("Total: " + ItensPesquisados.getTracks().getTotal());
			      return ItensPesquisados; //getAlbuns, getArtists, get...
			    } catch (IOException | SpotifyWebApiException | ParseException e) {
			      System.out.println("Error: " + e.getMessage());
			    }
			  	return null;
			  
			  
			}



}
