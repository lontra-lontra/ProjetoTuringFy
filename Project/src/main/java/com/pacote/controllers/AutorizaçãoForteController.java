package com.pacote.controllers;

import java.util.Scanner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import se.michaelthelin.spotify.model_objects.specification.Track;
import java.net.URI;

@Controller
public class AutorizaçãoForteController {

	@GetMapping("/aut")
	public String greeting(@RequestParam(name="pesquisa", required=false, defaultValue="") String name, Model model) {
		URI uri =  ComunicadorDoSpotify.geraLink();
		model.addAttribute("link",uri);
		//System.out.println();
		return "autorizaçãoForteView";
		
	}

}


