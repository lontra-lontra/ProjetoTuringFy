import React from "react"
import Musica from "./Musica"
export default function ListaDePlaylists({playlists})
{
    return (
    <div>
        selecione a playlist:
    <select name="cars" id="cars">
    <option value="volvo">Volvo</option>
        {playlists.map(playlist => {
            return (
            <option key={playlist.id} value={playlist.id}>   {playlist.nome} </option>);
        })}
    </select>
    </div>
    )
}