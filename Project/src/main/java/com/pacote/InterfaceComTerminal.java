package com.pacote;
import com.pacote.controllers.OperacoesDoUsuario;

import java.util.Scanner;

public class InterfaceComTerminal {

	public static void main(String[] args) {
		int opcaoDoUsuario = 0; //variavel que
		OperacoesDoUsuario executor = new OperacoesDoUsuario();
		
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
				executor.pesquisarMusicaPorNome(entrada);
			else if(opcaoDoUsuario == 2)
				executor.visualizarPlaylists(entrada);
			else if(opcaoDoUsuario == 3)
				executor.criarPlaylist(entrada);
			else if(opcaoDoUsuario == 4)
				executor.deletarPlaylist(entrada);
			else if(opcaoDoUsuario == 5)
				executor.pesquisarEmPlaylist(entrada);
				
		}
		entrada.close();
		
	}

}