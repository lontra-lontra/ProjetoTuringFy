package com.pacote;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.pacote.controllers.OperacoesDoUsuario;
import com.pacote.controllers.OperacoesInternas;


@SpringBootApplication
public class SiteLabooApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(SiteLabooApplication.class, args);
		
		int opcaoDoUsuario = 0;
        OperacoesDoUsuario executor = new OperacoesDoUsuario();
		
		Scanner entrada = new Scanner(System.in);
		System.out.println("Boot system? Y(1)/N(0)");
			if(entrada.nextInt() == 1) {
			System.out.println("Bem-vindo ao Sistema Music Library!");
			OperacoesInternas.setUser();
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
		}
			entrada.close();
	}

}
