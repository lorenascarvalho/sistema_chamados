import { BrowserRouter } from "react-router-dom";
import AuthProvider from "./contexts/AuthContext";
//import 'react-toastify/dist/ReactToastify.css';
//import { ToastContainer } from 'react-toastify';
import Rotas from "./routes/Rotas";

import { onAuthStateChanged } from "firebase/auth";
import { useState, useEffect } from "react";
import { useAuthentication } from "./contexts/useAuthentication";

function App() {

  const [user, setUser] = useState(undefined);
  const {auth} = useAuthentication();

  const loadingUser = user === undefined;

  useEffect(() => {
    onAuthStateChanged(auth, (user) => {
      setUser(user)
    });
  }, [auth]);

  if(loadingUser){
    return <p>Carregando...</p>;
  }

  return (
    <div>
      <AuthProvider value={{user}}>
        <BrowserRouter>
          <Rotas />
        </BrowserRouter>
      </AuthProvider>
    </div>
  );
} export default App;