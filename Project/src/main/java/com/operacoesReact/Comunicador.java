package com.operacoesReact;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import se.michaelthelin.spotify.requests.data.albums.GetAlbumRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsTopTracksRequest;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetAudioFeaturesForSeveralTracksRequest;
import se.michaelthelin.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.User;

import org.apache.hc.core5.http.ParseException;

import com.neovisionaries.i18n.CountryCode;
import com.pacote.operacoesTerminal.ComunicadorDoSpotify;

import java.io.IOException;



public class Comunicador {
private static final String clientId = "996174542561436ead6fda89541d0083";
private static final String clientSecret = "cffbb71c871d4a2088f069137c43064f";
private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/red");
private static String codigoDeAutorização = "";


public static Playlist[] getCurrentUsersPlaylist() {
	ConversorDeTipo conversor = new ConversorDeTipo();
	GetListOfCurrentUsersPlaylistsRequest getListOfCurrentUsersPlaylistsRequest = spotifyApi.getListOfCurrentUsersPlaylists()
		    .limit(20)
		    .build();
		    try {
		      final Paging<PlaylistSimplified> playlistSimplifiedPaging = getListOfCurrentUsersPlaylistsRequest.execute();
		      Playlist[] bibliotecaDePlaylists = new Playlist[playlistSimplifiedPaging.getItems().length];
		      return conversor.getFromDifferentType(playlistSimplifiedPaging.getItems()).toArray(bibliotecaDePlaylists);
		    } catch (IOException | SpotifyWebApiException | ParseException e) {
		      System.out.println("Error: " + e.getMessage());
		    }
		    
		 return null;
	}





public static void createUsersPlaylist(String playlistName, String userID) {
	CreatePlaylistRequest createPlaylistRequest = ComunicadorDoSpotify.getSpotifyapi().createPlaylist(userID, playlistName).build();
	 try {
	      createPlaylistRequest.execute();
	    } catch (IOException | SpotifyWebApiException | ParseException e) {
	      System.out.println("Error: " + e.getMessage());
	    }	
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







public static void setCodigoDeAutorização(String codigoDeAutorização) {
	Comunicador.codigoDeAutorização = codigoDeAutorização;
}

private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
  .setClientId(clientId)
  .setClientSecret(clientSecret)
  .setRedirectUri(redirectUri)
  .build();
private static final ClientCredentialsRequest RequestDasNossasCredenciais = spotifyApi.clientCredentials()
  .build();

public static Track[] pesquisaXMusicas(String textoDePesquisa,int X) {
	  try {
	    final ClientCredentials nossasCredenciais = RequestDasNossasCredenciais.execute();
	    spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
	  } catch (IOException | SpotifyWebApiException | ParseException e) {
	   System.out.println("Error: " + e.getMessage());
	  }
	  
	   final SearchTracksRequest RequestDasMusicasPesquisadas = spotifyApi.searchTracks(textoDePesquisa)
	         .limit(X)
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


public static Track[] pesquisaMusicas(String textoDePesquisa) {
  try {
    final ClientCredentials nossasCredenciais = RequestDasNossasCredenciais.execute();
    spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
  } catch (IOException | SpotifyWebApiException | ParseException e) {
   System.out.println("Error: " + e.getMessage());
  }
  
   final SearchTracksRequest RequestDasMusicasPesquisadas = spotifyApi.searchTracks(textoDePesquisa)
         .limit(5)
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


public static Artist[] pesquisaArtistas(String nomeDoArtista, int limite) {

  try {
    final ClientCredentials nossasCredenciais = RequestDasNossasCredenciais.execute();
    spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
  } catch (IOException | SpotifyWebApiException | ParseException e) {
    System.out.println("Error: " + e.getMessage());
  }

  SearchArtistsRequest RequestDosArtistasPesquisados = ComunicadorDoSpotify.getSpotifyapi().searchArtists(nomeDoArtista)
		  .limit(limite)
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

 public static Playlist[] pesquisaPlaylists(String nomeDaPlaylist, int limite) {
	 ConversorDeTipo conversor = new ConversorDeTipo();
  try {
    final ClientCredentials nossasCredenciais = RequestDasNossasCredenciais.execute();
    spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
  } catch (IOException | SpotifyWebApiException | ParseException e) {
    System.out.println("Error: " + e.getMessage());
  }

  SearchPlaylistsRequest RequestDasPlaylistsPesquisadas = ComunicadorDoSpotify.getSpotifyapi().searchPlaylists(nomeDaPlaylist)
      .limit(limite)
      .build();
  try {
      final Paging<PlaylistSimplified> PlaylistsPesquisadas = RequestDasPlaylistsPesquisadas.execute();
      Playlist[] playlistConvertida = new Playlist[PlaylistsPesquisadas.getItems().length];
      return conversor.getFromDifferentType(PlaylistsPesquisadas.getItems()).toArray(playlistConvertida);
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return null;
}

private static final AuthorizationCodeUriRequest requestDoLinkParaAutorização = spotifyApi.authorizationCodeUri()
//      .state("x4xkmn9pu3j6ukrs8n")
//      .scope("user-read-birthdate,user-read-email")
//      .show_dialog(true)
.build();

public static URI geraLink() {
final URI linkParaAutorização = requestDoLinkParaAutorização.execute();
return linkParaAutorização;
}



public static void pegaAutorizaçãoDeTabela() {
	final AuthorizationCodeRequest requestDoCodigoDeAutorização = spotifyApi.authorizationCode(codigoDeAutorização)
			.build();

try {
  final AuthorizationCodeCredentials authorizationCodeCredentials =  requestDoCodigoDeAutorização.execute();

  
  spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
  spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

} catch (IOException | SpotifyWebApiException | ParseException e) {
  System.out.println("Error: " + e.getMessage());
}
}


public static void main(String[] args) {
	//PesquisaMusicas("ALO");
}



public static Album[] pesquisaAlbum(String pesquisa, int limite) {	
	  ConversorDeTipo conversor = new ConversorDeTipo();
	  try {
	    final ClientCredentials nossasCredenciais = RequestDasNossasCredenciais.execute();
	    spotifyApi.setAccessToken(nossasCredenciais.getAccessToken());
	  } catch (IOException | SpotifyWebApiException | ParseException e) {
	    System.out.println("Error: " + e.getMessage());
	  }
	
	  SearchAlbumsRequest RequestDosAlbunsPesquisados = ComunicadorDoSpotify.getSpotifyapi().searchAlbums(pesquisa)
	   .limit(limite)
	   .build();
	  try {
	      final Paging<AlbumSimplified> albunsPesquisados = RequestDosAlbunsPesquisados.execute();
	
	      System.out.println("Total: " + albunsPesquisados.getTotal());
	      Album[] albunsConvertidos = new Album[albunsPesquisados.getItems().length];
 	      return conversor.getFromDifferentType(albunsPesquisados.getItems()).toArray(albunsConvertidos);
	    } catch (IOException | SpotifyWebApiException | ParseException e) {
	      System.out.println("Error: " + e.getMessage());
	    }
	    return null;
}



public static List<AudioFeatures> getAudioFeatures(String[] ids) {
	GetAudioFeaturesForSeveralTracksRequest getAudioFeaturesForSeveralTracksRequest = ComunicadorDoSpotify.getSpotifyapi()
		    .getAudioFeaturesForSeveralTracks(ids)
		    .build();
	AudioFeatures[] audioFeatures = null;
	 try {
	      audioFeatures = getAudioFeaturesForSeveralTracksRequest.execute();
	    } catch (IOException | SpotifyWebApiException | ParseException e) {
	      System.out.println("Não foi possível retornar a feature: " + e.getMessage());
	    }
	
	return Arrays.asList(audioFeatures);
}



public static Track[] getAlbunsTracks(String albumID) {
	ConversorDeTipo conversor = new ConversorDeTipo();
	GetAlbumRequest getAlbumRequest = spotifyApi.getAlbum(albumID)
    .build();
	Album album;
	 try {
	      album = getAlbumRequest.execute();
	      Track[] musicas = new Track[album.getTracks().getItems().length];
	      return conversor.getFromDifferentType(album.getTracks().getItems()).toArray(musicas);

	    } catch (IOException | SpotifyWebApiException | ParseException e) {
	      System.out.println("Error: " + e.getMessage());
	    }
	 return null;
	 
}


public static Track[] getPlaylistsTracks(String playlistID) {
	ConversorDeTipo conversor = new ConversorDeTipo();
	GetPlaylistRequest getPlaylistRequest = spotifyApi.getPlaylist(playlistID)
    .build();
	
	 try {
	      final Playlist playlist = getPlaylistRequest.execute();
	      Track[] listaDeMusicas = new Track [playlist.getTracks().getItems().length];
	      return conversor.getFromDifferentType(playlist.getTracks().getItems()).toArray(listaDeMusicas);
	    } catch (IOException | SpotifyWebApiException | ParseException e) {
	      System.out.println("Error: " + e.getMessage());
	    }
	return null;
}

public static Track[] getArtistsTracks(String artistID) {
	
	GetArtistsTopTracksRequest getArtistsTopTracksRequest = spotifyApi
		    .getArtistsTopTracks(artistID, CountryCode.BR)
		    .build();	
	 try {
	      final Track[] tracks = getArtistsTopTracksRequest.execute();
	      return tracks;

	    } catch (IOException | SpotifyWebApiException | ParseException e) {
	      System.out.println("Error: " + e.getMessage());
	    }
	 return null;
	  }
}
