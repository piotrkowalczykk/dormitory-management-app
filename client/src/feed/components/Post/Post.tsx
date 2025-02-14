import classes from './Post.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faThumbsUp } from '@fortawesome/free-solid-svg-icons';
import { faComment } from '@fortawesome/free-solid-svg-icons';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
export function Post(){
    return(
        <div className={classes.container}>
            <div className={classes.user}>
                <div className={classes.userData}>
                    <img className={classes.userAvatar} src="https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg"/>
                    <div className={classes.userDataContainer}>
                        <h1 className={classes.userName}>Piotrek Kowalczyk</h1>
                        <h2 className={classes.data}>20-02-2025 17:35</h2>
                    </div>
                </div>
                <div className={classes.userBtns}>
                    <button className={classes.editPost}><FontAwesomeIcon icon={faEdit} className={classes.editIcon} /></button>
                    <button className={classes.deletePost}><FontAwesomeIcon icon={faTrash} className={classes.deleteIcon} /></button>
                </div>
            </div>
            <div className={classes.content}>
            500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop pu
            </div>
            <img className={classes.image} src="https://upload.wikimedia.org/wikipedia/commons/c/c4/Gmach_g%C5%82%C3%B3wny_AGH.jpg"/>
            <div className={classes.stats}>
                <button className={classes.likes}>👍 232</button>
                <button className={classes.comments}>💬 17</button>
            </div>
            <div className={classes.btns}>
                <button className={classes.like}><FontAwesomeIcon icon={faThumbsUp} /> I like it!</button>
                <button className={classes.comment}><FontAwesomeIcon icon={faComment} /> Comment</button>
            </div>
        </div>
    )
}