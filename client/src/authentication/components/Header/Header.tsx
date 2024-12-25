import { Link } from 'react-router-dom';
import classes from './Header.module.css';

export function Header(){
    return(
            <div className={classes.container}>
                <Link to="/" className={classes.link}>
                    <img className={classes.logo} src="/logo.png" />
                </Link>
            </div>
    );
}