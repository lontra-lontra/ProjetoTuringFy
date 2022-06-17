package com.pacote.controllers;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
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
import se.michaelthelin.spotify.enums.AuthorizationScope;
import se.michaelthelin.spotify.enums.ModelObjectType;
import java.util.*;

import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URI;



public class ComunicadorDoSpotify {
private static final String clientId = "996174542561436ead6fda89541d0083";
private static final String clientSecret = "cffbb71c871d4a2088f069137c43064f";
private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/red");
private static String codigoDeAutorização = "";




public static void setCodigoDeAutorização(String codigoDeAutorização) {
	ComunicadorDoSpotify.codigoDeAutorização = codigoDeAutorização;
}

private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
  .setClientId(clientId)
  .setClientSecret(clientSecret)
  .setRedirectUri(redirectUri)
  .build();
static final ClientCredentialsRequest RequestDasNossasCredenciais = getSpotifyapi()
  .clientCredentials()
  .build();

private static final AuthorizationCodeUriRequest requestDoLinkParaAutorização = getSpotifyapi().authorizationCodeUri()
.scope(AuthorizationScope.values())
.show_dialog(true)
.build();

public static URI geraLink() {
final URI linkParaAutorização = requestDoLinkParaAutorização.execute();

System.out.println("URI: " + linkParaAutorização.toString());
return linkParaAutorização;
}



public static void pegaAutorizaçãoDeTabela() {
	final AuthorizationCodeRequest requestDoCodigoDeAutorização = getSpotifyapi().authorizationCode(codigoDeAutorização)
			.build();

try {
  final AuthorizationCodeCredentials authorizationCodeCredentials =  requestDoCodigoDeAutorização.execute();

  
  getSpotifyapi().setAccessToken(authorizationCodeCredentials.getAccessToken());
  getSpotifyapi().setRefreshToken(authorizationCodeCredentials.getRefreshToken());

  System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
} catch (IOException | SpotifyWebApiException | ParseException e) {
  System.out.println("Error: " + e.getMessage());
}
}


public static ClientCredentialsRequest getRequestdasnossascredenciais() {
	return RequestDasNossasCredenciais;
}



public static SpotifyApi getSpotifyapi() {
	return spotifyApi;
}

private static final AuthorizationCodeRefreshRequest authorizationCodeRefreshRequest = spotifyApi.authorizationCodeRefresh()
.build();

public static void authorizationCodeRefresh_Sync() {
try {
  final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRefreshRequest.execute();

  // Set access and refresh token for further "spotifyApi" object usage
  spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());

  System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
} catch (IOException | SpotifyWebApiException | ParseException e) {
  System.out.println("Error: " + e.getMessage());
}
}
}