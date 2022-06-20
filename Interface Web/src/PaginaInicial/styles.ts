import styled from "styled-components";


export const CorpoTodo = styled.div`
    font-family: 'Nunito Sans', sans-serif;
    font-weight: 400;
    display: flex;
    flex-direction: column;
    justify-content: baseline;
    width: 100vw;
    height: 100vh;
`
export const header = styled.header`
    width: 100%;
    height: 17vh;
    background-color: black;
    display: flex;
    flex-direction:row ;
    justify-content:baseline;
    align-items: center;
`

export const corpo = styled.div`
    display: flex;
    flex-direction: row;
`

export const TituloTuring = styled.p`
    color: white;  
    font-size: 4rem;
    margin-left: 4rem;
`

export const TituloFy = styled.span`
    color: #0CC629;   
    font-size: 4rem;
    margin-left: 0;
`

export const Slogan = styled.p`
    color: white;
    position: absolute;
    margin-left: 35%;
    font-size: 3em;
`

export const ColunaEsquerda = styled.div`
    align-items: center;
    width: 20%;
    height:fit-content;
    background-color: #0F0F0F;
    display: flex;
    flex-direction: column;
    justify-content:space-around;
    min-height:83vh;
`

export const SuasPlaylists = styled.div`
    font-weight: bold;
    color: white;
    font-size: 2.8em;
    margin-top: 2rem,;
    font-family: 'Nunito Sans', sans-serif;
`

export const SuasPlaylistsNomes = styled.button`
    font-family: 'Nunito Sans', sans-serif;
    padding: 0.6rem 1.2rem;
    color: white;
    font-size: 2.2rem;
    background-color:#0F0F0F;
    border-radius: 24px;
    border: 1px solid white;   
    text-align: center; 
    align-items: center;
    cursor:pointer;
`

export const BlocoDeBusca = styled.div`
    display: flex;
    flex-direction: column;
    width: 80%;
    background-color: #1B1B1B;
    color: white;
    font-family: 'Nunito Sans', sans-serif;
    align-items: flex-start;
`

export const Buscar = styled.h2`
    font-size: 5rem;
    font-family: 'Nunito Sans', sans-serif;
    margin-left: 4.8rem;
    height: min-content;
    margin-bottom: 0.8rem;
`

export const DescricaoBusca = styled.p`
    margin-top: 0px;
    margin-left: 4.8rem;
    font-size: 2.4rem;
    font-family: 'Nunito Sans', sans-serif;     
`
export const BarraBuscaEFiltro = styled.div`
    display: flex;
    margin-left: 4.8rem;
    gap:1.6em;
`
export const BotaoFiltro = styled.button`
    background-color: #1B1B1B;
    cursor: pointer;
    border-color: #1B1B1B;
`
export const BarraBusca = styled.input`

    width: 943px;
    height: 67px;
    background: #0F0F0F;
    border-radius: 37px;
    font-size: 32px;
    font-family: 'Nunito Sans', sans-serif;    
    color: white;
    padding-left: 24px;
`
export const BotoesBuscaETexto = styled.div`
    display: flex;
    flex-direction: column;
    align-items: flex-start;
`

export const BotaoBuscar = styled.button`
    display: flex;
    width: 12vw;
    height: 4vh;
    background-color:black;
    color:white;
    font-size: 40px;    
    font-family: 'Nunito Sans', sans-serif;    
    border-radius:15px;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    margin-top: 1.2rem;
    margin-left: 4rem;
    font-style: bold;
    padding: 1.2rem;
`
export const BotoesBusca = styled.div`
    display:flex;
    flex-direction: row;
    align-items: center;

`

export const TextoBuscar = styled.p`
    font-size: 3.2rem;
    margin-left: 4.8rem;
`