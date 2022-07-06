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
            DANCABILIDADE:
            {musica.parametros[0]}
            ENERGIA:
            {musica.parametros[1]}
        </div>
    )
    }
    else
    return null;
}