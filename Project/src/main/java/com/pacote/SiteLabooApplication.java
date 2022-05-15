//package com.pacote;


import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import com.pacote.controllers.comunicadorDoSpotify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.Track;

@SpringBootApplication
public class SiteLabooApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteLabooApplication.class, args);
		int encerrou = 0;
		
		while(encerrou == 0) {
			System.out.println("Realizar novas buscas....");
			
			Scanner entrada = new Scanner(System.in);
			
			String palavras_chave = entrada.next();
			
			if(palavras_chave != null) {
				SearchResult itens_pesquisados = comunicadorDoSpotify.PesquisaItens(palavras_chave);
				
				if(itens_pesquisados != null) {
					System.out.println("Resultados da busca: ");
					
					System.out.println(itens_pesquisados.getTracks().getItems());
					System.out.println(itens_pesquisados.getAlbums().getItems());
					System.out.println(itens_pesquisados.getArtists().getItems());
					System.out.println(itens_pesquisados.getEpisodes().getItems());
					System.out.println(itens_pesquisados.getPlaylists().getItems());
					System.out.println(itens_pesquisados.getShows().getItems());
				}
				
				itens_pesquisados = null;
			}
			
			System.out.println("Realizar nova busca?");
			
			Scanner saida = new Scanner(System.in);
			String resposta = saida.next();
			
			if(resposta != null) {
				encerrou = 1;
			}
			else
			{
				encerrou = 0;
			}
			
			palavras_chave = null;
			resposta = null;
		}
		
		
	}

}
