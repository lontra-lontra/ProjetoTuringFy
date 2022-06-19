package com.pacote.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import se.michaelthelin.spotify.model_objects.specification.Track;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/")
public class UserController {
	@GetMapping("pesquisa")
	
	public MusicaParaEnviar[] informacao( @RequestParam(name="pesquisa", required=false, defaultValue="musica") String pesquisa) {
		Track[] info =  Comunicador.PesquisaMusicas(pesquisa);
		//MusicaParaEnviar[] MusicasParaEnviar = new MusicaParaEnviar [10];
		//tipo_de_info um = new tipo_de_info();
		//tipo_de_info dois = new tipo_de_info();
		MusicaParaEnviar[] vec = new MusicaParaEnviar[5];
		for (int i = 0; i < 5;i++)
		{
			vec[i] = new MusicaParaEnviar(info[i]);
			if(info != null)
			{
				//vec[i].nome = info[i].getName();
			}
		}

		
		return vec;	
	}
}

