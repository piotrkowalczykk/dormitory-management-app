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
import { PostEditor } from '../PostEditor/PostEditor';
export function Post({post}){

    
    const [isPostEditorOpen, setIsPostEditorOpen] = useState(false);
    const [isCommentOpen, setIsCommentOpen] = useState(false);
    const [isLikeOpen, setIsLikeOpen] = useState(false);
    const [isPostLiked, setIsPostLiked] = useState(false);
    const [likesCounter, setLikesCounter] = useState(post.likedUsers.length);
    const [commentsCounter, setCommentsCounter] = useState(post.comments.length);
    const {userDetails} = useAuth();
    
    useEffect(() => {
        setIsPostLiked(post.likedUsers?.some(user => user.id === userDetails.id) ?? false);
    }, [post, userDetails.id]);



    const handleLikePost = async () => {
        try {
            const response = await fetch(`http://localhost:8080/feed/posts/${post.id}/like`, {
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

    const handleDeletePost = async () => {
        try {
            const response = await fetch(`http://localhost:8080/feed/posts/${post.id}`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${localStorage.getItem("token")}`,
                },
            });
            onPostDelete();
        } catch (error){
            console.log(error);
        }
    }

    return(
        <>
        <div className={classes.container}>
            <div className={classes.user}>
                <div className={classes.userData}>
                    <img className={classes.userAvatar} src="https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg" />
                    <div className={classes.userDataContainer}>
                        <h1 className={classes.userName}>{post.author.firstName + " " + post.author.lastName}</h1>
                        <h2 className={classes.data}>{post.creationDate}</h2>
                    </div>
                </div>
                {userDetails.id === post.author.id && (<div className={classes.userBtns}>
                    <button className={classes.editPost} onClick={()=>setIsPostEditorOpen(true)}><FontAwesomeIcon icon={faEdit} className={classes.editIcon} /></button>
                    <button className={classes.deletePost} onClick={handleDeletePost}><FontAwesomeIcon icon={faTrash} className={classes.deleteIcon} /></button>
                </div>)}
            </div>
            <p className={classes.content}>{post.content}</p>
            <img className={classes.image} src={post.image} />
            <div className={classes.stats}>
                <button className={classes.likes} onClick={()=>setIsLikeOpen(true)}>👍 {likesCounter}</button>
                <button className={classes.comments} onClick={()=>setIsCommentOpen(true)}>💬 {commentsCounter}</button>
            </div>
            <div className={classes.btns}>
                <button className={`${classes.like} ${isPostLiked ? classes.liked : ""}`} onClick={handleLikePost}><FontAwesomeIcon icon={faThumbsUp} /> I like it!</button>
                <button className={classes.comment} onClick={()=>setIsCommentOpen(true)}><FontAwesomeIcon icon={faComment} /> Comment</button>
            </div>
        </div>
        {isCommentOpen && <Comments closeModal={() => setIsCommentOpen(false)} postId={post.id} onCommentChange={setCommentsCounter}/>}
        {isLikeOpen && <ListOfLikes closeModal={() => setIsLikeOpen(false)} postId={post.id} />}
        {isPostEditorOpen && <PostEditor closeModal={()=>setIsPostEditorOpen(false)} post={post} />}
        </>
    )
}