import * as S from "./styles";
import { useNavigate } from "react-router-dom";

function Login() {

    let navega = useNavigate();
    const navegaPaginaInicial = () =>{
        navega("/PaginaInicial")
    }

    const AbreLogin = () => {
        window.open("Localhost:8080/aut");
      }

    return(
        <>
        <S.Corpo>
            <S.header>
                <S.TituloTuring>Turing</S.TituloTuring><S.TituloFy>fy</S.TituloFy>
                <S.Slogan>O seu super gerenciador musical</S.Slogan>
            </S.header>
            <S.TextoEBotao  id="formLogin" >
                <S.TextoRedireciona>
                    Para poder aproveitar todos os recursos do Turingfy, precisamos que voce se conecte antes na sua conta do Spotify!
                </S.TextoRedireciona>
                <S.BotaoVamos onClick={AbreLogin}>
                    <S.TextoBotaoVamos>Conectar</S.TextoBotaoVamos>
                </S.BotaoVamos>                
                <S.BotaoPaginaInicial onClick={navegaPaginaInicial}>
                    <S.TextoBotaoVamos>Pagina Inicial</S.TextoBotaoVamos>
                </S.BotaoPaginaInicial>
            </S.TextoEBotao>
        </S.Corpo>
        </>
    )
}


export default Login