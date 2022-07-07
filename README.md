**Projeto Turingfy**
1. Informação sobre as Classes do Projeto
    - com.pacote
        - Classes que contêm a main para executar o projeto.
        - 18/06/2022 : Como rodar o projeto:
            1. Acesso a Interface Web:
                1. Abra a pasta "Projeto" no eclipse/programa de sua preferência.
                2. Execute o arquivo: SiteLabooAplication.java.
                3. Abra a pasta "Interface Web" no terminal.
                4. Execute os comandos `npm install @mui/material @emotion/react @emotion/styled` e `npm start`
                    - Caso apareça a mensagem de erro: 'react-scripts' is not recognized as an internal 
                    or external command, rodar: `npm install react-scripts –save`
                5. Após um certo tempo, a página localhost:3000 vai abrir, aperte o botão conectar.
                6. Na página nova, clique na URL e dê enter.
                7. Faça login na conta teste.
                    - dummy2spotifylaboo@gmail.com / Dummy123#
                8. Aperte o link para voltar ao Turingfy.
                9. Navegue normalmente pelo sistema.
                - Obs: Funcionalidades Implementadas
                    - Preview de música
                    - Pesquisa de playlists, albums, músicas e artistas 
                    - Visualização de músicas de Artista, Playlist(Públicas e Privadas) e Álbum
                    - Login no Spotify 
                    - Filtragem de Músicas em Pesquisa Avançada
                    - Criar e Deletar Playlists
                    - Remover e Adicionar Musicas a Playlist
                - Obs: Ajuste o zoom da página, até ela ficar adequada à visualização.
                - Obs: [PaginaVePlaylist](http://localhost:3000/PaginaVePlaylist) não está acessível da Página Principal e deve ser acessada para visualizar as playlists do usuário.

    - com.pacote.controllers
        - XXXXController: Classes que definem o comportamento das páginas web inseridas diretamente no sistema;

    - com.pacote.operacoesTerminal
        - Classes Relacionadas A Execução de Comandos Na Interface do terminal
        - ComunicadorDoSpotify: Classe com métodos de autenticação e autorização para coleta e envio de dados na API do ComunicadorDoSpotify;
        - BuscadorDoSpotify: Classe com métodos que recolhem informação do Spotify;
        - EditorDePlaylist: Classe com métodos para criar e deletar playlists, alterar nome, adicionar e remover músicas;
        - OperacoesDoUsuario: Classe que implementa os métodos chamados na Main, as ações do usuário no sistema;
        - OperacoesInternas: Classe que conecta os métodos da OperacoesDoUsuario com os métodos presentes em outras classes;

    - com.operacoesReact
        - Classes Com Especifidades Para Comunicação com a Interface Web;
        - Comunicador: Versão alterada do ComunicadorDoSpotify, definida para suprir a Interface Web;

    - com.pacote.API
        - Conjunto De Classes que Realizam o Mapeamento da API da Interface WEB;
        - APIdePesquisa: Classe com métodos de comunicação com a página WEB usando React; 
        - APIMusicasAlbum: Classe com o método de exibição de Músicas de um Álbum;
        - APIMusicasArtista: Classe com o método para exibir Músicas de um Artista;
        - APIMusicasPlaylist: Classe com o método para exibir Músicas de uma Playlist;

    - com.pacote.itemParaEnviar
        - Classes com Objetos que associam um item da biblioteca da Web API do Spotify com itens utilizáveis no react;
        - AlbumParaEnviar;
        - ArtistaParaEnviar;
        - MusicaParaEnviar;
        - PlaylistParaEnviar;

    - com.pacote.customComparator
        - Classes para implementação da tabela de músicas, a qual pode ser reorganizada de acordo com os atributos de AudioFeatures;
        - Cada uma delas contém um Comparator<Track> para cada feature e possui um único método para executar um sort de uma List<Track>        
        implementado na Classe OperacoesDoUsuario;
