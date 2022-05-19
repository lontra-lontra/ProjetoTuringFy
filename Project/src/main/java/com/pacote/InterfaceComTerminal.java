package com.pacote;

import java.util.Scanner;

import com.pacote.controllers.AcoesDoUsuario;
import com.pacote.controllers.BibliotecaDePlaylists;

public class InterfaceComTerminal {

	public static void main(String[] args) {
		BibliotecaDePlaylists bibliotecaDoUsuario = new BibliotecaDePlaylists();
		int opcaoDoUsuario = 0; //variavel que
		AcoesDoUsuario executor = new AcoesDoUsuario();
		
		Scanner entrada = new Scanner(System.in);

		System.out.println("Bem-vindo ao Sistema Music Library!");
		
		while(opcaoDoUsuario != 6) {
			System.out.println(" ");
			System.out.println("Ações disponíveis:");
			System.out.println("1. Pesquisar música");
			System.out.println("2. Visualizar playlists");
			System.out.println("3. Criar playlist");
			System.out.println("4. Deletar playlist");
			System.out.println("5. Pesquisar em playlist");
			System.out.println("6. Encerrar o sistema");
					
			opcaoDoUsuario = entrada.nextInt();
			
			if(opcaoDoUsuario == 1)
				executor.pesquisarMusica(bibliotecaDoUsuario);
			else if(opcaoDoUsuario == 2)
				executor.visualizarPlaylists(bibliotecaDoUsuario);
			else if(opcaoDoUsuario == 3)
				bibliotecaDoUsuario = executor.criarPlaylist(bibliotecaDoUsuario);
			else if(opcaoDoUsuario == 4)
				executor.deletarPlaylist(bibliotecaDoUsuario);
			else if(opcaoDoUsuario == 5)
				executor.pesquisarEmPlaylist(bibliotecaDoUsuario);
				
		}
		
	}

}