import React from 'react';
import logo from './logo.svg';
import './App.css';
import PaginaInicial from './PaginaInicial';
import { BrowserRouter, BrowserRouter as Router,Route,Routes } from 'react-router-dom';
import PaginaResultados from './PaginaResultados';
import Login from './PaginaLogin';


function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
        <Route path='/' element={<Login/>}/>
        <Route path='/PaginaInicial' element={<PaginaInicial/>}/>
        <Route path='/' element={<PaginaInicial/>}/>
        <Route path="Resultados" element = {<PaginaResultados/>}/>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
