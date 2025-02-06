import classes from './Home.module.css'
import { NavBar } from '../../components/NavBar/NavBar'
import { Article } from '../../components/Article/Article'
import { Footer } from '../../../components/Footer/Footer'
import { useEffect, useState } from 'react'
import { Button } from '../../../authentication/components/Button/Button'
import { Layout } from '../../components/Layout/Layout'
export function Home(){

    const [posts, setPosts] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
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
                console.log(data);
            } catch (error){
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
                    <h1><span style={{color: 'red'}}>.</span>News</h1>
                </div>
                <Button type='button'>Add article +</Button>
                {isLoading ? <p>Loading...</p> : 
                    posts.map(post => 
                        (
                            <Article
                            key={post.id}
                            title={post.title}
                            description={post.description}
                            image={post.image}
                            data={post.creationDate}
                          />
                        ))
                }
            </div>
        </div>
        </Layout>
    )
}