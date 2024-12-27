import { Navigate } from "react-router-dom";
import { useAuth } from "./AuthProvider";

export const PublicRoute = ({children}) =>{
    const {isAuthenticated} = useAuth();
    console.log(isAuthenticated);

    if (isAuthenticated){
        return <Navigate to="/" replace />;
    }

    return children;
}
