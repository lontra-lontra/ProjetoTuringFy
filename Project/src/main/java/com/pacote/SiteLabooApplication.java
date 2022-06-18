package com.pacote;
<<<<<<< HEAD
=======



import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
>>>>>>> 84277630583929a0771e588387c804b4cb7ec9fc

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

<<<<<<< HEAD
=======
import com.pacote.controllers.BuscadorDoSpotify;
import com.pacote.controllers.ComunicadorDoSpotify;
import com.pacote.controllers.EditorDePlaylists;

import se.michaelthelin.spotify.enums.AuthorizationScope;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.User;

import com.pacote.controllers.CoversorDeTipo;

>>>>>>> 84277630583929a0771e588387c804b4cb7ec9fc
@SpringBootApplication
public class SiteLabooApplication {
	private static User user;
	
	public static void main(String[] args) {
		int k = 0;
		SpringApplication.run(SiteLabooApplication.class, args);
<<<<<<< HEAD
=======
		EditorDePlaylists editorMaster = new EditorDePlaylists();
		CoversorDeTipo conversor = new CoversorDeTipo();
		Scanner sc = new Scanner(System.in);
		System.out.println("Hello, booting system ...");		
		System.out.println("Pronta para começar?");
		int n = sc.nextInt();
		while(n != 9875) {
			
			if(k == 10) {
				ComunicadorDoSpotify.authorizationCodeRefresh_Sync();
			}
			if(n == 1) {
				user = BuscadorDoSpotify.getCurrentUsersProfile();
				System.out.println("User profile test ....");
				System.out.println("User: " + user.getDisplayName() + ". ID: " + user.getId());
			}
			else if(n == 2) {
				editorMaster.visualizaPlaylists(conversor.getFromDifferentType(BuscadorDoSpotify.getListOfUsersPlaylists(user.getId())));
			}
			else if(n == 3) {
				sc.nextLine();
				System.out.println("Nome: ");
				editorMaster.createUsersPlaylist(sc.nextLine(), user.getId());
			}
			else if(n == 4) {
				sc.nextLine();
				System.out.println("Delete Name: ");
				String name = sc.nextLine();
				List<Playlist> lista = conversor.getFromDifferentType(BuscadorDoSpotify.getListOfUsersPlaylists(user.getId()));
				List<Playlist> paraDel = lista.stream().filter(playlist -> playlist.getName().contains(name)).collect(Collectors.toList());
				for(Playlist list : paraDel) {
					editorMaster.deleteUsersPlaylist(list.getId());
			}
			}
			else if (n == 5) {
				System.out.println("Adiciona Música a Playlist");
				sc.nextLine();
				System.out.println("Termo de Busca");
				String palavra = sc.nextLine();
				Track[] listaDeBusca = BuscadorDoSpotify.pesquisaMusicas(palavra);
				String[] listaIds = new String[20];
				for(int i = 0; i < 20; i++ ) {
					listaIds[i] = listaDeBusca[i].getUri();
				}
				List<Playlist> listaP = conversor.getFromDifferentType(BuscadorDoSpotify.getListOfUsersPlaylists(user.getId()));
				editorMaster.adicionaMusica(listaP.get(0), listaIds);
			}
			else if (n == 6) {
				List<Playlist> listaP = conversor.getFromDifferentType(BuscadorDoSpotify.getListOfUsersPlaylists(user.getId()));
				editorMaster.vizualizaPlaylist(listaP.get(0));
			}
			n = sc.nextInt();
			k++;
			
			
		}
		
		
>>>>>>> 84277630583929a0771e588387c804b4cb7ec9fc
	}

}
