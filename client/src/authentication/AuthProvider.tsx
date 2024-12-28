import { createContext, useContext, useState } from "react";

const AuthContext = createContext(null);

export const AuthProvider = ({children}) => {
    const [token, setToken] = useState(() => localStorage.getItem("token"));

    const login = (token) => {
        setToken(token);
        localStorage.setItem("token", token);
    }

    const logout = () => {
        setToken(null);
        localStorage.removeItem("token");
    }

    const isAuthenticated = !!token;

    if (process.env.NODE_ENV === 'development') {
        window.logout = logout;
    }

    return(
        <AuthContext.Provider value={{token, login, logout, isAuthenticated}}>
            {children}
        </AuthContext.Provider>
    )
} 

export const useAuth = () => {
    return useContext(AuthContext);
}