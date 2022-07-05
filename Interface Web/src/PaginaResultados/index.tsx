
import axios from "axios";
import React, { useState } from "react";
import { useLocation } from "react-router-dom";
import * as S from "./styles";


const PaginaResultados = () => {
    
    const location = useLocation();
    const state = location.state as any
    const [musicasBusca, setMusicasBusca] = useState({musicas:[]})
    
    axios.get('http://localhost:8080/api/pesquisa', { params: { pesquisa: state.textoBusca} } ).then (res => {
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
            <ul>
                {musicasBusca.musicas.map(musica => <S.ItemResultados>{musica["nome"]}</S.ItemResultados>)}
            </ul>
        </S.CorpoPagina>

        </>
    )
}

export default PaginaResultados;