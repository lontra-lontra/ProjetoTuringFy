import java.util.*;

public class Playlist {
	private String nome;
	private ArrayList<Musica> playlist;
	
	public Playlist (String nome) {
		this.nome = nome;
		this.playlist = new ArrayList<Musica>();
	}
	
	// so adiciona uma musica a playlist se a musica nao estiver na playlist
	public Playlist adicionarMusica(Playlist lista, Musica musica) {
		int i = lista.playlist.lastIndexOf(musica);
		if(i == -1) {
			lista.playlist.add(musica);
		}
		return lista;
	}
	//ver se precisa da informacao lista
	
	public void removerMusica(Playlist lista, Musica musica) {
		int index = lista.playlist.lastIndexOf(musica);
		lista.playlist.remove(index);
	}
	
	public void removerPlaylist(Playlist lista) {
		lista.playlist.removeAll(playlist);
		lista.nome = null;
		lista = null;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Playlist [nome=" + nome + ", playlist=" + playlist + "]";
	}	
	
	public static void main(String[] args) {
		Musica comoTa = new Musica("Como e que ta?", 
			new String[] {"Major RD", "Rock Danger", "El Lif Beats"}, 2021, 139); 
		Musica aulas = new Musica("Aulas e Cursos", 
			new String[] {"DNASTY"}, 2019, 214);
		Musica fragancia = new Musica("Fragancia", 
				new String[] {"Major RD", "L7NNON", "Offlei Sounds"}, 2020, 172);
		Playlist playlist1 = new Playlist("playlist 1");
		playlist1.adicionarMusica(playlist1, fragancia);
		playlist1.adicionarMusica(playlist1, aulas);
		playlist1.adicionarMusica(playlist1, comoTa);
		System.out.println(playlist1);
	}	
	//private getMusicabyName(String name) {
	//	
	//}
	
	
	//private int getIndex (ArrayList<Musica> lista, Musica musica){
	//	int index = lista.lastIndexOf(musica);
	//	return index;
	//}
}
