import { useEffect, useState } from 'react';
import { FileUpload } from '../FileUpload/FileUpload';
import classes from './PostEditor.module.css';

export function PostEditor({ closeModal, post }) {
    const [postData, setPostData] = useState({
        content: '',
        image: null
    });

    useEffect(() => {
        if(post) {
            setPostData(prevData => ({
                ...prevData,
                content: post.content || '',
                image: post.image || null
            }));
        }
    }, [post]);

    const handleSave = async () => {
        const formData = new FormData();
        formData.append('content', postData.content);
        if (postData.image && typeof postData.image !== 'string') {
            formData.append('image', postData.image);
        }

        const method = post ? 'PUT' : 'POST';
        const url = post ? `http://localhost:8080/feed/posts/${post.id}` : `http://localhost:8080/feed/posts`;

        try {
            const response = await fetch(url, {
                method,
                headers: {
                    "Authorization": `Bearer ${localStorage.getItem("token")}`,
                },
                body: formData,
            });

            if (!response.ok) {
                console.log(`Failed to save post. Status code: ${response.status}.`);
            }

            closeModal();
            post ? alert("Post edited successfully") : alert("Post added successfully");

        } catch (error) {
            console.log(error);
        }
    };

    const handleChange = (e) => {
        setPostData({ ...postData, content: e.target.value });
    };

    return (
        <div className={classes.overlay} onClick={closeModal}>
            <div className={classes.container} onClick={(e) => e.stopPropagation()}>
                <div className={classes.header}>
                    <div className={classes.spacer} />
                    <p className={classes.title}>{post ? "Edit Post" : "Create a post"}</p>
                    <button className={classes.closeBtn} onClick={closeModal}>X</button>
                </div>
                <textarea 
                    className={classes.postContent} 
                    rows='3' 
                    placeholder='What are you thinking about?' 
                    onChange={handleChange} 
                    value={postData.content}
                />
                <FileUpload 
                    onFileSelect={(image) => {
                        setPostData(prevData => ({ ...prevData, image }));
                    }}
                    onRemoveImage={() => {
                        setPostData(prevData => ({ ...prevData, image: null }));
                    }}
                    initialImage={postData.image}
                />
                <button className={classes.publishBtn} onClick={handleSave}>
                    {post ? "Update" : "Publish"}
                </button>
            </div>
        </div>
    );
}
