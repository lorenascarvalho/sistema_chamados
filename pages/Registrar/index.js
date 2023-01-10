import { useState, useEffect } from "react"
import { useAuthentication } from "../../contexts/useAuthentication";
import styles from "./registrar.module.css";

const Registrar = () => {

    const [displayName, setDisplayName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmepassword, setConfirmePassword] = useState("");
    const [error, setError] = useState("");

    const {createUser, error: authError, loading} = useAuthentication();

    const handleSubmit = async (e) => {
        e.preventDefault()

        setError("")

        const user = {
            displayName,
            email,
            password
        }

        if(password !== confirmepassword){
            setError("Os valores não são iguais")
            return
        }

        const res = await createUser(user)

        console.log(res);

      
    };

    

    useEffect(() => {
        if(authError){
            setError(authError);
        }
    }, [authError]);

    return (
        <div className={styles.register}>
            <h1>REGISTRE-SE</h1>

            <form onSubmit={handleSubmit}>
                <label>
                    <span>Nome:</span>
                    <input type="text" name="displayName" placeholder="Nome do usuário" value={displayName} onChange={(e) => setDisplayName(e.target.value)} required></input>
                </label>
                <label>
                    <span>Email:</span>
                    <input type="email" name="email" placeholder="Email do usuário" value={email} onChange={(e) => setEmail(e.target.value)} required></input>
                </label>
                <label>
                    <span>Senha:</span>
                    <input type="password" name="password" placeholder="Insira sua senha" value={password} onChange={(e) => setPassword(e.target.value)} required></input>
                </label>
                <label>
                    <span>Confirme sua Senha:</span>
                    <input type="password" name="confirmepassword" placeholder="Confirme sua senha" value={confirmepassword} onChange={(e) => setConfirmePassword(e.target.value)} required></input>
                </label>
                <button className="btn">Cadastrar</button>
                {error && <p>{error}</p>}
            </form>

        </div>


    )
}

export default Registrar;