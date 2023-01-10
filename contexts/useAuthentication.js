import{ db } from "../services/firebaseConnection"

import {
    getAuth, 
    createUserWithEmailAndPassword, 
    signInWithEmailAndPassword, 
    updateProfile,
    signOut
} from 'firebase/auth'

import { useState, useEffect } from 'react'

export const useAuthentication = () => {
    const [error, setError] = useState(null)
    //const [loading, setLoading] = useState(null)

    //cleanup

    const [cancelled, setCancelled] = useState(false)

    const auth = getAuth()

    function checkIfIsCancelled(){
        if (cancelled){
            return;
        }
    }

    const createUser = async (data) => {
        checkIfIsCancelled();

        //setLoading(true);
        setError(null);
        try {
            const {user} = await createUserWithEmailAndPassword(
                auth,
                data.email,
                data.password
            )

            await updateProfile(user, {
                displayName: data.displayName
            })

            return user

        } catch (error) {
            console.log(error.message)
            console.log(typeof error.message)

            let systemErrorMessage;

            if(error.message.includes("Password")){
                systemErrorMessage = "A senha precisa conter ao menos seis caracteres.";
            } else if(error.message.includes("email-already")){
                systemErrorMessage = "Email já cadastrado.";
            } else if(error.message.includes("invalid-email")){
                systemErrorMessage = "Email inválido";
            }else {
                systemErrorMessage = "Ocorreu um erro, por favor tente mais tarde!";
            }
            setError(systemErrorMessage);
        }

        //setLoading(false) //acabou a função, acabou o loading
    }


    //logout
    const logout = () => {
        checkIfIsCancelled();
        signOut(auth);
    }

    //login - página de login
    const login = async(data) => {
        checkIfIsCancelled();
        setError(false);

        try {
            await signInWithEmailAndPassword(auth, data.email, data.password)
        } catch (error) {
            let systemErrorMessage;

            if(error.message.includes("user-not-found")){
                systemErrorMessage = "Usuário não encontrado.";
            } else if(error.message.includes("wrong-password")){
                systemErrorMessage = "Senha incorreta";
            } else{
                systemErrorMessage = "Ocorreu um erro, por favor tente mais tarde!";
            }

            setError(systemErrorMessage);
        }
    }

    useEffect(() => {
        return () => setCancelled(true);

    }, []);

    return{
        auth,
        createUser,
        error,
        //loading
        logout,
        login,
    }

}