package com.pacote;

import com.pacote.controllers.BuscadorDoSpotify;
import com.pacote.controllers.EditorDePlaylists;
import com.pacote.controllers.GetItemFromSimplifiedType;

public class RunningOnConsole {
	
	public static void main(String[] args) {
		EditorDePlaylists editorMaster = new EditorDePlaylists();
		GetItemFromSimplifiedType conversor = new GetItemFromSimplifiedType();
		
		System.out.println("Hello, booting system ...");
		
		System.out.println("Playlist view test...");
		
		editorMaster.visualizaPlaylists(conversor.getFromDifferentType(BuscadorDoSpotify.getListOfCurrentUsersPlaylists_Sync()));
		
		
		
	}

}
