public class Album {
	private Musica [] musicas;
	private String artista;
	private String nome;
	private int ano;
	
	public Album(Musica[] musicas, String artista, String nome, int ano) {
		this.musicas = musicas;
		this.artista = artista;
		this.nome = nome;
		this.ano = ano;
	}
	// nao vou criar setters pois um album nao é volatil que nem uma playlist
	// criarei um DestroiAlbum pro caso em que seja criado de forma erronea

	public Musica[] getMusicas() {
		return musicas;
	}

	public String getArtista() {
		return artista;
	}

	public String getNome() {
		return nome;
	}

	public int getAno() {
		return ano;
	}
	
	public void DestroiAlbum(Album lista) {
		for (int i = 0; i < lista.musicas.length; i++) lista.musicas[i] = null;
		lista.nome = null;
		lista.artista = null;
		lista.ano = 0;
		lista = null;
	}
	

}
