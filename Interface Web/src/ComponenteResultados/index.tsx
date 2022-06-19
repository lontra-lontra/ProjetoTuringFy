import axios from "axios";
import { useState } from "react";
import * as S from "./styles";

interface interfacePesquisa{
    TextoBusca: string;
}

const ComponenteResultados: React.FC<interfacePesquisa> = ({TextoBusca}) => {

const [musicasBusca, setMusicasBusca] = useState({musicas:[]})

axios.get('http://localhost:8080/api/pesquisa', { params: { pesquisa: {TextoBusca} } } ).then (res => {
    console.log()
    setMusicasBusca({musicas: res.data});
});

return(
    <>
        <p>teste</p>
        <ul>
            {musicasBusca.musicas.map(musica => <S.ItemResultados>{musica["nome"]}</S.ItemResultados>)}
        </ul>
    </>
);

}

export default ComponenteResultados;