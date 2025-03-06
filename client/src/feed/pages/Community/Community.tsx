import { Layout } from '../../components/Layout/Layout'
import { Post } from '../../components/Post/Post'
import { PostEditor } from '../../components/PostEditor/PostEditor';
import classes from './Community.module.css'
import { useState, useEffect } from 'react';
export function Community(){

    const [posts, setPosts] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [isPostEditorOpen, setIsPostEditorOpen] = useState(false);

    const [newPostData, setNewPostData] = useState({
        content: '',
        image: ''
    });
    
    useEffect(()=>{
        const fetchPosts = async () => {
            try {
                const response = await fetch("http://localhost:8080/feed/posts", {
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
                    setPosts(data);
                } catch(error){
                    console.log(error);
                } finally {
                    setIsLoading(false);
                }   
            };
            fetchPosts();
        }, []);
    
    return (
        <Layout pageTitle="Community" navigation={
            <> 
            / <span style={{color: 'red'}}>Community</span>
            </>
        }>
        <div className={classes.container}>
            <div className={classes.main}>
                <div className={classes.newPostContainer}>
                    <button className={classes.newPostBtn} onClick={()=>setIsPostEditorOpen(true)}>What are you thinking about?</button>
                </div>
                
                {isLoading ? <p>Loading...</p> :
                    posts.map(post => (
                        <Post
                            key={post.id} 
                            post={post}
                        />
                    ))
                }
            </div>
        </div>
        {isPostEditorOpen && <PostEditor closeModal={()=>setIsPostEditorOpen(false)} />}
        </Layout>
    )
}