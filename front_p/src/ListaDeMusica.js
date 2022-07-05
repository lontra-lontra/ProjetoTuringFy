import React from "react"
import Musica from "./Musica"
export default function ListaDeMusica({musicas})
{
    return (
        musicas.map(musica => {
            return <Musica key={musica.id} musica={musica}/>
        })
    )
}