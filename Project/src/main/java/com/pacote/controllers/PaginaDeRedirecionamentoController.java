package com.pacote.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pacote.operacoesTerminal.ComunicadorDoSpotify;

@Controller
public class PaginaDeRedirecionamentoController {
	
	@GetMapping("/red")
	public String greeting(@RequestParam(name="code", required=false, defaultValue="") String code, Model model) {
		System.out.println(code);
		ComunicadorDoSpotify.setCodigoDeAutorização(code);
		ComunicadorDoSpotify.pegaAutorizaçãoDeTabela();
		return "paginaDeRedirecionamentoView";	
	}

}
