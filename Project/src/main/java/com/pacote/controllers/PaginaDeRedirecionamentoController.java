package com.pacote.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaginaDeRedirecionamentoController {
	
	@CrossOrigin(origins = "http://localhost:3000/")
	@GetMapping("/PaginaInicial")
	public String greeting(@RequestParam(name="code", required=false, defaultValue="") String code, Model model) {
		System.out.println(code);
		ComunicadorDoSpotify.setCodigoDeAutorização(code);
		ComunicadorDoSpotify.pegaAutorizaçãoDeTabela();
		return "paginaDeRedirecionamentoView";	
	}

}
