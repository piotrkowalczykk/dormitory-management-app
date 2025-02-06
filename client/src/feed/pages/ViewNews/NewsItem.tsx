import { useLocation } from 'react-router-dom'
import classes from './NewsItem.module.css'
import { NavBar } from '../../components/NavBar/NavBar';
import { Footer } from '../../../components/Footer/Footer';

export function NewsItem(){

    const location = useLocation();
    const {title, image, content, data} = location.state || {};

    return (
        <>
        <NavBar />
        <div className={classes.container}>
            <div className={classes.innerContainer}>
                <img className={classes.image} src={image}></img>
                <h1 className={classes.title}>{title}</h1>
                <p className={classes.content}>{content}</p>
                <p className={classes.data}>{data}</p>
            </div>
        </div>
        <Footer />
        </>
    )
    
}