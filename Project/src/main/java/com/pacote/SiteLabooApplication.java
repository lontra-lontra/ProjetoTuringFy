//package com.pacote;


import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import com.pacote.controllers.comunicadorDoSpotify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
				Track[] musicas_pesquisadas = comunicadorDoSpotify.PesquisaMúsicas(palavras_chave);
				
				if(musicas_pesquisadas != null) {
					System.out.println("Resultados da busca: ");
					
					for(Track musica : musicas_pesquisadas) {
						System.out.println(musica);
					}
				}
				
				musicas_pesquisadas = null;
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
