import classes from './SideBar.module.css'
import { NavLink } from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHouse } from '@fortawesome/free-solid-svg-icons';
import { faCalendar } from '@fortawesome/free-solid-svg-icons';
import { faUserGroup } from '@fortawesome/free-solid-svg-icons';

export function SideBar({isVisible}: {isVisible: boolean}){
    return(
        <div className={`${classes.container} ${isVisible ? classes.visible : classes.hidden}`}>
            <div className={classes.userData}>
                <img className={classes.userAvatar} src="https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg"/>
                <h1 className={classes.userName}>Piotrek Kowalczyk</h1>
            </div>
            <ul className={classes.navigation}>
                    <li>
                        <NavLink to="/" className={classes.navigationItem}>
                            <FontAwesomeIcon icon={faHouse} className={classes.icon}/>
                            <p className={classes.linkName}>Home</p>
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="/dashboard" className={classes.navigationItem}>
                            <FontAwesomeIcon icon={faCalendar} className={classes.icon}/>
                            <p className={classes.linkName}>Dashboard</p>
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="/community" className={classes.navigationItem}>
                            <FontAwesomeIcon icon={faUserGroup} className={classes.icon}/>
                            <p className={classes.linkName}>Community</p>
                        </NavLink>
                    </li>
                </ul>
        </div>
    )
}