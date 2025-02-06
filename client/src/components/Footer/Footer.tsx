import classes from './Footer.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faGithub} from '@fortawesome/free-brands-svg-icons';
export function Footer(){
    return(
            <div className={classes.container}>
                <FontAwesomeIcon icon={faGithub} />
                <span className={classes.text}> Created by piotrkowalczykk</span>
            </div>
    );
}