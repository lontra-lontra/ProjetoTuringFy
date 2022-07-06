import React from 'react';
import logo from './logo.svg';
import './App.css';
import PaginaInicial from './PaginaInicial';
import { BrowserRouter, BrowserRouter as Router,Route,Routes } from 'react-router-dom';
import PaginaResultadosMusica from './PaginaResultadosMusica';
import Login from './PaginaLogin';
import PaginaResultadosArtista from './PaginaResultadosArtista';
import PaginaResultadosAlbum from './PaginaResultadosAlbum';
import PaginaResultadosPlaylist from './PaginaResultadosPlaylist';
import PaginaMusicasArtista from './PaginaMusicasArtista';
import PaginaMusicasPlaylist from './PaginaMusicasPlaylist';
import PaginaMusicasAlbum from './PaginaMusicasAlbum';


function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
        <Route path='/' element={<Login/>}/>
        <Route path='/PaginaInicial' element={<PaginaInicial/>}/>
        <Route path="ResultadosMusica" element = {<PaginaResultadosMusica/>}/>
        <Route path="ResultadosArtista" element = {<PaginaResultadosArtista/>}/>
        <Route path="ResultadosPlaylist" element = {<PaginaResultadosPlaylist/>}/>
        <Route path="ResultadosAlbum" element = {<PaginaResultadosAlbum/>}/>
        <Route path="MusicasAlbum" element = {<PaginaMusicasAlbum/>}/>
        <Route path="MusicasPlaylist" element = {<PaginaMusicasPlaylist/>}/>
        <Route path="MusicasArtista" element = {<PaginaMusicasArtista/>}/>



        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
