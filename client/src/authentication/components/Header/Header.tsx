import classes from './Header.module.css';

export function Header(){
    return(
            <div className={classes.container}>
                <img className={classes.logo} src="/logo.png" />
            </div>
    );
}