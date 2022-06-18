import React from "react";
import * as S from "./styles";
import Filtro from "../Imagens/Filtro.svg"
import { useNavigate } from "react-router-dom";

function PaginaInicial() {

    let navega = useNavigate();
    const navegaResultados = () =>{
        navega("/Resultados")
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
                    <S.BarraBusca></S.BarraBusca>
                    <S.BotaoFiltro><img src={Filtro}/></S.BotaoFiltro>
                </S.BarraBuscaEFiltro>
                <S.BotaoBuscar onClick={navegaResultados}>Buscar</S.BotaoBuscar>
            </S.BlocoDeBusca>
        </S.corpo>
    </S.CorpoTodo>
    </>
)
}

export default PaginaInicial