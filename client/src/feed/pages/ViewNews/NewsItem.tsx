import { useLocation } from 'react-router-dom'
import classes from './NewsItem.module.css'
import { Nav } from '../../components/Nav/Nav';
import { Footer } from '../../../authentication/components/Footer/Footer';

export function NewsItem(){

    const location = useLocation();
    const {title, image, content, data} = location.state || {};

    return (
        <>
        <Nav />
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