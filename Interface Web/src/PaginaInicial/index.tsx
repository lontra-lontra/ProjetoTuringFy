import React, { useState } from "react";
import * as S from "./styles";
import { useNavigate } from "react-router-dom";

function PaginaInicial() {

    const [textoPesquisa, setTextoPesquisa] = useState("")

    let navega = useNavigate();
    const navegaResultadosMusica = () =>{
        navega("/ResultadosMusica", {state:{textoBusca: textoPesquisa}})
    }
    const navegaResultadosArtista = () =>{
        navega("/ResultadosArtista", {state:{textoBusca: textoPesquisa}})
    }
    const navegaResultadosPlaylist = () =>{
        navega("/ResultadosPlaylist", {state:{textoBusca: textoPesquisa}})
    }
    const navegaResultadosAlbum = () =>{
        navega("/ResultadosAlbum", {state:{textoBusca: textoPesquisa}})
    }

return(
    <>
    <S.CorpoTodo>
        <S.header>
            <S.TituloTuring>Turing</S.TituloTuring><S.TituloFy>fy</S.TituloFy>
            <S.Slogan>O seu super gerenciador musical</S.Slogan>
        </S.header>
        <S.corpo>
            <S.ColunaEsquerda>
                <S.SuasPlaylists>Suas Playlists</S.SuasPlaylists>
                <a href="Levara para a playlist" target="_blank"><S.SuasPlaylistsNomes>Rock Brasileiro</S.SuasPlaylistsNomes></a>
                <a href="Levara para a playlist" target="_blank"><S.SuasPlaylistsNomes>Jazz Brasileiro</S.SuasPlaylistsNomes></a>
                <a href="Levara para a playlist" target="_blank"><S.SuasPlaylistsNomes>Pop</S.SuasPlaylistsNomes></a>
                <a href="Levara para a playlist" target="_blank"><S.SuasPlaylistsNomes>No Chuveiro</S.SuasPlaylistsNomes></a>
                <a href="Levara para a playlist" target="_blank"><S.SuasPlaylistsNomes>Indie</S.SuasPlaylistsNomes></a>
            </S.ColunaEsquerda>
            <S.BlocoDeBusca>
                <S.Buscar>Buscar</S.Buscar>
                <S.DescricaoBusca>Pesquise por  título, autor, nome do álbum ou playlist</S.DescricaoBusca>
                <S.BarraBuscaEFiltro>

                    <S.BarraBusca placeholder="Digite sua pesquisa" id="InputBusca"
                     onChange={(e) => {setTextoPesquisa(e.target.value);}}>

                     </S.BarraBusca>
                </S.BarraBuscaEFiltro>
                <S.BotoesBuscaETexto>
                    <S.TextoBuscar>Buscar Por:</S.TextoBuscar>
                    <S.BotoesBusca>
                        <S.BotaoBuscar onClick={()=>{navegaResultadosMusica()}}>Musica</S.BotaoBuscar>
                        <S.BotaoBuscar onClick={()=>{navegaResultadosPlaylist()}}>Playlist</S.BotaoBuscar>
                        <S.BotaoBuscar onClick={()=>{navegaResultadosAlbum()}}>Album</S.BotaoBuscar>
                        <S.BotaoBuscar onClick={()=>{navegaResultadosArtista()}}>Artista</S.BotaoBuscar>
                    </S.BotoesBusca>
                </S.BotoesBuscaETexto>
            </S.BlocoDeBusca>
        </S.corpo>
    </S.CorpoTodo>
    </>
)
}

export default PaginaInicial