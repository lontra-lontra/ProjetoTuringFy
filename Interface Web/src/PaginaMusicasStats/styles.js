import styled from "styled-components";


export const CorpoPagina = styled.div`
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: start;
    overflow: scroll;
`

export const Tudo = styled.div`
    position:fixed;
    width: 100%;
    height: 100%;
    background-color: #1B1B1B;
`

export const TextoPlaylists = styled.p`
color:white;
font-family: 'Nunito Sans', sans-serif;
font-size: 4rem;
margin-left: 4.8rem;
margin-bottom: 0px;
margin-top: 5px;
`

export const TextoSelecioneAPlaylist = styled.p`
color:white;
font-family: 'Nunito Sans', sans-serif;
font-size: 2.4rem;
margin-left: 4.8rem;
margin-top: 5px;
`

export const TextoResultados = styled.p`
color:white;
font-family: 'Nunito Sans', sans-serif;
font-size: 3rem;
margin-left: 4.8rem;
`

export const Header = styled.header`
    width: 100%;
    height: 17vh;
    background-color: black;
    display: flex;
    flex-direction:row ;
    justify-content:baseline;
    align-items: center;
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

export const BotaoPesquisar = styled.button`
  display: flex;
  width: 18vw;
  height: 4vh;
  background: black; 
  border-color:#0CC629 ;
  border-radius: 20px;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  width: fit-content;
  color: white;
  font-size: 1.5rem;
`

export const inputPesquisar = styled.input`

    width: 20vw;
    height: 2rem;
    background: #fff;
    border-radius: 37px;
    font-size: 32px;
    font-family: 'Nunito Sans', sans-serif;    
    color: black;
    padding-left: 24px;
`
export const divPesquisar = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  margin-bottom: 10px;
  margin-left: 4.8rem;
  gap:1rem;
`
export const divSelecioneAPlaylist = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  margin-bottom: 1rem;
  row-gap: 5px;
  margin-top: 0px;
`


export const TextoParametros = styled.p`
  color:white;
  font-family: 'Nunito Sans', sans-serif;
  font-size: 1.4rem;
  margin: 0px;
`

export const divCorpoParametros = styled.div`
  margin-top: 0;
`
export const divPesquisaEParametros = styled.div`
  display: flex;
  flex-direction:row;
  gap: 10vw;
`