import { Navigate } from "react-router-dom";
import { useAuth } from "./AuthProvider";

export const PublicRoute = ({children}) => 
{
    const {isAuthenticated, userDetails} = useAuth();

    if(isAuthenticated && userDetails){
            return <Navigate to="/" replace />;
    }

    return children;
}

export const ProtectedRoute = ({children}) =>
{
    const {isAuthenticated} = useAuth();

    if(!isAuthenticated){
        return <Navigate to="/login" replace />;
    }
    
    return children;
}

