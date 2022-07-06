import { buildTimeValue } from "@testing-library/user-event/dist/utils";
import React from "react";
export default function Musica({musica,addmusica})
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
             <button onClick={function(){addmusica(musica.id)}}> adicionar musica </button>
        </div>

    )
    }
    else
    return null;
}