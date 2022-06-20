package com.pacote.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.Album;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/")
public class APIdePesquisa {
	@GetMapping("pesquisa")
	public MusicaParaEnviar[] informacao( @RequestParam(name="pesquisa", required=false, defaultValue="musica") String pesquisa) {
		Track[] info = new Track[20];
		info = Comunicador.pesquisaMusicas(pesquisa);
		//MusicaParaEnviar[] MusicasParaEnviar = new MusicaParaEnviar [10];
		//tipo_de_info um = new tipo_de_info();
		//tipo_de_info dois = new tipo_de_info();
		MusicaParaEnviar[] vec = new MusicaParaEnviar[5];
		for (int i = 0; i < 5;i++) {
			vec[i] = new MusicaParaEnviar(info[i]);
			if(info != null){
				//vec[i].nome = info[i].getName();
			}
		}
		return vec;	
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

