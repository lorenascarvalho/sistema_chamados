import { Link, useNavigate } from 'react-router-dom';
import { useRef, useState } from 'react';
import { useUserAuth } from '../../contexts/auth';
import api from '../../services/api';
import styles from './NovoUsuario.module.css';

export default function NovoUsuario() {

  const nome = useRef();
  const email = useRef();
  const senha = useRef();

  const [error, setError] = useState();

  const { signUp } = useUserAuth();

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!nome.current.value) {
      setError('Nome inválido!');
      return;
    }

    makeSignUp();
  }

  const makeSignUp = async () => {
    let response;

    try {
      response = await signUp(email.current.value, senha.current.value);

      if (response != null) {
        await signUpPersistence(response.user);
        navigate('/chamados');
      }

    } catch (error) {
      switch (error.code) {
        case 'auth/invalid-email': setError('E-mail inválido!'); break;
        case 'auth/weak-password': setError('Senha inválida!'); break;
        case 'auth/email-already-in-use': setError('E-mail já está em uso!'); break;
        default: setError('Ocorreu um erro desconhecido!'); break;
      }
    }
  }

  async function signUpPersistence(user) {
    await api.post('/usuarios', {
      uid: user.uid,
      email: user.email,
      nome: nome.current.value,
    })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <div className={styles.novousuario}>
      <div className={styles.brand}>Sistema de <span>Chamados</span></div>


      <h4>Realize o seu cadastro</h4>

      <form>
        <div className='containerLogin'>
          <div>
            <label>
              <span>Nome:</span>
              <input type="text" placeholder="Seu nome" ref={nome} />
            </label>
          </div>
          <div>
            <label>
              <span>E-mail:</span>
              <input type="text" placeholder="email@email.com" ref={email} />
            </label>
          </div>
          <div>
            <label>
              <span>Senha:</span>
              <input type="password" placeholder="*****" ref={senha} />
            </label>
          </div>
          <button className='btn btn-success mt-4 mb-5' type="submit" onClick={handleSubmit}>Cadastrar</button>
          <p style={{ display: error ? "block" : "none" }}>{error}</p>
        </div>
      </form>

      <Link to="/" className='btn btn-outline-secondary'>Já possui uma conta? Entre aqui!</Link>

    </div>


  );
}