import React,{useState,useRef,useEffect} from 'react'
import ListaDeMusica from './ListaDeMusica';
import ListaDePlaylists from './ListaDePlaylists';
import axios from 'axios';
import Box from '@mui/material/Box';
import Slider from '@mui/material/Slider';
import * as S from "./styles";

function valuetext(value) {
  return `${value}`;
}

export default function VePlaylist()
{
    React.useEffect(() => {
    pegaplaylists()
  }, []);
    

    const [rangeparam0, setRangeparam0] = React.useState([0, 100]);
    const [rangeparam1, setRangeparam1] = React.useState([0, 100]);
    const [rangeparam2, setRangeparam2] = React.useState([0, 200]);
    const [rangeparam3, setRangeparam3] = React.useState([-50, 50]);
    const [rangeparam4, setRangeparam4] = React.useState([0, 100]);
    const [rangeparam5, setRangeparam5] = React.useState([0, 100]);
    const [rangeparam6, setRangeparam6] = React.useState([0, 100]);
    const [rangeparam7, setRangeparam7] = React.useState([0, 100]);

    const [ultimoparam, setUltimoparam] = React.useState(0);
    const [resultado,setResultado] = useState([])
    const [ranges,setRanges] = useState([0,100])
    const [playlistsDoCliente,setPlaylistsDoCliente] = useState([]);

        const barraDePesquisa = useRef()
    const barraDoNomeDaPlaylist =useRef()
    const seletorDePlaylist =useRef()

  const handleChangep0 = (event, newValue) => {
    setUltimoparam(0)
    setRangeparam0(newValue);
  };
  const handleChangep1 = (event, newValue) => {
    setUltimoparam(1)
    setRangeparam1(newValue);
  };
  const handleChangep2 = (event, newValue) => {
    setUltimoparam(2)
    setRangeparam2(newValue);
  };
  const handleChangep3 = (event, newValue) => {
    setUltimoparam(3)
    setRangeparam3(newValue);
  };    const handleChangep4 = (event, newValue) => {
    setUltimoparam(4)
    setRangeparam4(newValue);
  };
  const handleChangep5 = (event, newValue) => {
    setUltimoparam(5)
    setRangeparam5(newValue);
  };
    const handleChangep6 = (event, newValue) => {
    setUltimoparam(6)
    setRangeparam6(newValue);
  };    const handleChangep7 = (event, newValue) => {
    setUltimoparam(7)
    setRangeparam7(newValue);
  };

    useEffect(() => {
        setResultado( prevResultado =>
      {
          if(prevResultado == [])
          {
            return []
          }
          
          return prevResultado.sort(function(a, b){return a.parametros[ultimoparam] - b.parametros[ultimoparam]});
          //return [];
      })
    },[rangeparam0,rangeparam1,rangeparam2,rangeparam3,rangeparam4,rangeparam5,rangeparam6,rangeparam7]) 

    function FazPesquisa()
    {
        const PlaylistID = seletorDePlaylist.current.value;
        
        axios.get('http://localhost:8080/api/MusicasPlaylist', {params: { PlaylistID: PlaylistID}})
  .then(function (response) {
    //setResultado(response.data)
    
     setResultado(response.data)
  })
  console.log(resultado);

      
}

function destroiPlaylist()
{
        const PlaylistID = seletorDePlaylist.current.value;
        
        axios.get('http://localhost:8080/api/deletaPlaylist', {params: { PlaylistID: PlaylistID}})
  .then(function (response) {
  })
  window.location.reload()
}
function pegaplaylists()
{
    axios.get('http://localhost:8080/api/pegaPlaylistsDoCliente', {params: {}})
  .then(function (response) {
    setPlaylistsDoCliente(response.data)
  })
}
function tiramusica (id_e_uri)
{
  const id_da_musica = id_e_uri[0];
  const id_da_playlist = seletorDePlaylist.current.value;

  axios.get('http://localhost:8080/api/removeMusicaDaPlaylist', {params: {MusicaID: id_da_musica, PlaylistID: id_da_playlist }})
  .then(function (response) {
  })
  window.location.reload()
}
    return (
    <>
    <S.Tudo>
    <S.CorpoPagina>
    <S.Header>
        <S.TituloTuring>Turing</S.TituloTuring><S.TituloFy>fy</S.TituloFy>
        <S.Slogan>O seu super gerenciador musical</S.Slogan>
    </S.Header>
    <S.divGrande>
    <div>
          <S.TextoPlaylists>Playlists:</S.TextoPlaylists>
          <S.divSelecioneAPlaylist>
              <S.TextoSelecioneAPlaylist>Selecione a playlist:</S.TextoSelecioneAPlaylist>
          <select ref = {seletorDePlaylist} name="playlists">
              {playlistsDoCliente.map(playlist => {
                  return (
                <option  value={playlist.id}>   {playlist.nome} </option>);
              })}
          </select>
          </S.divSelecioneAPlaylist> 
              <S.divPesquisar>
                <S.BotaoPesquisar onClick={FazPesquisa}> Ver Playlist  </S.BotaoPesquisar>
                
              </S.divPesquisar>

              <S.divPesquisar>
                <S.BotaoPesquisar onClick={destroiPlaylist} > Destruir Playlist </S.BotaoPesquisar>
                
              </S.divPesquisar>  
    </div>
      <S.divCorpoParametros>
        <S.TextoParametros>Dancabilidade:</S.TextoParametros>
        <Slider
          getAriaLabel={() => 'Temperature range'}
          value={rangeparam0}
          onChange={handleChangep0}
          valueLabelDisplay="auto"
          getAriaValueText={valuetext}
        />
        <S.TextoParametros>Energia:</S.TextoParametros>
        <Slider
          getAriaLabel={() => 'Temperature range'}
          value={rangeparam1}
          onChange={handleChangep1}
          valueLabelDisplay="auto"
          getAriaValueText={valuetext}
        />
        <S.TextoParametros>Tempo:</S.TextoParametros>
              <Slider
          getAriaLabel={() => 'Temperature range'}
          value={rangeparam2}
          onChange={handleChangep2}
          valueLabelDisplay="auto"
          getAriaValueText={valuetext}
          min={0}
          max={200}
        />
        <S.TextoParametros>Intesidade:</S.TextoParametros>
              <Slider
              
          getAriaLabel={() => 'Temperature range'}
          value={rangeparam3}
          onChange={handleChangep3}
          valueLabelDisplay="auto"
          getAriaValueText={valuetext}
          min={-50}
          max={50}
        />
      </S.divCorpoParametros>
      <S.divCorpoParametros>
        <S.TextoParametros>Fala:</S.TextoParametros>
              <Slider
          getAriaLabel={() => 'Temperature range'}
          value={rangeparam4}
          onChange={handleChangep4}
          valueLabelDisplay="auto"
          getAriaValueText={valuetext}
        />
        <S.TextoParametros>Instrumentalidade:</S.TextoParametros>
        <Slider
          getAriaLabel={() => 'Temperature range'}
          value={rangeparam5}
          onChange={handleChangep5}
          valueLabelDisplay="auto"
          getAriaValueText={valuetext}
        />
        <S.TextoParametros>Acusticidade:</S.TextoParametros>
                    <Slider
          getAriaLabel={() => 'Temperature range'}
          value={rangeparam6}
          onChange={handleChangep6}
          valueLabelDisplay="auto"
          getAriaValueText={valuetext}
        />
        <S.TextoParametros>"liveness":</S.TextoParametros>
                    <Slider
          getAriaLabel={() => 'Temperature range'}
          value={rangeparam7}
          onChange={handleChangep7}
          valueLabelDisplay="auto"
          getAriaValueText={valuetext}
        />
    </S.divCorpoParametros>
    </S.divGrande>
        <table>
            <S.TabelaHead>
            <S.ColunaTabela>
            <S.itemHead>Nome</S.itemHead>
            <S.itemHead>Dancabilidade</S.itemHead>
            <S.itemHead>Energia</S.itemHead>
            <S.itemHead>Tempo</S.itemHead>
            <S.itemHead>Intensidade</S.itemHead>
            <S.itemHead>Fala</S.itemHead> 
            <S.itemHead>Instrumentalidade</S.itemHead>
            <S.itemHead>Acusticidade</S.itemHead>
            <S.itemHead>Liveness</S.itemHead>
            <S.itemHead>Preview</S.itemHead>
            <S.itemHead>Remover</S.itemHead>

            </S.ColunaTabela>
            </S.TabelaHead>
            <ListaDeMusica  range={[
          rangeparam0,
          rangeparam1,
          rangeparam2,
          rangeparam3,
          rangeparam4,
          rangeparam5,
          rangeparam6,
          rangeparam7,]} musicas={resultado} addmusica={tiramusica} />
        </table>
    </S.CorpoPagina>
    </S.Tudo>
        </>
    );
}