import axios from "axios";
import React, { useState } from "react";
import ComponenteResultados from "../ComponenteResultados";
import * as S from "./styles";


function PaginaResultados(){

    return(
        <>
        <S.CorpoPagina>
            <S.Header>
                <S.TituloTuring>Turing</S.TituloTuring><S.TituloFy>fy</S.TituloFy>
                <S.Slogan>O seu super gerenciador musical</S.Slogan>
            </S.Header>
            <S.TextoResultados>Resultados</S.TextoResultados>
            <ComponenteResultados TextoBusca={"love"}></ComponenteResultados>
        </S.CorpoPagina>
        </>
    )
}

export default PaginaResultados;