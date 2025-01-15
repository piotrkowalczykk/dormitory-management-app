import classes from './Dashboard.module.css'
import { Nav } from '../../components/Nav/Nav'

export function Dashboard(){
    return (
        <div className={classes.container}>
            <Nav />
        </div>
    )
}