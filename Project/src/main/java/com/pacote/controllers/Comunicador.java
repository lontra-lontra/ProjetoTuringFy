package com.pacote.controllers;
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
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;

import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URI;



public class Comunicador {
private static final String clientId = "5b1ca7af0e4d44349dc5e7691cb31f9e";
private static final String clientSecret = "a97ac799a878484998b7876f28ec46b9";
private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/red");
private static String codigoDeAutorização = "";




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
	//PesquisaMusicas("ALO");
}



public static Album[] pesquisaAlbum(String pesquisa) {
	// TODO Auto-generated method stub
	return null;
}


}