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

export default function Pesquisa()
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
            console.log("oi")
            return []
          }
          
          return prevResultado.sort(function(a, b){return a.parametros[ultimoparam] - b.parametros[ultimoparam]});
          //return [];
      })
    },[rangeparam0,rangeparam1,rangeparam2,rangeparam3,rangeparam4,rangeparam5,rangeparam6,rangeparam7]) 

    function FazPesquisa()
    {
        const palavraPesquisada = barraDePesquisa.current.value;
        console.log(palavraPesquisada)
        
        axios.get('http://localhost:8080/api/pesquisa', {params: { pesquisa: palavraPesquisada}})
  .then(function (response) {
    setResultado(response.data)
  })
         pegaplaylists()
    }
    function criaplaylist (id)
{
  console.log(id);
  const nomeDaPlaylist = barraDoNomeDaPlaylist.current.value;
  axios.get('http://localhost:8080/api/criaPlaylist', {params: {nome: nomeDaPlaylist}})
  .then(function (response) {
window.location.reload()
    //console.log(response.data[0]);
  })

}


function pegaplaylists()
{
    axios.get('http://localhost:8080/api/pegaPlaylistsDoCliente', {params: {}})
  .then(function (response) {
    console.log(response.data);
    setPlaylistsDoCliente(response.data)
  })
}
function addmusica (id_e_uri)
{
  const id_da_musica = id_e_uri[0];
  const id_da_playlist = seletorDePlaylist.current.value;
  console.log("musica na playlist:")
  console.log(id_da_playlist);
  console.log(id_da_musica);
  axios.get('http://localhost:8080/api/adicionaMusicaNaPlaylist', {params: {MusicaID: id_da_musica, PlaylistID: id_da_playlist }})
  .then(function (response) {
    console.log(response.data);
  })
}
    return (
    <>
    <S.Tudo>
    <S.CorpoPagina>
    <S.Header>
        <S.TituloTuring>Turing</S.TituloTuring><S.TituloFy>fy</S.TituloFy>
        <S.Slogan>O seu super gerenciador musical</S.Slogan>
    </S.Header>
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
      <S.divPesquisaEParametros>
        <div>
        <S.divPesquisar>
          <S.BotaoPesquisar onClick={FazPesquisa}> Pesquisar  </S.BotaoPesquisar>
          <S.inputPesquisar ref={barraDePesquisa} type="text"/>
        </S.divPesquisar>

        <S.divPesquisar>
          <S.BotaoPesquisar onClick={criaplaylist}> Criar playlist </S.BotaoPesquisar>
          <S.inputPesquisar ref={barraDoNomeDaPlaylist} type="text"/>
        </S.divPesquisar>  

        <ListaDeMusica  range={[
          rangeparam0,
          rangeparam1,
          rangeparam2,
          rangeparam3,
          rangeparam4,
          rangeparam5,
          rangeparam6,
          rangeparam7,]} musicas={resultado} addmusica={addmusica} />
         </div>
        
    <S.divCorpoParametros>
    <Box sx={{ width: 300 }}>
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
    </Box>
    </S.divCorpoParametros>
    </S.divPesquisaEParametros>
    </S.CorpoPagina>
    </S.Tudo>
        </>
    );
}


      /*console.log(resultado);
      const novoR = resultado;
      const tamanho = resultado.size;
      console.log("eu sou uma funcao inutil ")
      for (let i = 0; i < tamanho; i++)
      {
            //novoR[i].respeitaParametro0 = (value[0] <= resultado[i].parametros[0]*100) &&(resultado[i].parametros[0]*100 <= value[1]);
            novoR[i].respeitaParametro0 = {};
            novoR[i].respeitaParametro0 = true;
      }
      novoR.sort(function(a, b){return a.parametros[0] - b.parametros[0]})
      
      const n = novoR;
      this.setResultado(n)
      setx(n);








          function reacao (value,param)
    {
      setResultado( prevResultado =>
      {
       // const tamanho = resultado.size;
      for (let i = 0; i < tamanho; i++)
      {
            //novoR[i].respeitaParametro0 = (value[0] <= resultado[i].parametros[0]*100) &&(resultado[i].parametros[0]*100 <= value[1]);
            prevResultado[i].respeitaParametro0 = {};
            prevResultado[i].respeitaParametro0 = true;
      }
        return [prevResultado[0]];
        //return prevResultado.sort(function(a, b){return a.parametros[0] - b.parametros[0]});
    })
  }
      */