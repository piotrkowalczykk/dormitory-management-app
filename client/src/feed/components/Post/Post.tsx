import classes from './Post.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faThumbsUp } from '@fortawesome/free-solid-svg-icons';
import { faComment } from '@fortawesome/free-solid-svg-icons';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { useState } from 'react';
import { Comments } from '../Comments/Comments';
import { useAuth } from '../../../authentication/AuthProvider';
export function Post({postId, content, image, name, date, likes, comments}){

    const [isCommentOpen, setIsCommentOpen] = useState(false);
    
    const handleComment = () => {
        setIsCommentOpen(true);
    }


    const {userDetails} = useAuth();

    return(
        <>
        <div className={classes.container}>
            <div className={classes.user}>
                <div className={classes.userData}>
                    <img className={classes.userAvatar} src="https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg" />
                    <div className={classes.userDataContainer}>
                        <h1 className={classes.userName}>{name}</h1>
                        <h2 className={classes.data}>{date}</h2>
                    </div>
                </div>
                <div className={classes.userBtns}>
                    <button className={classes.editPost}><FontAwesomeIcon icon={faEdit} className={classes.editIcon} /></button>
                    <button className={classes.deletePost}><FontAwesomeIcon icon={faTrash} className={classes.deleteIcon} /></button>
                </div>
            </div>
            <p className={classes.content}>{content}</p>
            <img className={classes.image} src={image} />
            <div className={classes.stats}>
                <button className={classes.likes}>👍 {likes.length}</button>
                <button className={classes.comments} onClick={handleComment}>💬 {comments.length}</button>
            </div>
            <div className={classes.btns}>
                <button className={classes.like}><FontAwesomeIcon icon={faThumbsUp} /> I like it!</button>
                <button className={classes.comment}><FontAwesomeIcon icon={faComment} /> Comment</button>
            </div>
        </div>
        {isCommentOpen && <Comments closeModal={() => setIsCommentOpen(false)} postId={postId} />}
        </>
    )
}