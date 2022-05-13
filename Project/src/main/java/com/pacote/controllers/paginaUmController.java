package com.pacote.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.michaelthelin.spotify.model_objects.specification.Track;

@Controller
public class paginaUmController {

	@GetMapping("/pesquisa")
	public String greeting(@RequestParam(name="pesquisa", required=false, defaultValue="") String name, Model model) {
		Track[] Músicas =  comunicadorDoSpotify.PesquisaMúsicas(name);
		model.addAttribute("musicas", Músicas);
		//System.out.println();
		return "paginaUmView";
		
	}

}
