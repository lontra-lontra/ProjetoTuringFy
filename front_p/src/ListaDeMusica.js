import React from "react"
import Musica from "./Musica"
export default function ListaDeMusica({musicas,range,addmusica})
{
    var musicasX = [];
    var size = musicas.lenght
    console.log(range);
    var range0 = range[0];
    var range1 = range[1];
    var filtered = musicas.filter(function(element, index, array) {
  return false||((range[0][0] <= element.parametros[0]*100 )&& ( element.parametros[0]*100 <= range[0][1] )
  &&     (range[1][0] <= element.parametros[1]*100 )&& ( element.parametros[1]*100 <= range[1][1] )
 &&      (range[2][0] <= element.parametros[2]     )&& ( element.parametros[2]     <= range[2][1] )
  &&     (range[3][0] <= element.parametros[3] )&& ( element.parametros[3] <= range[3][1] )
  &&     (range[4][0] <= element.parametros[4]*100 )&& ( element.parametros[4]*100 <= range[4][1] )
  &&     (range[5][0] <= element.parametros[5]*100 )&& ( element.parametros[5]*100 <= range[5][1] )
  &&     (range[6][0] <= element.parametros[6]*100 )&& ( element.parametros[6]*100 <= range[6][1] )
  &&     (range[7][0] <= element.parametros[7]*100 )&& ( element.parametros[7]*100 <= range[7][1] ));
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
            return <Musica key={musica.id} musica={musica} addmusica={addmusica} />
        })
    )
}