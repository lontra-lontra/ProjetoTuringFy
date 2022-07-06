
import axios from "axios";
import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import * as S from "./styles";


const PaginaMusicasArtista = () => {
    
    const location = useLocation();
    const state = location.state as any
    const [musicasBusca, setMusicasBusca] = useState({musicas:[]})
    
    axios.get('http://localhost:8080/api/album', { params: { pesquisa: state.textoBusca} } ).then (res => {
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
            <S.TextoResultados>Musicas do Artista</S.TextoResultados>

            <S.ItemResultados>Musica do Artista     <S.BotaoMusica>+</S.BotaoMusica></S.ItemResultados>

        </S.CorpoPagina>

        </>
    )
}

export default PaginaMusicasArtista
;