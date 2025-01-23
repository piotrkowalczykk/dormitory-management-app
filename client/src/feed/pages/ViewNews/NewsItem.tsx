import { useParams } from 'react-router-dom'
import classes from './NewsItem.module.css'
import { Nav } from '../../components/Nav/Nav';

export function NewsItem(){

    const { newsTitle } = useParams();

    return (
        <div className={classes.container}>
            <Nav />
            <div className={classes.content}>
            </div>
            <h1>{newsTitle}</h1>
        </div>
    )
    
}