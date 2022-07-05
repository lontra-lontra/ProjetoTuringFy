import { buildTimeValue } from "@testing-library/user-event/dist/utils";
import React from "react";
export default function Musica({musica})
{
    if(musica.ParametroOk[0])
    {
    return (
        <div>
            {musica.nome}
            ENERGIA:
            {musica.parametros[0]}
        </div>
    )
    }
    else
    return null;
}