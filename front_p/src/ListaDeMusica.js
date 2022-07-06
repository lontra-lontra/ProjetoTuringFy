import React from "react"
import Musica from "./Musica"
export default function ListaDeMusica({musicas,range})
{
    var musicasX = [];
    var size = musicas.lenght
    console.log(range);
    var filtered = musicas.filter(function(element, index, array) {
  return (range[0] < element.parametros[0]*100 )&& ( element.parametros[0]*100 < range[1] ) ;
});
    for(let i = 0;i < size;i++)
    {
            musicasX.push(musicas[i])
    }
    console.log(musicas);
    console.log(musicasX);
    console.log(filtered);
    return (
        filtered.map(musica => {
            return <Musica key={musica.id} musica={musica} />
        })
    )
}