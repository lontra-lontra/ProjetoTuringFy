import React,{useState,useRef,useEffect} from 'react'

import ListaDeMusica from './ListaDeMusica'
import axios from 'axios';
import RangeSlider from './component/multiRangeSlider/RangeSlider';
import Box from '@mui/material/Box';
import Slider from '@mui/material/Slider';
function addmusica (id)
{
  console.log(id);
  axios.get('http://localhost:8080/api/criaPlaylist', {params: {nome: "teste"}})
  .then(function (response) {
    //console.log(response.data[0]);
  })
}
function valuetext(value) {
  return `${value}`;
}

export default function Pesquisa()
{
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
    const barraDePesquisa = useRef()
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
    }
    
    return (
        <>
        <button onClick={FazPesquisa}> pesquisar  </button>
        <input ref={barraDePesquisa} type="text"/>
        <ListaDeMusica  range={[
          rangeparam0,
          rangeparam1,
          rangeparam2,
          rangeparam3,
          rangeparam4,
          rangeparam5,
          rangeparam6,
          rangeparam7,]} musicas={resultado} addmusica={addmusica} />
         
        
        
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