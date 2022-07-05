import React from 'react';
import axios from 'axios';
import { render } from '@testing-library/react';

export default class Component extends React.Component {
    state = {
        musicas : [], 
    };

    componentDidMount ()
    {
        axios.get('http://localhost:8080/api/pesquisa', { params: { pesquisa: "batata" } } ).then (res => {
            console.log()
            this.setState({musicas: res.data});
        });
    }

    render()
    {
        return(
            <ul>
                {this.state.musicas.map(musica => <li>{musica.nome}</li>)} 
            </ul>
        );
    }
}