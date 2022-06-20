**Projeto Turingfy**
1. Informação sobre as Classes do Projeto
    - com.pacote
        - Classes que contêm a main para executar o projeto.
        - 18/06/2022 : Como rodar o projeto:
            1. Funcionalidades no Terminal:
                1. Abra a pasta "Projeto" no eclipse/programa de sua preferência.
                2. Execute o arquivo: SiteLabooAplication.java.
                3. Espere aparecer a mensagem de Boot no console. Não responda a mensagem ainda.
                4. Abra no navegador o endereço: [localhost:8080/aut](http://localhost:8080/aut).
                5. Clique no link disponível na página.
                6. Após o redirecionamento, faça login usando a conta de teste: dummy2spotifylaboo@gmail.com / Dummy123#
                7. Retorne ao Console e insira 1 para iniciar o sistema.
                8. Navegue livremente no sistema.
            2. Visualização da página Web [Incompleta]
                1. Repita os passos 1. e 2. do item anterior.
                2. Abra a pasta "Interface Web" no terminal.
                3. Execute os comandos `npm install` e `npm start`
                    - Caso apareça a mensagem de erro: 'react-scripts' is not recognized as an internal 
                    or external command Rodar: `npm install react-scripts –save`
                4. Após um certo tempo, a página localhost:3000 vai abrir, aperte o botão conectar.
                5. Faça login na conta teste.
                6. Navegue normalmente pelo sistema.

    - com.pacote.controllers
        - Classes de Execução dos Procedimentos do projeto.
        - ComunicadorDoSpotify: Classe com métodos de autenticação e autorização para coleta e envio de dados na API do ComunicadorDoSpotify;
        - BuscadorDoSpotify: Classe com métodos que recolhem informação do Spotify;
        - EditorDePlaylist: Classe com métodos para criar e deletar playlists, alterar nome, adicionar e remover músicas;
        - OperacoesDoUsuario: Classe que implementa os métodos chamados na Main, as ações do usuário no sistema;
        - OperacoesInternas: Classe que conecta os métodos da OperacoesDoUsuario com os métodos presentes em outras classes;
        - ConversorDeTipo: Classe Adaptadora entre os métodos nativos da library Spotify-Web-Api e os formatos usados no sistema;
        - XXXXController: Classes que definem o comportamento das páginas web inseridas diretamente no sistema;
        - Comunicador: Classe com elementos similares a Classe ComunicadorDoSpotify e BuscadorDoSpotify, para melhor execução da interface WEB;
        - APIdePesquisa: Classe com métodos de comunicação com a página WEB usando React; 
        - XXXXParaEnviar: Classes com métodos auxiliares para transferência de dados para a página WEB;

    - com.pacote.customComparator
        - Classes para implementação da tabela de músicas, a qual pode ser reorganizada de acordo com os atributos de AudioFeatures;
        - Cada uma delas contém um Comparator<Track> para cada feature e possui um único método para executar um sort de uma List<Track>        
        implementado na Classe OperacoesDoUsuario;

**Observações**
* Em decorrência de problemas na utilização do Git, uma branch main foi deletada e acabamos perdendo  commits de alguns alunos. Além disso, adotamos como estratégia de projeto, nessa segunda parte do projeto, seguir o estilo de "live coding". Ou seja, programamos em conjunto e e realizando commits em blocos, o que justifica a concentração de commits na conta de poucos alunos do grupo.
