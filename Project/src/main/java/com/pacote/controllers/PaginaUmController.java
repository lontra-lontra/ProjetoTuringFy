package com.pacote.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.michaelthelin.spotify.model_objects.specification.Track;

@Controller
public class PaginaUmController {

	@GetMapping("/pesquisa")
	public String greeting(@RequestParam(name="pesquisa", required=false, defaultValue="") String name, Model model) {
<<<<<<< HEAD:Project/src/main/java/com/pacote/controllers/paginaUmController.java
		Track[] Músicas =  ComunicadorDoSpotify.PesquisaMusicas(name);
=======
		Track[] Músicas =  BuscadorDoSpotify.pesquisaMusicas(name);
>>>>>>> 84277630583929a0771e588387c804b4cb7ec9fc:Project/src/main/java/com/pacote/controllers/PaginaUmController.java
		model.addAttribute("musicas", Músicas);
		//System.out.println();
		return "paginaUmView";
		
	}

}
