import { ReactNode } from 'react';
import classes from './Layout.module.css';
import { Header } from '../Header/Header';
import { Footer } from '../Footer/Footer';

export function Layout({children}: {children: ReactNode}){
    return(
        <>
        <div className={classes.container}>
            <Header />
            <div className={classes.main}>
                {children}
            </div>
            <Footer />
        </div>
        </>
    );
}