import { buildTimeValue } from "@testing-library/user-event/dist/utils";
import React from "react";
export default function Musica({musica,funcaoDoBotao,fraseNoBotao})
{
    if(musica.respeitaParametro0 || true )
    {
    return (
        <div>
            {musica.nome}
            ======
            ---Dancabilidade:
            {musica.parametros[0]}
            ---Energia:
            {musica.parametros[1]}
            ---Tempo:
            {musica.parametros[2]}
            ---Intensidade:
            {musica.parametros[3]}
            ---Fala:
            {musica.parametros[4]}
            ---Instrumentalidade:
            {musica.parametros[5]}
            --Acusticidade:
            {musica.parametros[4]}
            ---"Liveness":
            {musica.parametros[5]}
             <button onClick={function(){funcaoDoBotao([musica.id,musica.uri])}}> {fraseNoBotao } </button>
        </div>

    )
    }
    else
    return null;
}