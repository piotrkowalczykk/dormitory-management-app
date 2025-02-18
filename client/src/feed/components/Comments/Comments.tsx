import { SingleComment } from '../SingleComment/SingleComment';
import classes from './Comments.module.css';
import { useEffect, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';

export function Comments({ closeModal, postId, onCommentChange }) {

    const [comments, setComments] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [newComment, setNewComment] = useState('');

    useEffect(()=>{
        const fetchComments = async () => {
            try {
                const response = await fetch(`http://localhost:8080/feed/posts/${postId}/comments`, {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": `Bearer ${localStorage.getItem("token")}`,
                    },
            });
                if(!response.ok){
                    console.log(response.status);
                }
                const data = await response.json();
                setComments(data);
            } catch(error){
                console.log(error);
            } finally {
                setIsLoading(false);
            }   
        };
        fetchComments();
    }, []);

    useEffect(()=>{
        onCommentChange(comments.length)
    }, [comments, onCommentChange]);

    const handleAddComment = async () => {
        try {
            const response = await fetch(`http://localhost:8080/feed/posts/${postId}/comments`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${localStorage.getItem("token")}`,
                },
                body: JSON.stringify({ content: newComment }),
            });

            if (!response.ok) {
                console.log("Failed to add comment");
                return;
            }

            const addedComment = await response.json();
            setComments(prevComments => [...prevComments, addedComment]);
            setNewComment('');
        } catch (error) {
            console.log(error);
        }
    };

    const handleDeleteComment = (commentId) => {
        setComments(comments.filter(comment => comment.id !== commentId));
    }

    const handleEditComment = (commentId, editedContent) => {
        setComments(prevComments => prevComments.map(comment =>
            comment.id === commentId ? { ...comment, content: editedContent } : comment
        ));
    };
    
    return (
        <div className={classes.overlay} onClick={closeModal}>
            <div className={classes.container} onClick={(e) => e.stopPropagation()}>
                <button className={classes.closeBtn} onClick={closeModal}>X</button>
                <input type='text'
                        placeholder='Write a comment'
                        className={classes.input}
                        value={newComment} 
                        onChange={(e) => setNewComment(e.target.value)} />
                <button className={classes.send} disabled={newComment === ''} onClick={handleAddComment}><FontAwesomeIcon icon={faPaperPlane} /></button>
                <div className={classes.listOfComments}>
                    {isLoading ? <p>Loading...</p> :
                        comments.map(comment => (
                            <SingleComment
                                key={comment.id}
                                commentId={comment.id}
                                authorId={comment.author.id} 
                                name={comment.author.firstName + " " + comment.author.lastName}
                                date={comment.creationDate}
                                content={comment.content}
                                onDelete={handleDeleteComment}
                                onEdit={handleEditComment}
                            />
                        ))
                    }
                </div>
            </div>
        </div>
    );
}
