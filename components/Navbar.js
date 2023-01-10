import { NavLink } from "react-router-dom";
import { useAuthentication } from "../contexts/useAuthentication";
import { useAuthValue } from "../contexts/AuthContext";
import styles from "../components/Navbar.module.css";
import "../../src/css/style.css";


export default function Menu() {
  const { logout } = useAuthentication();
  const { user } = useAuthValue();

  return (
    <nav className={styles.navbar}>
      <NavLink className={styles.brand} to="/">
        Sistema de <span>Chamados</span>
      </NavLink>
      <ul className={styles.links_list}>
        <li>
          <NavLink
            to="/"
            className={({ isActive }) => (isActive ? styles.active : "")}
          >
            Home
          </NavLink>
        </li>
        {!user && (
          <>
            <li>
              <NavLink
                to="/login"
                className={({ isActive }) => (isActive ? styles.active : "")}
              >
                Entrar
              </NavLink>
            </li>
            <li>
              <NavLink
                to="/registrar"
                className={({ isActive }) => (isActive ? styles.active : "")}
              >
                Registrar
              </NavLink>
            </li>
          </>
        )}
        {user && (
          <>
            <li>
              <NavLink
                to="/chamados"
                className={({ isActive }) => (isActive ? styles.active : "")}
              >
                Chamados
              </NavLink>
            </li>
            <li>
              <NavLink
                to="/clientes"
                className={({ isActive }) => (isActive ? styles.active : "")}
              >
                Clientes
              </NavLink>
            </li>
            <li>
              <NavLink
                to="/configuracao"
                className={({ isActive }) => (isActive ? styles.active : "")}
              >
                Configuração
              </NavLink>
            </li>
          </>
        )}
        <li>
          <NavLink
            to="/sobre"
            className={({ isActive }) => (isActive ? styles.active : "")}
          >
            Sobre
          </NavLink>
        </li>
        {user && (
          <li>
            <button onClick={logout}>Sair</button>
          </li>
        )}
      </ul>
    </nav>
  );
};
