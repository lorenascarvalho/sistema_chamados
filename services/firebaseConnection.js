// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
//import { getAnalytics } from "firebase/analytics";
import {getAuth} from "firebase/auth";

// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyD8S6wt1W_BcBY1oT6WHk__MgySYyFL3IA",
  authDomain: "sistemachamados-b26e6.firebaseapp.com",
  projectId: "sistemachamados-b26e6",
  storageBucket: "sistemachamados-b26e6.appspot.com",
  messagingSenderId: "971283491157",
  appId: "1:971283491157:web:5998835db8869a9b9967dc",
  measurementId: "G-3SC87LMGC0"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
//const analytics = getAnalytics(app);
export const auth = getAuth(app);