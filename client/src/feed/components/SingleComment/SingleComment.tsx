import classes from './SingleComment.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';
import { useAuth } from '../../../authentication/AuthProvider';
import { useEffect, useRef, useState } from 'react';

export function SingleComment({commentId, authorEmail, name, date, content, onDelete, onEdit}){

    const {userDetails} = useAuth();
    const [isEditing, setIsEditing] = useState(false);
    const [editedContent, setEditedContent] = useState(content);
    const inputRef = useRef(null);

    useEffect(()=>{
        if(isEditing && inputRef.current){
            inputRef.current.focus();
            inputRef.current.setSelectionRange(editedContent.length, editedContent.length);
        }
    }, [isEditing]);

    const handleDelete = async () => {
        try {
            const response = await fetch(`http://localhost:8080/feed/comments/${commentId}`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${localStorage.getItem("token")}`,
                },
            });
            if(response.ok){
                onDelete(commentId);
            } else {
                console.log('Failed to delete comment');
            }

        } catch (error){
            console.log(error);
        }
    }

    const handleEdit = async () => {
        try {
            const response = await fetch(`http://localhost:8080/feed/comments/${commentId}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${localStorage.getItem("token")}`,
                },
                body: JSON.stringify({content: editedContent}),
            });
            if(response.ok){
                setIsEditing(false);
                onEdit(commentId, editedContent);
            } else {
                console.log('Failed to edit comment');
            }

        } catch (error){
            console.log(error);
        }
    }

    return(
        <div className={classes.container}>
                <div className={classes.userData}>
                    <img className={classes.userAvatar} src="https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg"/>
                    <div className={classes.userDataContainer}>
                        <h1 className={classes.userName}>{name}</h1>
                        <h2 className={classes.data}>{date}</h2>
                    </div>
                    {userDetails.email === authorEmail && (
                        isEditing ? 
                        <div className={classes.userBtns}>
                            <button className={classes.saveEdit} onClick={handleEdit}><FontAwesomeIcon icon={faPaperPlane} className={classes.editIcon} /></button>
                            <button className={classes.cancelEdit} onClick={()=>setIsEditing(false)}>X</button>
                        </div>
                        :
                        <div className={classes.userBtns}>
                            <button className={classes.editPost} onClick={()=>setIsEditing(true)}><FontAwesomeIcon icon={faEdit} className={classes.editIcon} /></button>
                            <button className={classes.deletePost} onClick={handleDelete}><FontAwesomeIcon icon={faTrash} className={classes.deleteIcon} /></button>
                        </div>
                    )}
                </div>
                {isEditing ?
                    <input
                        type='text' 
                        value={editedContent}
                        onChange={(e)=> setEditedContent(e.target.value)}
                        ref={inputRef}
                        className={classes.editInput}
                         />
                    :
                    <p className={classes.content}>{content}</p>
                }
            </div>
    )
}