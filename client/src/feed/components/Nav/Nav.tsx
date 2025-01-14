import { Link, NavLink } from 'react-router-dom'
import classes from './Nav.module.css'

export function Nav(){
    return (
        <div className={classes.container}>
            <Link to="/">
                <img src='logo.png' className={classes.logo}/>
            </Link>
            <div className={classes.menu}>
                <span></span>
                <span></span>
                <span></span>
            </div>
            <div className={classes.nav}>
                <ul>
                    <li>
                        <NavLink to="/">Home</NavLink>
                    </li>
                    <li>
                        <NavLink to="/">Dashboard</NavLink>
                    </li>
                    <li>
                        <NavLink to="/">Community</NavLink>
                    </li>
                </ul>
            </div>
            <div className={classes.btns}>Logout</div>
        </div>
    )
}