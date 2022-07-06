package com.pacote.API;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.operacoesReact.Comunicador;
import com.pacote.itemParaEnviar.PlaylistParaEnviar;

import se.michaelthelin.spotify.model_objects.specification.Playlist;
@CrossOrigin(origins = "http://localhost:3000/")	
public class APIdaPaginaInicial {	
	@GetMapping("PaginaInicial")
	public PlaylistParaEnviar[] userPlaylists(@RequestParam(name="token", required=false, defaultValue=" ") String userToken){
		int quantidade = 20;
		Playlist[] userLists = new Playlist[quantidade];
		userLists = Comunicador.getCurrentUsersPlaylist(userToken);
		quantidade = userLists.length;
		PlaylistParaEnviar[] userListsToApi = new PlaylistParaEnviar[quantidade];
		for(int i = 0; i < quantidade; i ++) {
			if(userLists[i] != null) {
				userListsToApi[i] = new PlaylistParaEnviar(userLists[i]);				
			}
		}
		return userListsToApi;
	}

}
