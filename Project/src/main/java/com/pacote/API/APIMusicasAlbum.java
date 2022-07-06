package com.pacote.API;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.operacoesReact.Comunicador;
import com.pacote.itemParaEnviar.MusicaParaEnviar;

import se.michaelthelin.spotify.model_objects.specification.Track;

public class APIMusicasAlbum {
	
	@CrossOrigin(origins = "http://localhost:3000/")	
	public class APIdaPaginaInicial {	
		@GetMapping("MusicasAlbum")
		public MusicaParaEnviar[] musicasDeUmAlbum(@RequestParam(name="albumID", required=false, defaultValue=" ") String albumID){
			int quantidade = 20;
			Track[] userLists = new Track[quantidade];
			userLists = Comunicador.getAlbunsTracks(albumID);
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

}