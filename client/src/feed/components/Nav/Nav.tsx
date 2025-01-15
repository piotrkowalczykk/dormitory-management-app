import { Link, NavLink } from 'react-router-dom'
import classes from './Nav.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHouse } from '@fortawesome/free-solid-svg-icons';
import { faCalendar } from '@fortawesome/free-solid-svg-icons';
import { faUserGroup } from '@fortawesome/free-solid-svg-icons';


export function Nav(){
    return (
        <div className={classes.container}>
            <Link to="/">
                <img src='logo.png' className={classes.logo}/>
            </Link>
            <div className={classes.nav}>
                <ul>
                    <li>
                        <NavLink to="/" className={({ isActive }) => isActive ? `${classes.icons} ${classes.activeIcon}` : classes.icons}>
                            <FontAwesomeIcon icon={faHouse} />
                            <p className={classes.linkName}> Home</p>
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="/dashboard" className={({ isActive }) => isActive ? `${classes.icons} ${classes.activeIcon}` : classes.icons}>
                            <FontAwesomeIcon icon={faCalendar} />
                            <p className={classes.linkName}> Dashboard</p>
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="/community" className={({ isActive }) => isActive ? `${classes.icons} ${classes.activeIcon}` : classes.icons}>
                            <FontAwesomeIcon icon={faUserGroup} />
                            <p className={classes.linkName}> Community</p>
                        </NavLink>
                    </li>
                </ul>
            </div>
            <img src="" className={classes.avatar}></img>
        </div>
    )
}