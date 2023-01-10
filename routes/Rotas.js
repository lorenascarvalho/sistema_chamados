import "./Rotas.css";

import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";
import Navbar from "../components/Navbar.js";
import AuthProvider from "../contexts/AuthContext";
import Footer from "../components/Footer.js";

import { useAuthentication } from "../contexts/useAuthentication";
import { onAuthStateChanged } from "firebase/auth";
import { useState, useEffect } from "react";
//import user from "../App";

//paginas
import Home from "../pages/Home";
import Sobre from "../pages/Sobre";
import Login from "../pages/Login";
import Registrar from "../pages/Registrar";
import Atendimentos from "../pages/Atendimentos";
import Buscar from "../pages/Atendimentos/Buscar";
import Editar from "../pages/Atendimentos/Editar";
import Novo from "../pages/Atendimentos/Novo";
import PerfilCliente from "../pages/PerfilCliente";
import PerfilProfissional from "../pages/PerfilProfissional";


//import Menu from "../components/Menu";

export default function Rotas() {
  const [user, setUser] = useState(undefined);
  const { auth } = useAuthentication();

  const loadingUser = user === undefined;

  useEffect(() => {
    onAuthStateChanged(auth, (user) => {
      setUser(user)
    });
  }, [auth]);

  if (loadingUser) {
    return <p>Carregando...</p>;
  }

  return (
    <div>
      <AuthProvider value={{ user }}>
        <BrowserRouter>
          <Navbar />
          <div className="container">
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/sobre" element={<Sobre />} />
              <Route path="/registrar" element={!user ? <Registrar /> : <Navigate to="/" />} />
              <Route path="/login" element={!user ? <Login /> : <Navigate to="/" />} />
              <Route path="/chamados" element={user ? <Atendimentos /> : <Navigate to="/login" />} />
              <Route path="/atendimento/buscar" element={user ? <Buscar /> : <Navigate to="/login" />} />
              <Route path="/atendimento/novo" element={user ? <Novo /> : <Navigate to="/login" />} />
              <Route path="/atendimento/editar" element={user ? <Editar /> : <Navigate to="/login" />} />
              <Route path="/clientes" element={user ? <PerfilCliente /> : <Navigate to="/login" />} />
              <Route path="/configuracao" element={user ? <PerfilProfissional /> : <Navigate to="/login" />} />
            </Routes>
          </div>
          <Footer />
        </BrowserRouter>
      </AuthProvider>
    </div>
  )
}