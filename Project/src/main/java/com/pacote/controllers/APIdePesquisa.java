package com.pacote.controllers;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;
import se.michaelthelin.spotify.model_objects.specification.Playlist;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/")

public class APIdePesquisa {
	@GetMapping("pesquisa")
	public MusicaParaEnviar[] informacao( @RequestParam(name="pesquisa", required=false, defaultValue="musica") String pesquisa) {
		int quantidade = 12;
		Track[] pesquisaBruta = new Track[quantidade];
		pesquisaBruta = Comunicador.pesquisaXMusicas(pesquisa,quantidade);
		quantidade = pesquisaBruta.length;
		MusicaParaEnviar[] musicaParaEnviar = new MusicaParaEnviar[quantidade];
		String[] ids = new String[quantidade];
		for (int i = 0; i < quantidade;i++) {
			if(pesquisaBruta[i] != null)
			{
				musicaParaEnviar[i] = new MusicaParaEnviar(pesquisaBruta[i]);
				ids[i] = musicaParaEnviar[i].getId();
			}	
		}
		setMusicaParaEnviarAudioFeatures(quantidade, musicaParaEnviar, ids);
		return musicaParaEnviar;	
	}


	private void setMusicaParaEnviarAudioFeatures(int quantidade, MusicaParaEnviar[] musicaParaEnviar, String[] ids) {
		List<AudioFeatures> featuresBrutas;
		AudioFeatures features;
		featuresBrutas = Comunicador.getAudioFeatures(ids);
		
		for (int i = 0; i < quantidade;i++) {

				features = featuresBrutas.get(i);				
				musicaParaEnviar[i].parametros[0] = features.getDanceability();
				musicaParaEnviar[i].parametros[1] = features.getEnergy();
				musicaParaEnviar[i].parametros[2] = features.getTempo();
				musicaParaEnviar[i].parametros[3] = features.getLoudness();
				musicaParaEnviar[i].parametros[4] = features.getSpeechiness();
				musicaParaEnviar[i].parametros[5] = features.getInstrumentalness();
				musicaParaEnviar[i].parametros[6] = features.getAcousticness();
				musicaParaEnviar[i].parametros[7] = features.getLiveness();
		}
	}
	
	
	@GetMapping("album")
	public AlbumParaEnviar[] infoAlbum( @RequestParam(name="pesquisa", required=false, defaultValue="musica") String pesquisa) {
		Album[] info = Comunicador.pesquisaAlbum(pesquisa);
		AlbumParaEnviar[] vec = new AlbumParaEnviar[info.length];
		for (int i = 0; i < vec.length;i++){
			if(info[i] != null){
				vec[i] = new AlbumParaEnviar(info[i]);
			}
		}
		return vec;	
	}	
	
	@GetMapping("artista")
	public ArtistaParaEnviar[] infoArtista(@RequestParam(name="pesquisa", required=false, defaultValue="musica") String pesquisa) {
		Artist[] info = Comunicador.pesquisaArtistas(pesquisa);
		ArtistaParaEnviar[] vec = new ArtistaParaEnviar[info.length];
		for (int i = 0; i < vec.length; i++){			
			if(info != null) {
				vec[i] = new ArtistaParaEnviar(info[i]);
			}
		}
		return vec;	
	}	
	
	@GetMapping("playlist")
	public PlaylistParaEnviar[] infoPlaylist(@RequestParam(name="pesquisa", required=false, defaultValue="musica") String pesquisa) {
		Playlist[] info = Comunicador.pesquisaPlaylists(pesquisa);
		PlaylistParaEnviar[] vec = new PlaylistParaEnviar[info.length];
		for (int i = 0; i < vec.length; i++){			
			if(info != null) {
				vec[i] = new PlaylistParaEnviar(info[i]);
			}
		}
		
		return vec;	
	}	
}

