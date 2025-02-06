import { ReactNode } from 'react';
import { NavBar } from '../NavBar/NavBar'
import { SideBar } from '../SideBar/SideBar'
import classes from './Layout.module.css'
import { Footer } from '../../../components/Footer/Footer';

export function Layout({children}: {children: ReactNode}){
    

    return(
        <div className={classes.container}>
                <NavBar />
                <div className={classes.innerContainer}>
                    <SideBar />
                    {children}
                </div>
                <Footer />
        </div>
    )
}