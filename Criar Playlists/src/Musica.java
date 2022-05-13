import java.util.Arrays;

public class Musica {
	private String nome;
	private String [] artistas;
	private int ano;
	private int duracaoSegundos;
	//colocar duracao em segundos para ser mais facil de comparar a duracao das musicas?
	
	public Musica(String nome, String[] artistas, int ano, int duracaoSegundos) {
		this.nome = nome;
		this.artistas = artistas;
		this.ano = ano;
		this.duracaoSegundos = duracaoSegundos;
	}
	//exemplo pra construir uma musica 
	//Musica comoTa = new Musica("Como e que ta?", 
	//	new String[] {"Major RD", "Rock Danger", "El Lif Beats"}, 2021, 139);

	public String getNome() {
		return nome;
	}

	public String[] getArtistas() {
		return artistas;
	}

	public int getAno() {
		return ano;
	}

	public int getDuracaoSegundos() {
		return duracaoSegundos;
	}

	@Override
	public String toString() {
		return "Musica [nome=" + nome + ", artistas=" + Arrays.toString(artistas) + ", ano="
				+ ano + ", duracao " + duracaoSegundos/60 + " min " + duracaoSegundos%60 + " seg]\n";
	}
	/* main pra testar o codigo
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Musica comoTa = new Musica("Como e que ta?", 
			new String[] {"Major RD", "Rock Danger", "El Lif Beats"}, 2022, 139);
		
		System.out.println(comoTa);
	}
	*/
}