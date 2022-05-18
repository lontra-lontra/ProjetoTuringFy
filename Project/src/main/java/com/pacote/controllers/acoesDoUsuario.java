package com.pacote.controllers;
import java.util.Arrays;
import java.util.Scanner;
import se.michaelthelin.spotify.model_objects.special.SearchResult;

public class acoesDoUsuario {
 

    public void pesquisarMusica(){
        Scanner sc = new Scanner(System.in);
        String nomeMusica;
        System.out.println("Insira o nome da música");
        nomeMusica = sc.nextLine();
        SearchResult itens_pesquisados = comunicadorDoSpotify.PesquisaItens(nomeMusica);
        PlaylistInterna listaDeBusca = new PlaylistInterna();
        listaDeBusca.playlist = Arrays.asList(itens_pesquisados.getTracks().getItems());
        listaDeBusca.imprimePlaylist();
        sc.close();
    }

    public void visualizarPlaylists(BibliotecaDePlaylists bibliotecaMaster){
        if(bibliotecaMaster.biblioteca == null){
            System.out.println("Não há nenhuma playlist salva");
        }
        else{
            for(PlaylistInterna listaDeMusicas : bibliotecaMaster.biblioteca){
                System.out.println(listaDeMusicas.nome);
                
            }
        }

    
        
    }

    public void criarPlaylist(){

    }

    public void deletarPlaylist(){

    }

    public void pesquisarEmPlaylist(){

    }
}
