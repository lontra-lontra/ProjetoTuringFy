package com.pacote;



import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pacote.controllers.BuscadorDoSpotify;
import com.pacote.controllers.EditorDePlaylists;

import se.michaelthelin.spotify.enums.AuthorizationScope;
import se.michaelthelin.spotify.model_objects.specification.User;

import com.pacote.controllers.CoversorDeTipo;

@SpringBootApplication
public class SiteLabooApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteLabooApplication.class, args);
		EditorDePlaylists editorMaster = new EditorDePlaylists();
		CoversorDeTipo conversor = new CoversorDeTipo();
		Scanner sc = new Scanner(System.in);
		System.out.println("Hello, booting system ...");		
		System.out.println("Pronta para começar?");
		
		if(sc.nextInt() == 1) {
			User user = BuscadorDoSpotify.getCurrentUsersProfile();
			System.out.println("User profile test ....");
			System.out.println("User: " + user.getDisplayName() + ". ID: " + user.getId());
		}
		
		
	}

}
