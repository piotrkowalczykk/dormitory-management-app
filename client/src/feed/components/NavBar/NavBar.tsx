import { Link } from 'react-router-dom'
import classes from './NavBar.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars } from '@fortawesome/free-solid-svg-icons';


export function NavBar({ onToggleSideBar }: { onToggleSidebar: () => void }){

    return (
        <div className={classes.container}>
            <Link to="/">
                <img src='/public/logo.png' className={classes.logo}/>
            </Link>
            <button onClick={onToggleSideBar}>
                <FontAwesomeIcon icon={faBars} className={classes.btn}/>
            </button>
        </div>
    )
}