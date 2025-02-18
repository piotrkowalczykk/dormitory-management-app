import classes from './Post.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faThumbsUp } from '@fortawesome/free-solid-svg-icons';
import { faComment } from '@fortawesome/free-solid-svg-icons';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { useEffect, useState } from 'react';
import { Comments } from '../Comments/Comments';
import { useAuth } from '../../../authentication/AuthProvider';
import { ListOfLikes } from '../ListOfLikes/ListOfLikes';
export function Post({postId, content, image, name, date, likes, comments}){

    const [isCommentOpen, setIsCommentOpen] = useState(false);
    const [isLikeOpen, setIsLikeOpen] = useState(false);
    const [isPostLiked, setIsPostLiked] = useState(false);
    const [likesCounter, setLikesCounter] = useState(likes.length);
    const [commentsCounter, setCommentsCounter] = useState(comments.length);
    const {userDetails} = useAuth();
    
    const handleComment = () => {
        setIsCommentOpen(true);
    }
    
    const handleLikedUsers = () => {
        setIsLikeOpen(true);
    }

    const handleLikePost = async () => {
        try {
            const response = await fetch(`http://localhost:8080/feed/posts/${postId}/like`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${localStorage.getItem("token")}`,
                },
            });

            if(response.ok){
                const data = await response.json();
                const userLiked = data.likedUsers?.some(user => user.id === userDetails.id) ?? false;
                setIsPostLiked(userLiked);
                setLikesCounter(data.likedUsers.length);
            }
        } catch (error){
            console.log(error);
        }
    }

    useEffect(()=>{
        console.log(userDetails.id)
        setIsPostLiked(likes.some(user => user.id === userDetails.id));
    }, [likes, userDetails.id]);

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
                <button className={classes.likes} onClick={handleLikedUsers}>👍 {likesCounter}</button>
                <button className={classes.comments} onClick={handleComment}>💬 {commentsCounter}</button>
            </div>
            <div className={classes.btns}>
                {console.log(isPostLiked)}
                <button className={`${classes.like} ${isPostLiked ? classes.liked : ""}`} onClick={handleLikePost}><FontAwesomeIcon icon={faThumbsUp} /> I like it!</button>
                <button className={classes.comment} onClick={handleComment}><FontAwesomeIcon icon={faComment} /> Comment</button>
            </div>
        </div>
        {isCommentOpen && <Comments closeModal={() => setIsCommentOpen(false)} postId={postId} onCommentChange={setCommentsCounter}/>}
        {isLikeOpen && <ListOfLikes closeModal={() => setIsLikeOpen(false)} postId={postId} />}
        </>
    )
}