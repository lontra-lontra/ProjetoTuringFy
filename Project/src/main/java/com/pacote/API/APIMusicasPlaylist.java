package com.pacote.API;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.operacoesReact.Comunicador;
import com.pacote.itemParaEnviar.MusicaParaEnviar;

import se.michaelthelin.spotify.model_objects.specification.Track;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/")

public class APIMusicasPlaylist {
		@GetMapping("MusicasPlaylist")
		public MusicaParaEnviar[] musicasDeUmaPlaylist(@RequestParam(name="PlaylistID", required=false, defaultValue=" ") String PlaylistID){
			int quantidade = 20;
			Track[] userLists = new Track[quantidade];
			userLists = Comunicador.getPlaylistsTracks(PlaylistID);
			quantidade = userLists.length;
			MusicaParaEnviar[] userListsToApi = new MusicaParaEnviar[quantidade];
			for(int i = 0; i < quantidade; i ++) {
				if(userLists[i] != null) {
					userListsToApi[i] = new MusicaParaEnviar(userLists[i]);				
				}
			}
			return userListsToApi;
		}
	}
