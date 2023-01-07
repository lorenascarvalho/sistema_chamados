import { NavLink } from 'react-router-dom';
import { useUserAuth } from '../../contexts/auth';
import styles from './Navbar.module.css'
import "../../css/style.css";

export default function Header() {


    return (
        <nav className={styles.navbar}>
            <NavLink className={styles.brand} to="/">
                Sistema de <span>Chamados</span>
            </NavLink>
            <ul className={styles.links_list}>
                <li>
                    <NavLink to="/chamados">
                        Chamados
                    </NavLink>
                </li>


                <li>
                    <NavLink to="/clientes">
                        Clientes
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/perfil">
                        Configurações
                    </NavLink>
                </li>
                
            </ul>
        </nav >
    );
}