import React,{useState,useRef} from 'react'
import ListaDeMusica from './ListaDeMusica'
import axios from 'axios';
import MultiRangeSlider from "./component/multiRangeSlider/MultiRangeSlider";
import RangeSlider from './component/multiRangeSlider/RangeSlider';
export default function Pesquisa()
//parametro 0: energia
{
  	//incluindo dançável, energia, andamento (tempo), força (loudness), fala (speechiness), instrumental, ao vivo, acústica
    const [criteriosRange,setCriteriosRange] = useState([[0,100],[0,100],[0,100],[0,100],[0,100],[0,100],[0,100],[0,100]])

    const [criterio0Range,setcriterio0Range] = useState(0,100)
    const [criterio1Range,setcriterio1Range] = useState(0,100)
    const [criterio2Range,setcriterio2Range] = useState(0,100)
    const [criterio3Range,setcriterio3Range] = useState(0,100)
    const [criterio4Range,setcriterio4Range] = useState(0,100)
    const [criterio5Range,setcriterio5Range] = useState(0,100)
    const [criterio6Range,setcriterio6Range] = useState(0,100)
    
    const [energiaRange,setEnergiaRange] = useState([0,100])
    const [andamentoRange,setAndamentoRange] = useState(0,100)
    const [forcaRange,setForcaRange] = useState(0,100)
    const [falaRange,setFalaRange] = useState(0,100)
    const [instrumentalRange,setInstrumentalRange] = useState(0,100)
    const [aovivoRange,setAovivoRange] = useState(0,100)
    const [acusticoRange,setAcusticoRange] = useState(0,100)

    //const [param,setParam] = useState(0)

    const [resultado,setResultado] = useState([])
    //const resultado = [{id: 1,nome: 'a'},{id: 2,nome: 'b'}];
    const barraDePesquisa = useRef()
    const slider = useRef()
    function reacao(value,param)
    {
      var criteriosRangeAtualizados = criteriosRange;
      criteriosRangeAtualizados[param] = value;
      setCriteriosRange(criteriosRangeAtualizados);

      console.log(criteriosRange);
      const novoR = resultado;
      const tamanho = resultado.length;
      console.log(param);
      for (let i = 0; i < tamanho; i++)
      {
            novoR[i].ParametroOk[0] = (criteriosRange[0][0] <= resultado[i].energia*100) &&(resultado[i].energia*100 <= criteriosRange[0][1]);
      }
        novoR.sort(function(a, b){return a.parametros[param] - b.parametros[param]});
     setResultado(novoR);

    }
    function FazPesquisa()
    {
        const palvaraPesquisada = barraDePesquisa.current.value;
        console.log(palvaraPesquisada)
        
        axios.get('http://localhost:8080/api/pesquisa', {params: { pesquisa: palvaraPesquisada}})
  .then(function (response) {

    console.log(response.data[0]);
    const tamanho = response.data.length;
    //response.data
    const resultadoDaPesquisa = response.data;
    for(let i = 0;i < tamanho;i++)
    {
      resultadoDaPesquisa[i].ParametroOk = {};
      resultadoDaPesquisa[i].ParametroOk = [true,true,true,true,true,true,true,true];
    }
    setResultado(resultadoDaPesquisa )
  })
    }
    return (
        <>
        <button onClick={FazPesquisa}> oi </button>
        <input ref={barraDePesquisa} type="text"/>
        <ListaDeMusica musicas={resultado}/>
        <div > Dancabilidade:
        <RangeSlider reacao={reacao} tipo = {0}></RangeSlider>
        </div>
          <div > Energia:
        <RangeSlider reacao={reacao} tipo = {1}></RangeSlider>
        </div>
        <div > andamento:
        <RangeSlider reacao={reacao} tipo = {2}></RangeSlider>
        </div>
          <div > forca:
        <RangeSlider reacao={reacao} tipo = {3}></RangeSlider>
        </div>
        </>
    )
}