import { Link, useNavigate } from 'react-router-dom'
import { useEffect, useRef, useState } from 'react';
import { useUserAuth } from '../../contexts/auth';
import styles from "./Login.module.css"
import "../../css/style.css";

export default function Login() {

  const email = useRef();
  const senha = useRef();

  const [error, setError] = useState();

  const { login } = useUserAuth();

  const navigate = useNavigate();

  useEffect(() => {
    const user = {
      email: localStorage.getItem('user-email'),
      senha: localStorage.getItem('user-senha'),
    };
    if (user.email && user.senha) {
      email.current.value = user.email;
      senha.current.value = user.senha;
      fazerLogin();
    }
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    fazerLogin();
    localStorage.setItem('user-email', email.current.value);
    localStorage.setItem('user-senha', senha.current.value);
  }

  const fazerLogin = async () => {
    try {
      await login(email.current.value, senha.current.value);
      navigate('/chamados');

    } catch (error) {
      switch (error.code) {
        case 'auth/user-not-found': setError('Usuário não encontrado!');
          break;

        case 'auth/wrong-password': setError('Senha incorreta!');
          break;

        case 'auth/invalid-email': setError('E-mail inválido!');
          break;

        default: setError('Ocorreu um erro desconhecido!');
          break;
      }
    }
  }

  return (
    <div className={styles.login}>
      <div className={styles.brand}>Sistema de <span>Chamados</span></div>


      <h4>Faça o login para poder utilizar o sistema</h4>

      {/* <div className="login-area">
        <img src={logo} alt="Logo do Sistema" />
      </div>
 */}
      <form>
        <div className='containerLogin'>
          <div>
            <label>
              <span>E-mail:</span>
              <input type="text" placeholder="email@email.com" ref={email} />
            </label></div>
          <div>
            <label>

              <span>Senha:</span>
              <input type="password" placeholder="*****" ref={senha} />
            </label>
          </div>
          <button className='btn btn-success mt-4 mb-5' type="submit" onClick={handleSubmit}>Acessar</button>
          <p style={{ display: error ? "block" : "none" }}>{error}</p>
        </div>
      </form>

      <Link to="/cadastro" className='btn btn-outline-secondary'>Criar uma conta...</Link>

    </div>

  );
}
