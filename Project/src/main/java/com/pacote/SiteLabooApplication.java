package com.pacote;



import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pacote.controllers.BuscadorDoSpotify;
import com.pacote.controllers.ComunicadorDoSpotify;
import com.pacote.controllers.EditorDePlaylists;

import se.michaelthelin.spotify.enums.AuthorizationScope;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.User;

import com.pacote.controllers.CoversorDeTipo;

@SpringBootApplication
public class SiteLabooApplication {
	private static User user;
	
	public static void main(String[] args) {
		int k = 0;
		SpringApplication.run(SiteLabooApplication.class, args);
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
			n = sc.nextInt();
			k++;
			
			
		}
		
		
	}

}
