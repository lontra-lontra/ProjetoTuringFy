package com.pacote.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import se.michaelthelin.spotify.model_objects.specification.Track;

@Controller
public class paginaDeRedirecionamentoController {

	@GetMapping("/red")
	public String greeting(@RequestParam(name="code", required=false, defaultValue="") String code, Model model) {
		System.out.println(code);
		comunicadorDoSpotify.setCodigoDeAutorização(code);
		comunicadorDoSpotify.pegaAutorizaçãoDeTabela();
		return "paginaDeRedirecionamentoView";	
	}

}
