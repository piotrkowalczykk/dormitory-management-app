import classes from './ManageCard.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faNewspaper, faBuilding, faUsers, faBed } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';

const iconMap = {
    faNewspaper: faNewspaper,
    faBuilding: faBuilding,
    faUsers: faUsers,
    faBed: faBed,
};

export function ManageCard({bgColor, iconName, counter, name, link}){
    const icon = iconMap[iconName];
    return(
        <Link to={link}>
            <div className={classes.container} style={{background: bgColor}}>
                <div className={classes.textContainer}>
                    <h1 className={classes.counter}>{counter}</h1>
                    <h2 className={classes.fieldName}>{name}</h2>
                </div>
                <FontAwesomeIcon icon={icon} className={classes.icon} style={{color: bgColor}}/>
            </div>
        </Link>
    )
}