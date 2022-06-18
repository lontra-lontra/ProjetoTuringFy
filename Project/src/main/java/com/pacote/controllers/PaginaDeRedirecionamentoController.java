package com.pacote.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import se.michaelthelin.spotify.model_objects.specification.Track;

@Controller
public class PaginaDeRedirecionamentoController {

	@GetMapping("/red")
	public String greeting(@RequestParam(name="code", required=false, defaultValue="") String code, Model model) {
<<<<<<< HEAD:Project/src/main/java/com/pacote/controllers/paginaDeRedirecionamentoController.java
		//System.out.println(code);
=======
		System.out.println(code);
>>>>>>> 84277630583929a0771e588387c804b4cb7ec9fc:Project/src/main/java/com/pacote/controllers/PaginaDeRedirecionamentoController.java
		ComunicadorDoSpotify.setCodigoDeAutorização(code);
		ComunicadorDoSpotify.pegaAutorizaçãoDeTabela();
		return "paginaDeRedirecionamentoView";	
	}

}
