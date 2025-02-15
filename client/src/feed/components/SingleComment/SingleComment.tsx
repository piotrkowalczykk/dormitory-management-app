import classes from './SingleComment.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { useAuth } from '../../../authentication/AuthProvider';

export function SingleComment({authorEmail, name, date, content}){

    const {userDetails} = useAuth();

    return(
        <div className={classes.container}>
                <div className={classes.userData}>
                    <img className={classes.userAvatar} src="https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg"/>
                    <div className={classes.userDataContainer}>
                        <h1 className={classes.userName}>{name}</h1>
                        <h2 className={classes.data}>{date}</h2>
                    </div>
                    {userDetails.email === authorEmail && (<div className={classes.userBtns}>
                        <button className={classes.editPost}><FontAwesomeIcon icon={faEdit} className={classes.editIcon} /></button>
                        <button className={classes.deletePost}><FontAwesomeIcon icon={faTrash} className={classes.deleteIcon} /></button>
                    </div>)}
                </div>
                <p className={classes.content}>{content}</p>
            </div>
    )
}