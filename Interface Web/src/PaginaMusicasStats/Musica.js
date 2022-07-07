import { buildTimeValue } from "@testing-library/user-event/dist/utils";
import React from "react";
import * as S from "./styles";

export default function Musica({musica,funcaoDoBotao,fraseNoBotao})
{
    if(musica.respeitaParametro0 || true )
    {
    return (
        <>
            <tbody>
            <S.tableRow>
            <S.styledTdNome>{musica.nome}</S.styledTdNome>
            <S.styledTd>{musica.parametros[0]}</S.styledTd>
            <S.styledTd>{musica.parametros[1]}</S.styledTd>
            <S.styledTd>{musica.parametros[2]}</S.styledTd>
            <S.styledTd>{musica.parametros[3]}</S.styledTd>
            <S.styledTd>{musica.parametros[4]}</S.styledTd>
            <S.styledTd>{musica.parametros[5]}</S.styledTd>
            <S.styledTd>{musica.parametros[6]}</S.styledTd>
            <S.styledTd>{musica.parametros[7]}</S.styledTd>
            <S.styledTd>   <a href={musica.url} target="_blank" rel="noreferrer" ><S.BotaoAdicionar><img src={require('../Imagens/Play.png')} width = "40px" height="40px" alt=""></img></S.BotaoAdicionar></a> </S.styledTd>
            <S.styledTd><S.BotaoAdicionar onClick={function(){funcaoDoBotao([musica.id,musica.uri])}}> + </S.BotaoAdicionar></S.styledTd>
            </S.tableRow>
            </tbody>
        </>        
    )
    }
    else
    return null;
}