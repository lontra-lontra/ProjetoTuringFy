import React,{useState,useRef,useEffect} from 'react'
import ListaDeMusica from './ListaDeMusica';
import ListaDePlaylists from './ListaDePlaylists';
import axios from 'axios';
import Box from '@mui/material/Box';
import Slider from '@mui/material/Slider';
import * as S from "./styles";


const marksTimeS = [
  {
    value: 3,
    label: '3/4',
  },
  {
    value: 4,
    label: '4/4',
  },
  {
    value: 5,
    label: '5/4',
  },
  {
    value: 6,
    label: '6/8',
  },
];


const marksP = [
  {
    value: -1,
    label: '',
  },
  {
    value: 0,
    label: 'C',
  },
  {
    value: 1,
    label: 'C#',
  },
  {
    value: 2,
    label: 'D',
  },
    {
    value: 3,
    label: 'D#',
  },
  {
    value: 4,
    label: 'E',
  },
  {
    value: 5,
    label: 'F',
  },
  {
    value: 6,
    label: 'F#',
  },
    {
    value: 7,
    label: 'G',
  },
  {
    value: 8,
    label: 'G#',
  },
  {
    value: 9,
    label: 'A',
  },
  {
    value: 10,
    label: 'A#',
  },
    {
    value: 11,
    label: 'B',
  },
];

const marksMode =
[
  {
    value: 1,
    label: 'maior',
  },
  {
    value: '0',
    label: 'menor',
  }
];

function valuetext(value) {
  return `${value} OI`;
}
function valuetextTimeS(value) {
  if(value == 3)
    return "3/4"
  if(value == 4)
    return "4/4"
  if(value == 5)
    return "5/4"
  if(value == 6)
    return "6/8"
}
export default function Pesquisa()
{
    React.useEffect(() => {
    pegaplaylists()
  }, []);
    const [descricao,setDescricao] = React.useState("");
    const [viziblidadeDaImagemDoAlbum, setVizibilidadeDaImagemDoAlbum] = React.useState("hidden");
    const [urlDaImagemDoAlbum, seturlDaImagemDoAlbum] = React.useState("");
    const [rangeparam0, setRangeparam0] = React.useState([0, 100]);
    const [rangeparam1, setRangeparam1] = React.useState([0, 100]);
    const [rangeparam2, setRangeparam2] = React.useState([0, 200]);
    const [rangeparam3, setRangeparam3] = React.useState([-50, 50]);
    const [rangeparam4, setRangeparam4] = React.useState([0, 100]);
    const [rangeparam5, setRangeparam5] = React.useState([0, 100]);
    const [rangeparam6, setRangeparam6] = React.useState([0, 100]);
    const [rangeparam7, setRangeparam7] = React.useState([0, 100]);
    const [rangeparam8, setRangeparam8] = React.useState([-1, 11]);
    const [rangeparam9, setRangeparam9] = React.useState([0, 1]);
    const [rangeparam10, setRangeparam10] = React.useState([3, 6]);

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
  };    
  const handleChangep7 = (event, newValue) => {
    setUltimoparam(7)
    setRangeparam7(newValue);
  };

   const handleChangep8 = (event, newValue) => {
    setUltimoparam(8)
    setRangeparam8(newValue);
  };
   const handleChangep9 = (event, newValue) => {
    setUltimoparam(9)
    setRangeparam9(newValue);
  };
   const handleChangep10 = (event, newValue) => {
    setUltimoparam(10)
    setRangeparam10(newValue);
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
    setVizibilidadeDaImagemDoAlbum ("hidden");
  })
         pegaplaylists()
    }

    function FazPesquisaAlbum()
    {
        const palavraPesquisada = barraDePesquisa.current.value;
        console.log(palavraPesquisada)
        
        axios.get('http://localhost:8080/api/pesquisaMusicasDoAlbum', {params: { pesquisa: palavraPesquisada}})
  .then(function (response) {
    setResultado(response.data)
  })
        axios.get('http://localhost:8080/api/pesquisaImagemDoAlbum', {params: { pesquisa: palavraPesquisada}})
  .then(function (response) {
    seturlDaImagemDoAlbum(response.data)
    setVizibilidadeDaImagemDoAlbum ("visible");
  })
         pegaplaylists()
    }

    function FazPesquisaPlaylist()
    {
        const palavraPesquisada = barraDePesquisa.current.value;
        console.log(palavraPesquisada)
        axios.get('http://localhost:8080/api/pesquisaMusicasDaPlaylist', {params: { pesquisa: palavraPesquisada}})
  .then(function (response) {
    setResultado(response.data)
  })
         pegaplaylists()
         seturlDaImagemDoAlbum (urlDaImagemDoAlbum);
         setVizibilidadeDaImagemDoAlbum ("hidden");
    }

    function FazPesquisaArtista()
    {
        // solucao esperta 
        const palavraPesquisada = "this is " + barraDePesquisa.current.value;
        console.log(palavraPesquisada)
        
        axios.get('http://localhost:8080/api/pesquisaMusicasDaPlaylist', {params: { pesquisa: palavraPesquisada}})
  .then(function (response) {
    setResultado(response.data)
  })
         pegaplaylists()
        setVizibilidadeDaImagemDoAlbum ("hidden");
    }
function funcaoDoHover(descricaoDaMusica)
{
  setDescricao(descricaoDaMusica);
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
    <div>
    <a href="default.asp"><img src={urlDaImagemDoAlbum} alt="album" style={{visibility: viziblidadeDaImagemDoAlbum,width: "200px", height: "200px"}}></img></a>
    {descricao}
    </div>

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
                <S.BotaoPesquisar onClick={FazPesquisa}> Pesquisar  </S.BotaoPesquisar>
                <S.BotaoPesquisar onClick={FazPesquisaAlbum}> Album </S.BotaoPesquisar>
                <S.BotaoPesquisar onClick={FazPesquisaPlaylist}> Playlist </S.BotaoPesquisar>
                <S.BotaoPesquisar onClick={FazPesquisaArtista}> Artista </S.BotaoPesquisar>
                <S.inputPesquisar ref={barraDePesquisa} type="text"/>
              </S.divPesquisar>
              <S.divPesquisar>
              </S.divPesquisar>

              <S.divPesquisar>
                <S.BotaoPesquisar style={{visibility: "hidden"}} > ________ </S.BotaoPesquisar>
                <S.BotaoPesquisar onClick={criaplaylist}> Criar playlist </S.BotaoPesquisar>
                <S.BotaoPesquisar style={{visibility: "hidden"}} > ________ </S.BotaoPesquisar>
                <S.inputPesquisar ref={barraDoNomeDaPlaylist} type="text"/>
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
          max={500}
        />

        <S.TextoParametros>Intesidade:</S.TextoParametros>
              <Slider
              
          getAriaLabel={() => 'Temperature range'}
          value={rangeparam3}
          onChange={handleChangep3}
          valueLabelDisplay="off"
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
      </S.divCorpoParametros>
      
      <S.divCorpoParametros>


        <S.TextoParametros>Acusticidade:</S.TextoParametros>
                    <Slider
          getAriaLabel={() => 'Temperature range'}
          value={rangeparam6}
          onChange={handleChangep6}
          valueLabelDisplay="auto"
          getAriaValueText={valuetext}
        />
        <S.TextoParametros>"Liveness":</S.TextoParametros>
          
          <Slider
          getAriaLabel={() => 'Temperature range'}
          value={rangeparam7}
          onChange={handleChangep7}
          valueLabelDisplay="auto"
          getAriaValueText={valuetext}
        />

        <S.TextoParametros>Tonalidade:</S.TextoParametros>
        <Slider
          getAriaLabel={() => 'Temperature range'}
          value={rangeparam8}
          onChange={handleChangep8}
          valueLabelDisplay="off"
          min={-1}
          max={11}
          marks={marksP}
          getAriaValueText={null}
        />
        <Slider
          getAriaLabel={() => 'Temperature range'}
          value={rangeparam9}
          onChange={handleChangep9}
          valueLabelDisplay="off"
          min={0}
          max={1}
          marks={marksMode}
          getAriaValueText={null}
        />
        <S.TextoParametros>Batida:</S.TextoParametros>
        <Slider
          getAriaLabel={() => 'Temperature range'}
          value={rangeparam10}
          onChange={handleChangep10}
          min={3}
          max={6}
          marks={marksTimeS}
          valueLabelDisplay="off"
          getAriaValueText={null}
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
            <S.itemHead>Adicionar</S.itemHead>

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
          rangeparam7,
          rangeparam8,
          rangeparam9,
          rangeparam10,
          ]} musicas={resultado} addmusica={addmusica} funcaoDoHover={funcaoDoHover}/>
        </table>
    </S.CorpoPagina>
    </S.Tudo>
        </>
    );
}