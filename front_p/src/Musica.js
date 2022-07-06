import { buildTimeValue } from "@testing-library/user-event/dist/utils";
import React from "react";
export default function Musica({musica})
{
    if(musica.respeitaParametro0 || true )
    {
    return (
        <div>
            {musica.nome}
            ======
            P0:
            {musica.parametros[0]}
            P1:
            {musica.parametros[1]}
            P2:
            {musica.parametros[2]}
            P3:
            {musica.parametros[3]}
            P4:
            {musica.parametros[4]}
            P5:
            {musica.parametros[5]}
            P6:
            {musica.parametros[4]}
            P7:
            {musica.parametros[5]}
        </div>
    )
    }
    else
    return null;
}