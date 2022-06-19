import styled from "styled-components";


export const CorpoTodo = styled.div`
    font-family: 'Nunito Sans', sans-serif;
    font-weight: 400;
    display: flex;
    flex-direction: column;
    justify-content: baseline;
`
export const header = styled.header`
    width: 100%;
    height: 17vh;
    background-color: black;
    display: flex;
    flex-direction:row ;
    justify-content:baseline;
`

export const corpo = styled.div`
    display: flex;
    flex-direction: row;
`

export const TituloTuring = styled.p`
    color: white;  
    font-size: 64px;
    margin-left: 56px;
    margin-top: 32px;
`

export const TituloFy = styled.span`
    color: #0CC629;   
    font-size: 64px;
    margin-left: 0;
    margin-top: 32px;
`

export const Slogan = styled.p`
    color: white;   
    font-size: 48px;
    margin-left: 17%;
    margin-top: 54px;
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
    font-size: 48px;
    margin-top: 20px;
    font-family: 'Nunito Sans', sans-serif;
`

export const SuasPlaylistsNomes = styled.button`
    font-family: 'Nunito Sans', sans-serif;
    padding: 6px 12px;
    color: white;
    font-size: 36px;
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
    font-size: 56px;
    font-family: 'Nunito Sans', sans-serif;
    margin-left: 48px;
    height: min-content;
    margin-bottom: 8px;
`

export const DescricaoBusca = styled.p`
    margin-top: 0px;
    margin-left: 48px;
    font-size: 24px;
    font-family: 'Nunito Sans', sans-serif;     
`
export const BarraBuscaEFiltro = styled.div`
    display: flex;
    margin-left: 48px;
    gap:16px;
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

export const BotaoBuscar = styled.button`
    display: flex;
    width: 160px;
    height: 48px;
    background-color:#0CC629;
    opacity: 0.7;
    color:white;
    font-size: 40px;    
    font-family: 'Nunito Sans', sans-serif;    
    border-radius:15px;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    margin-top: 16px;
    margin-left: 48px;
`