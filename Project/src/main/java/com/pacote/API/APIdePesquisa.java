package com.pacote.API;

import com.operacoesReact.Comunicador;
import com.pacote.itemParaEnviar.AlbumParaEnviar;
import com.pacote.itemParaEnviar.ArtistaParaEnviar;
import com.pacote.itemParaEnviar.MusicaParaEnviar;
import com.pacote.itemParaEnviar.PlaylistParaEnviar;
import com.pacote.operacoesTerminal.ComunicadorDoSpotify;
import com.operacoesReact.ConversorDeTipo;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/")

public class APIdePesquisa {
	@GetMapping("pesquisa")
	public MusicaParaEnviar[] informacao( @RequestParam(name="pesquisa", required=false, defaultValue="musica") String pesquisa) {
		int quantidade = 12;
		Track[] pesquisaBruta = new Track[quantidade];
		pesquisaBruta = Comunicador.pesquisaXMusicas(pesquisa,quantidade);
		quantidade = pesquisaBruta.length;
		MusicaParaEnviar[] musicaParaEnviar = new MusicaParaEnviar[quantidade];
		String[] ids = new String[quantidade];
		for (int i = 0; i < quantidade;i++) {
			if(pesquisaBruta[i] != null)
			{
				musicaParaEnviar[i] = new MusicaParaEnviar(pesquisaBruta[i]);
				ids[i] = musicaParaEnviar[i].getId();
			}	
		}
		setMusicaParaEnviarAudioFeatures(quantidade, musicaParaEnviar, ids);
		return musicaParaEnviar;	
	}
	
	@GetMapping("criaPlaylist")
	public void criaPlaylist(@RequestParam(name="nome", required=false, defaultValue="playlist") String nome)
	{
			User usuario = Comunicador.getCurrentUsersProfile();
			String id = usuario.getId();
			Comunicador.createUsersPlaylist(nome, id);
	}
	
	@GetMapping("pegaPlaylistsDoCliente")
	public PlaylistParaEnviar[] playlistsdousuario()
	{
		Playlist[] playlists;
		System.out.println("pegando id{");
				
		System.out.println("pegando as playlists{");
		playlists  = Comunicador.getCurrentUsersPlaylist();
		System.out.println("}pegando as playlists");
		
		int quantidade;
		
		quantidade = playlists.length;
		PlaylistParaEnviar[] playlistsParaEnviar = new PlaylistParaEnviar[quantidade];
		
		for (int i = 0; i < quantidade; i++){			
			if(playlists[i] != null) {
				playlistsParaEnviar[i] = new PlaylistParaEnviar(playlists[i]);
			}
		}
		
		System.out.println(playlistsParaEnviar[0].getNome());
		return playlistsParaEnviar;
	}

	private void setMusicaParaEnviarAudioFeatures(int quantidade, MusicaParaEnviar[] musicaParaEnviar, String[] ids) {
		List<AudioFeatures> featuresBrutas;
		AudioFeatures features;
		featuresBrutas = Comunicador.getAudioFeatures(ids);
		
		for (int i = 0; i < quantidade;i++) {

				features = featuresBrutas.get(i);				
				musicaParaEnviar[i].parametros[0] = features.getDanceability();
				musicaParaEnviar[i].parametros[1] = features.getEnergy();
				musicaParaEnviar[i].parametros[2] = features.getTempo();
				musicaParaEnviar[i].parametros[3] = features.getLoudness();
				musicaParaEnviar[i].parametros[4] = features.getSpeechiness();
				musicaParaEnviar[i].parametros[5] = features.getInstrumentalness();
				musicaParaEnviar[i].parametros[6] = features.getAcousticness();
				musicaParaEnviar[i].parametros[7] = features.getLiveness();
		}
	}
	
	
	@GetMapping("album")
	public AlbumParaEnviar[] infoAlbum( @RequestParam(name="pesquisa", required=false, defaultValue="musica") String pesquisa) {
		int quantidade = 12;
		Album[] info = new Album[quantidade];
		info = Comunicador.pesquisaAlbum(pesquisa, quantidade);
		quantidade = info.length;
		AlbumParaEnviar[] vec = new AlbumParaEnviar[quantidade];
		for (int i = 0; i < vec.length;i++){
			if(info[i] != null){
				vec[i] = new AlbumParaEnviar(info[i]);
			}
		}
		return vec;	
	}	
	
	@GetMapping("artista")
	public ArtistaParaEnviar[] infoArtista(@RequestParam(name="pesquisa", required=false, defaultValue="musica") String pesquisa) {
		int quantidade = 12;	
		Artist[] info = new Artist[quantidade];
		info = Comunicador.pesquisaArtistas(pesquisa, quantidade);
		quantidade = info.length;
		ArtistaParaEnviar[] vec = new ArtistaParaEnviar[quantidade];
		for (int i = 0; i < vec.length; i++){			
			if(info[i] != null) {
				vec[i] = new ArtistaParaEnviar(info[i]);
			}
		}
		return vec;	
	}	
	
	@GetMapping("playlist")
	public PlaylistParaEnviar[] infoPlaylist(@RequestParam(name="pesquisa", required=false, defaultValue="musica") String pesquisa) {
		int quantidade = 12;
		Playlist[] info = new Playlist[12];
		info = Comunicador.pesquisaPlaylists(pesquisa, quantidade);
		quantidade = info.length;
		PlaylistParaEnviar[] vec = new PlaylistParaEnviar[quantidade];
		for (int i = 0; i < vec.length; i++){			
			if(info[i] != null) {
				vec[i] = new PlaylistParaEnviar(info[i]);
			}
		}
		
		return vec;	
	}
}

