import classes from './Home.module.css'
import { Nav } from '../../components/Nav/Nav'

export function Home(){
    return (
        <div className={classes.container}>
            <Nav />
        </div>
    )
}