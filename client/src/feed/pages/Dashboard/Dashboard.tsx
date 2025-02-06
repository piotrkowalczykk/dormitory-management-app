import classes from './Dashboard.module.css'
import { NavBar } from '../../components/NavBar/NavBar'

export function Dashboard(){
    return (
        <div className={classes.container}>
            <NavBar />
        </div>
    )
}