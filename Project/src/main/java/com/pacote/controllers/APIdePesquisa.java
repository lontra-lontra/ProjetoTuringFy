package com.pacote.controllers;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/")

public class APIdePesquisa {
	@GetMapping("pesquisa")
	public MusicaParaEnviar[] informacao( @RequestParam(name="pesquisa", required=false, defaultValue="musica") String pesquisa) {
		int quantidade = 12;
		Track[] pesquisaBruta = new Track[quantidade];
		pesquisaBruta = Comunicador.pesquisaXMusicas(pesquisa,quantidade);
		List<AudioFeatures> featuresBrutas;
		AudioFeatures features;
		quantidade = pesquisaBruta.length;
		MusicaParaEnviar[] musicaParaEnviar = new MusicaParaEnviar[quantidade];

		
		String[] s = new String[quantidade];
		String[] ids = new String[quantidade];
		for (int i = 0; i < quantidade;i++) {
			if(pesquisaBruta[i] != null)
			{
				musicaParaEnviar[i] = new MusicaParaEnviar(pesquisaBruta[i]);
				ids[i] = musicaParaEnviar[i].id;
			}	
		}
		
		featuresBrutas = Comunicador.getAudioFeatures(ids);
		
		for (int i = 0; i < quantidade;i++) {

				features = featuresBrutas.get(i);
				////incluindo dançável, energia, andamento (tempo), força (loudness), fala (speechiness), instrumental, ao vivo, acústica
				musicaParaEnviar[i].dancabilidade = features.getDanceability();
				musicaParaEnviar[i].energia = features.getEnergy();
				musicaParaEnviar[i].andamento = features.getTempo();
				musicaParaEnviar[i].forca = features.getLoudness();
				musicaParaEnviar[i].fala = features.getSpeechiness();
				musicaParaEnviar[i].instrumental = features.getInstrumentalness();
				musicaParaEnviar[i].acustico = features.getAcousticness();
				musicaParaEnviar[i].aovivo = features.getLiveness();
				
				musicaParaEnviar[i].parametros[0] = features.getDanceability();
				musicaParaEnviar[i].parametros[1] = features.getEnergy();
				musicaParaEnviar[i].parametros[2] = features.getTempo();
				musicaParaEnviar[i].parametros[3] = features.getLoudness();
				musicaParaEnviar[i].parametros[4] = features.getSpeechiness();
				musicaParaEnviar[i].parametros[5] = features.getInstrumentalness();
				musicaParaEnviar[i].parametros[6] = features.getAcousticness();
				musicaParaEnviar[i].parametros[7] = features.getLiveness();
		}

		return musicaParaEnviar;	
	}
	
	
	@GetMapping("album")
	public AlbumParaEnviar[] InfoAlbum( @RequestParam(name="pesquisa", required=false, defaultValue="musica") String pesquisa) {
		Album[] info = Comunicador.pesquisaAlbum(pesquisa);
		//MusicaParaEnviar[] MusicasParaEnviar = new MusicaParaEnviar [10];
		//tipo_de_info um = new tipo_de_info();
		//tipo_de_info dois = new tipo_de_info();
		AlbumParaEnviar[] vec = new AlbumParaEnviar[5];
		for (int i = 0; i < 5;i++)
		{
			vec[i] = new AlbumParaEnviar(info[i]);
			if(info != null)
			{
				//vec[i].nome = info.get(i).getName();
			}
		}
		return vec;	
	}	
	
}

