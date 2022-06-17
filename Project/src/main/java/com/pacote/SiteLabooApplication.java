package com.pacote;



import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pacote.controllers.BuscadorDoSpotify;
import com.pacote.controllers.EditorDePlaylists;
import com.pacote.controllers.GetItemFromSimplifiedType;

@SpringBootApplication
public class SiteLabooApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteLabooApplication.class, args);
		
		
		EditorDePlaylists editorMaster = new EditorDePlaylists();
		GetItemFromSimplifiedType conversor = new GetItemFromSimplifiedType();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Hello, booting system ...");
		
		System.out.println("Pronta para começar?");
		
		if(sc.nextInt() == 1) {
		
		System.out.println("Playlist view test...");
		
		editorMaster.visualizaPlaylists(conversor.getFromDifferentType(BuscadorDoSpotify.getListOfCurrentUsersPlaylists_Sync()));
		}
		
		
	}

}
