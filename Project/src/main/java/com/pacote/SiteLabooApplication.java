package com.pacote;

//import java.util.Arrays;
//import java.util.Objects;
import java.util.Scanner;

import com.pacote.controllers.acoesDoUsuario;
import com.pacote.controllers.BibliotecaDePlaylists;


//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import se.michaelthelin.spotify.model_objects.specification.Track;

//@SpringBootApplication
public class SiteLabooApplication {

	public static void main(String[] args) {
		BibliotecaDePlaylists bibliotecaDoUsuario = new BibliotecaDePlaylists();
		//SpringApplication.run(SiteLabooApplication.class, args);
		int opcaoDoUsuario = 0; //variavel que
		acoesDoUsuario executor = new acoesDoUsuario();
		
		Scanner entrada = new Scanner(System.in);

		System.out.println("Bem-vindo ao Sistema Music Library!");
		
		while(opcaoDoUsuario != 6) {
			System.out.println("Ações disponíveis:");
			System.out.println("1. Pesquisar música");
			System.out.println("2. Visualizar playlists");
			System.out.println("3. Criar playlist");
			System.out.println("4. Deletar playlist");
			System.out.println("5. Pesquisar em playlist");
			System.out.println("6. Encerrar o sistema");

			opcaoDoUsuario = entrada.nextInt();

			switch (opcaoDoUsuario){
				case 1:
					executor.pesquisarMusica();
				case 2:
					executor.visualizarPlaylists(bibliotecaDoUsuario);
				case 3:
					executor.criarPlaylist();
				case 4:
					executor.deletarPlaylist();
				case 5:
					executor.pesquisarEmPlaylist();
			}
			
			// System.out.println("Realizar novas buscas....");
			
			// String palavras_chave = entrada.next();
			
			// if(palavras_chave != null) {
			// 	SearchResult itens_pesquisados = comunicadorDoSpotify.PesquisaItens(palavras_chave);
				
			// 	if(itens_pesquisados != null) {
			// 		System.out.println("Resultados da busca: ");
					
			// 		System.out.println(itens_pesquisados.getTracks().getItems());
			// 		System.out.println(itens_pesquisados.getAlbums().getItems());
			// 		System.out.println(itens_pesquisados.getArtists().getItems());
			// 		System.out.println(itens_pesquisados.getEpisodes().getItems());
			// 		System.out.println(itens_pesquisados.getPlaylists().getItems());
			// 		System.out.println(itens_pesquisados.getShows().getItems());
			// 	}
				
			// 	itens_pesquisados = null;
			// }
			
			// System.out.println("Realizar nova busca?");
			
			// Scanner saida = new Scanner(System.in);
			// String resposta = saida.next();
			
			// if(resposta != null) {
			// 	encerrou = 1;
			// }
			// else{
			// 	encerrou = 0;
			// }
			
			// palavras_chave = null;
			// resposta = null;
			//entrada.close();
			// saida.close();
		}
		
	}

}
