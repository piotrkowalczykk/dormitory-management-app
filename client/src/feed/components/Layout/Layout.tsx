import { ReactNode, useState } from 'react';
import { NavBar } from '../NavBar/NavBar'
import { SideBar } from '../SideBar/SideBar'
import classes from './Layout.module.css'
import { Footer } from '../../../components/Footer/Footer';

export function Layout({children}: {children: ReactNode}){
    
    const [isSideBarVisible, SetIsSideBarVisible] = useState(false);

    const toggleSideBar = () =>{
        SetIsSideBarVisible((prev) => !prev);
    };

    return(
        <div className={classes.container}>
                <NavBar onToggleSideBar={toggleSideBar}/>
                <div className={classes.innerContainer}>
                    <SideBar isVisible={isSideBarVisible}/>
                    {children}
                </div>
                <Footer />
        </div>
    )
}