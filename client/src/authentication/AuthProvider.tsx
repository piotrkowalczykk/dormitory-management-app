import { createContext, useContext, useState, useEffect} from "react";

const AuthContext = createContext(null);

export const AuthProvider = ({children}) => {
    const [token, setToken] = useState(() => localStorage.getItem("token"));
    const [userDetails, setUserDetails] = useState(() => {
        const savedDetails = localStorage.getItem("userDetails");
        return savedDetails ? JSON.parse(savedDetails) : {
            id: "",
            email: "",
            firstName: "",
            lastName: "",
            gender: "",
            dateOfBirth: "",
            academyName: "",
            roles: [],
        };
    });

    const isAuthenticated = !!token;

    const login = (token, userData) => {
        setToken(token);
        localStorage.setItem("token", token);

        setUserDetails(userData);
        localStorage.setItem("userDetails", JSON.stringify(userData));
    };


    const logout = () => {
        setToken(null);
        setUserDetails({
            id: "",
            email: "",
            firstName: "",
            lastName: "",
            gender: "",
            dateOfBirth: "",
            academyName: "",
            roles: [],
        });

        localStorage.removeItem("token");
        localStorage.removeItem("userDetails");
    };


    if (process.env.NODE_ENV === 'development') {
        window.logout = logout;
    }

    return (
        <AuthContext.Provider value={{ token, login, logout, isAuthenticated, userDetails}}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    return useContext(AuthContext);
};
