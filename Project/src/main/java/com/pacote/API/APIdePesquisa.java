package com.pacote.API;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.operacoesReact.Comunicador;
import com.pacote.itemParaEnviar.AlbumParaEnviar;
import com.pacote.itemParaEnviar.ArtistaParaEnviar;
import com.pacote.itemParaEnviar.MusicaParaEnviar;
import com.pacote.itemParaEnviar.PlaylistParaEnviar;
import com.pacote.operacoesTerminal.EditorDePlaylists;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.Image;
//import se.michaelthelin.spotify.model_objects.specification;
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
	
	
	@GetMapping("pesquisaImagemDoAlbum")
	public String pesquisaImagemDoAlbum ( @RequestParam(name="pesquisa", required=false, defaultValue="album") String pesquisa) {
		Album[] album = new Album[1];
		album = Comunicador.pesquisaAlbum(pesquisa, 1);
		Image imagem = album[0].getImages()[0];
		return imagem.getUrl();
	}
	
	@GetMapping("pesquisaMusicasDoAlbum")
	public MusicaParaEnviar[] pesquisaMusicasDoAlbum ( @RequestParam(name="pesquisa", required=false, defaultValue="album") String pesquisa) {
		
		Album[] album = new Album[1];
		album = Comunicador.pesquisaAlbum(pesquisa, 1);
		String albumID = album[0].getId();
		Image imagem = album[0].getImages()[0];
		imagem.getUrl();
		int quantidade = 20;
		Track[] musicasDoAlbum;
		musicasDoAlbum = Comunicador.getAlbunsTracks(albumID);
		quantidade = musicasDoAlbum.length;
		
		MusicaParaEnviar[] musicasDoAlbumParaEnviar = new MusicaParaEnviar[quantidade];
		
		String[] ids = new String[quantidade];
			for(int i = 0; i < quantidade; i ++) {
			if(musicasDoAlbum[i] != null) {
				musicasDoAlbumParaEnviar[i] = new MusicaParaEnviar(musicasDoAlbum[i]);
				ids[i] = musicasDoAlbum[i].getId();
			}
		}
		APIdePesquisa.setMusicaParaEnviarAudioFeatures(quantidade, musicasDoAlbumParaEnviar, ids);
		return musicasDoAlbumParaEnviar;	
	}
	
	@GetMapping("pesquisaMusicasDaPlaylist")
	public MusicaParaEnviar[] pesquisaMusicasDaPlaylist ( @RequestParam(name="pesquisa", required=false, defaultValue="playlist") String pesquisa) {
		
		Playlist[] playlist = new Playlist[1];
		playlist = Comunicador.pesquisaPlaylists(pesquisa, 1);
		String playlistID = playlist[0].getId();
		
		Track[] musicasDaPlaylist;
		musicasDaPlaylist = Comunicador.getPlaylistsTracks(playlistID);
		int quantidade = musicasDaPlaylist.length;
		
		MusicaParaEnviar[] musicasDaPlaylistParaEnviar = new MusicaParaEnviar[quantidade];
		
		String[] ids = new String[quantidade];
			for(int i = 0; i < quantidade; i ++) {
			if(musicasDaPlaylist[i] != null) {
				musicasDaPlaylistParaEnviar[i] = new MusicaParaEnviar(musicasDaPlaylist[i]);
				ids[i] = musicasDaPlaylist[i].getId();
			}
		}
		APIdePesquisa.setMusicaParaEnviarAudioFeatures(quantidade, musicasDaPlaylistParaEnviar, ids);
		return musicasDaPlaylistParaEnviar;	
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
		playlists  = Comunicador.getCurrentUsersPlaylist();
		int quantidade;
		quantidade = playlists.length;
		PlaylistParaEnviar[] playlistsParaEnviar = new PlaylistParaEnviar[quantidade];
		for (int i = 0; i < quantidade; i++){			
			if(playlists[i] != null) {
				playlistsParaEnviar[i] = new PlaylistParaEnviar(playlists[i]);
			}
		}
		return playlistsParaEnviar;
	}

	public static void setMusicaParaEnviarAudioFeatures(int quantidade, MusicaParaEnviar[] musicaParaEnviar, String[] ids) {
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
				musicaParaEnviar[i].parametros[8] = features.getKey();
				
				musicaParaEnviar[i].parametros[9] = features.getMode().getType();
				musicaParaEnviar[i].parametros[10] = features.getTimeSignature();
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
	
	@GetMapping("adicionaMusicaNaPlaylist")
	public void adicionaAPlaylist(@RequestParam(name="MusicaID", required=false, defaultValue=" ")String ID, @RequestParam(name="PlaylistID", required = false, defaultValue=" ")String playlistId) {
		Playlist lista = Comunicador.getPlaylist(playlistId);
			Track track = Comunicador.getTrack(ID);
			String[] uris = new String[1];
			uris[0] = track.getUri();
			Comunicador.adicionaMusica(lista, uris);		
	}
	
	@GetMapping("removeMusicaDaPlaylist")
	public void removeDaPlaylist(@RequestParam(name="MusicaID", required=false, defaultValue=" ")String ID, @RequestParam(name="PlaylistID", required = false, defaultValue=" ")String playlistId) {
		Playlist lista = Comunicador.getPlaylist(playlistId);
				String p1 = "[{\"uri\":\"";
				String p2 = "\"}]";
				Track musica = Comunicador.getTrack(ID);
				JsonArray  musicaURI = JsonParser.parseString(p1.concat(musica.getUri().concat(p2))).getAsJsonArray();
				EditorDePlaylists editor = new EditorDePlaylists();
				editor.removeMusica(lista, musicaURI);
	}
	
	@GetMapping("deletaPlaylist")
	public void deletaPlaylistDoUsuario(@RequestParam(name="PlaylistID", required=false, defaultValue=" ")String playlistID) {
		EditorDePlaylists editor = new EditorDePlaylists();
		editor.deleteUsersPlaylist(playlistID);
	}
}

