package com.pacote.controllers;




import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.net.URI;

public class GeradorDoLinkDeAutorização {
  private static final String clientId = "996174542561436ead6fda89541d0083";
  private static final String clientSecret = "cffbb71c871d4a2088f069137c43064f";
  private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/red");

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setClientId(clientId)
    .setClientSecret(clientSecret)
    .setRedirectUri(redirectUri)
    .build();
  private static final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
    .build();

  public static URI geraLink() {
    final URI uri = authorizationCodeUriRequest.execute();

    System.out.println("URI: " + uri.toString());
    return uri;
  }


  public static void main(String[] args) {
    geraLink();
  }
}

