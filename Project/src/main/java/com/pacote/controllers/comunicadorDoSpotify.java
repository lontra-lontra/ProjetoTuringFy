package src.main.java.com.pacote.controllers;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;

import java.net.URI;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;

// PARA PESQUISAR ALBUNS
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;

// PARA PESQUISAR ARTISTAS
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;

// PARA PESQUISAR PLAYLISTS
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Show;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchPlaylistsRequest;

// PARA EPISODIOS
import se.michaelthelin.spotify.model_objects.specification.EpisodeSimplified;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchEpisodesRequest;

// PARA SHOWS
import se.michaelthelin.spotify.model_objects.specification.ShowSimplified;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchShowsRequest;

// PARA ITENS
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;
import se.michaelthelin.spotify.enums.ModelObjectType;
import java.util.*;

import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URI;



public class comunicadorDoSpotify {
private static final String clientId = "5b1ca7af0e4d44349dc5e7691cb31f9e";
private static final String clientSecret = "a97ac799a878484998b7876f28ec46b9";
private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/red");
private static String codigoDeAutorização = "";




public static void setCodigoDeAutorização(String codigoDeAutorização) {
	comunicadorDoSpotify.codigoDeAutorização = codigoDeAutorização;
}

private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
  .setClientId(clientId)
  .setClientSecret(clientSecret)
  .setRedirectUri(redirectUri)
  .build();
private static final ClientCredentialsRequest RequestDasNossasCredenciais = spotifyApi.clientCredentials()
  .build();


// MÉTODOS DE BUSCA

public static Track[] PesquisaMusicas(String textoDePesquisa) {
  try {
    final ClientCredentials nossasCredenciais = RequestDasNossasCredenciais.execute();

  
    spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
    
    
  } catch (IOException | SpotifyWebApiException | ParseException e) {
   System.out.println("Error: " + e.getMessage());
  }
  /
   final SearchTracksRequest RequestDasMusicasPesquisadas = spotifyApi.searchTracks(textoDePesquisa)
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
	    final ClientCredentials nossasCredenciais = RequestDasNossasCredenciais.execute();

	  
	    spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
	    
	    
	  } catch (IOException | SpotifyWebApiException | ParseException e) {
	   System.out.println("Error: " + e.getMessage());
	  }
	  /
	   final SearchAlbumsRequest RequestDosAlbunsPesquisados = spotifyApi.searchAlbums(nomeDoAlbum)
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
	    final ClientCredentials nossasCredenciais = RequestDasNossasCredenciais.execute();

	  
	    spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
	    
	    
	  } catch (IOException | SpotifyWebApiException | ParseException e) {
	   System.out.println("Error: " + e.getMessage());
	  }
	  /
	   final SearchArtistsRequest RequestDosArtistasPesquisados = spotifyApi.searchArtists(nomeDoArtista)
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
	    final ClientCredentials nossasCredenciais = RequestDasNossasCredenciais.execute();

	  
	    spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
	    
	    
	  } catch (IOException | SpotifyWebApiException | ParseException e) {
	   System.out.println("Error: " + e.getMessage());
	  }
	  /
	   final SearchPlaylistsRequest RequestDasPlaylistsPesquisadas = spotifyApi.searchPlaylists(nomeDaPlaylist)
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
	    final ClientCredentials nossasCredenciais = RequestDasNossasCredenciais.execute();

	  
	    spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
	    
	    
	  } catch (IOException | SpotifyWebApiException | ParseException e) {
	   System.out.println("Error: " + e.getMessage());
	  }
	  /
	   final SearchEpisodesRequest RequestDosEpisodiosPesquisados = spotifyApi.searchEpisodes(nomeDoEpisodio)
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
	    final ClientCredentials nossasCredenciais = RequestDasNossasCredenciais.execute();

	  
	    spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
	    
	    
	  } catch (IOException | SpotifyWebApiException | ParseException e) {
	   System.out.println("Error: " + e.getMessage());
	  }
	  /
	   final SearchShowsRequest RequestDosShowsPesquisados = spotifyApi.searchShows(nomeDoShow)
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
	    final ClientCredentials nossasCredenciais = RequestDasNossasCredenciais.execute();

	  
	    spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
	    
	    
	  } catch (IOException | SpotifyWebApiException | ParseException e) {
	   System.out.println("Error: " + e.getMessage());
	  }
	  /
	  final List <String> lista_tipo = new ArrayList <String> (Arrays.asList(ModelObjectType.ARTIST.getType(), ModelObjectType.ALBUM.getType(),
	                                      ModelObjectType.PLAYLIST.getType(), ModelObjectType.TRACK.getType(),
	                                      ModelObjectType.SHOW.getType(), ModelObjectType.EPISODE.getType()));
	  final String tipo = String.join(",", lista_tipo);
	   final SearchItemRequest RequestDosItensPesquisados = spotifyApi.searchItem(chaveDePesquisa, tipo)
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



// OUTROS MÉTODOS

private static final AuthorizationCodeUriRequest requestDoLinkParaAutorização = spotifyApi.authorizationCodeUri()
//      .state("x4xkmn9pu3j6ukrs8n")
//      .scope("user-read-birthdate,user-read-email")
//      .show_dialog(true)
.build();

public static URI geraLink() {
final URI linkParaAutorização = requestDoLinkParaAutorização.execute();

System.out.println("URI: " + linkParaAutorização.toString());
return linkParaAutorização;
}



public static void pegaAutorizaçãoDeTabela() {
	final AuthorizationCodeRequest requestDoCodigoDeAutorização = spotifyApi.authorizationCode(codigoDeAutorização)
			.build();

try {
  final AuthorizationCodeCredentials authorizationCodeCredentials =  requestDoCodigoDeAutorização.execute();

  
  spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
  spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

  System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
} catch (IOException | SpotifyWebApiException | ParseException e) {
  System.out.println("Error: " + e.getMessage());
}
}


public static void main(String[] args) {
	//PesquisaMúsicas("ALO");
}



public static ClientCredentialsRequest getRequestdasnossascredenciais() {
	return RequestDasNossasCredenciais;
}
}