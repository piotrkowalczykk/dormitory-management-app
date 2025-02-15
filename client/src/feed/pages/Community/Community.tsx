import { Layout } from '../../components/Layout/Layout'
import { Post } from '../../components/Post/Post'
import classes from './Community.module.css'
import { useState, useEffect } from 'react';

export function Community(){

    const [posts, setPosts] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    
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
        <Layout>
        <div className={classes.container}>
            <div className={classes.main}>
                <div className={classes.title}>
                    <h1><span style={{color: 'red'}}>.</span>Community</h1>
                </div>
                {isLoading ? <p>Loading...</p> :
                    posts.map(post => (
                        <Post 
                            key={post.id}
                            postId={post.id}
                            content={post.content}
                            image={post.image}
                            name={post.author.firstName + post.author.lastName}
                            date={post.creationDate}
                            likes={post.likes}
                            comments={post.comments}
                        />
                    ))
                }
            </div>
        </div>
        </Layout>
    )
}