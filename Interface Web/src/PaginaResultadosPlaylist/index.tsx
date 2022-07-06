
import axios from "axios";
import React, { useState } from "react";
import { useLocation } from "react-router-dom";
import * as S from "./styles";


const PaginaResultadosPlaylist = () => {
    
    const location = useLocation();
    const state = location.state as any
    const [musicasBusca, setMusicasBusca] = useState({musicas:[]})
    
    axios.get('http://localhost:8080/api/playlist', { params: { pesquisa: state.textoBusca} } ).then (res => {
        console.log()
        setMusicasBusca({musicas: res.data});
    });

    
    return(
        <>
        <S.CorpoPagina>
            <S.Header>
                <S.TituloTuring>Turing</S.TituloTuring><S.TituloFy>fy</S.TituloFy>
                <S.Slogan>O seu super gerenciador musical</S.Slogan>
            </S.Header>
            <S.TextoResultados>Resultados</S.TextoResultados>

            <S.ResultadosPara>Resultados para:  {state.textoBusca}</S.ResultadosPara>

                {musicasBusca.musicas.map(musica => <><S.ItemResultados>{musica["nome"]}      <S.BotaoMusica>+</S.BotaoMusica></S.ItemResultados></>)}

        </S.CorpoPagina>

        </>
    )
}

export default PaginaResultadosPlaylist;