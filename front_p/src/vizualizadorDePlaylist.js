import React,{useState,useRef,useEffect} from 'react'

import ListaDeMusica from './ListaDeMusica'
import ListaDePlaylists from './ListaDePlaylists';
import axios from 'axios';
import RangeSlider from './component/multiRangeSlider/RangeSlider';
import Box from '@mui/material/Box';
import Slider from '@mui/material/Slider';




function valuetext(value) {
  return `${value}`;
}

export default function Pesquisa()
{
    React.useEffect(() => {
    pegaplaylists()
    console.log("ooooooi")
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
            console.log("oi")
            return []
          }
          
          return prevResultado.sort(function(a, b){return a.parametros[ultimoparam] - b.parametros[ultimoparam]});
          //return [];
      })
    },[rangeparam0,rangeparam1,rangeparam2,rangeparam3,rangeparam4,rangeparam5,rangeparam6,rangeparam7]) 

    function FazPesquisa()
    {
        const idDaPlaylist = seletorDePlaylist.current.value;
        

        //--------------------------------------------------TODO
        axios.get('http://localhost:8080/api/pesquis', {params: { pesquisa: palavraPesquisada}})
  .then(function (response) {
    setResultado(response.data)
  })
         pegaplaylists()
    }


function pegaplaylists()
{
    axios.get('http://localhost:8080/api/pegaPlaylistsDoCliente', {params: {}})
  .then(function (response) {
    console.log(response.data);
    setPlaylistsDoCliente(response.data)
  })
}

function tiramusica (uri_da_musica)
{
  const id_da_playlist = seletorDePlaylist.current.value;
  console.log("tira musica da playlist:")
  //--------------------------------------------------TODO
  console.log(id_da_playlist);
  console.log(uri_da_musica);
  axios.get('http://localhost:8080/api/adcionaMusicaNaPlaylist', {params: {MusicaURI: [uri_da_musica], PlaylistID: id_da_playlist }})
  .then(function (response) {
    console.log(response.data);
  })

}
    return (
        <>
       playlists:----------------------------------------
    <div>
        selecione a playlist:
    <select ref = {seletorDePlaylist} name="playlists">
        {playlistsDoCliente.map(playlist => {
            return (
            <option  value={playlist.uri}>   {playlist.nome} </option>);
        })}
    </select>
    </div>
        

        <ListaDeMusica  range={[
          rangeparam0,
          rangeparam1,
          rangeparam2,
          rangeparam3,
          rangeparam4,
          rangeparam5,
          rangeparam6,
          rangeparam7,]} musicas={resultado} addmusica={tiramusica} />
         
        
        
    <Box sx={{ width: 300 }}>
      Dancabilidade:
      <Slider
        getAriaLabel={() => 'Temperature range'}
        value={rangeparam0}
        onChange={handleChangep0}
        valueLabelDisplay="auto"
        getAriaValueText={valuetext}
      />
      Energia:
      <Slider
        getAriaLabel={() => 'Temperature range'}
        value={rangeparam1}
        onChange={handleChangep1}
        valueLabelDisplay="auto"
        getAriaValueText={valuetext}
      />
      Tempo:
            <Slider
        getAriaLabel={() => 'Temperature range'}
        value={rangeparam2}
        onChange={handleChangep2}
        valueLabelDisplay="auto"
        getAriaValueText={valuetext}
        min={0}
        max={200}
      />
      Intesidade:
            <Slider
            
        getAriaLabel={() => 'Temperature range'}
        value={rangeparam3}
        onChange={handleChangep3}
        valueLabelDisplay="auto"
        getAriaValueText={valuetext}
        min={-50}
        max={50}
      />
      Fala:
            <Slider
        getAriaLabel={() => 'Temperature range'}
        value={rangeparam4}
        onChange={handleChangep4}
        valueLabelDisplay="auto"
        getAriaValueText={valuetext}
      />
      Instrumentalidade:
      <Slider
        getAriaLabel={() => 'Temperature range'}
        value={rangeparam5}
        onChange={handleChangep5}
        valueLabelDisplay="auto"
        getAriaValueText={valuetext}
      />
      Acusticidade:
                  <Slider
        getAriaLabel={() => 'Temperature range'}
        value={rangeparam6}
        onChange={handleChangep6}
        valueLabelDisplay="auto"
        getAriaValueText={valuetext}
      />
      "liveness:"
                  <Slider
        getAriaLabel={() => 'Temperature range'}
        value={rangeparam7}
        onChange={handleChangep7}
        valueLabelDisplay="auto"
        getAriaValueText={valuetext}
      />
    </Box>
        
        </>
    );
}
