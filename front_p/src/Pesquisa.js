import React,{useState,useRef,useEffect} from 'react'

import ListaDeMusica from './ListaDeMusica'
import axios from 'axios';
import RangeSlider from './component/multiRangeSlider/RangeSlider';
import Box from '@mui/material/Box';
import Slider from '@mui/material/Slider';
function nada ()
{

}
function valuetext(value) {
  return `${value}°C`;
}
export default function Pesquisa()
{
    const [value, setValue] = React.useState([20, 37]);
    const [x,setx] = useState([])
    const [resultado,setResultado] = useState([])
    const [ranges,setRanges] = useState([0,100])


  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
    const barraDePesquisa = useRef()
    useEffect(() => {
        setResultado( prevResultado =>
      {
          if(prevResultado == [])
          {
            console.log("oi")
            return []
          }
          
          return prevResultado.sort(function(a, b){return a.parametros[0] - b.parametros[0]});
          //return [];
      })
    },[value]) 

    function FazPesquisa()
    {
        const palvaraPesquisada = barraDePesquisa.current.value;
        console.log(palvaraPesquisada)
        
        axios.get('http://localhost:8080/api/pesquisa', {params: { pesquisa: palvaraPesquisada}})
  .then(function (response) {
    setResultado(response.data)
  })
    }
    
    return (
        <>
        <button onClick={FazPesquisa}> oi </button>
        <input ref={barraDePesquisa} type="text"/>
        <ListaDeMusica  range={value} musicas={resultado} />
         
        
        

    <Box sx={{ width: 300 }}>
      <Slider
        getAriaLabel={() => 'Temperature range'}
        value={value}
        onChange={handleChange}
        valueLabelDisplay="auto"
        getAriaValueText={valuetext}
      />
    </Box>
        teste:----------------------------------------
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