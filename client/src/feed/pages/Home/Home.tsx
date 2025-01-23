import classes from './Home.module.css'
import { Nav } from '../../components/Nav/Nav'
import { News } from '../../components/News/News'
import { Footer } from '../../../authentication/components/Footer/Footer'
import { useEffect, useState } from 'react'
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
        <div className={classes.container}>
            <Nav />
            <div className={classes.main}>
                <div className={classes.title}>
                    <h1><span style={{color: 'red'}}>.</span>News</h1>
                </div>
                {isLoading ? <p>Loading...</p> : 
                    posts.map(post => 
                        (
                            <News
                            key={post.id} // Klucz dla każdego elementu listy
                            title={post.title} // Tytuł posta
                            description={post.description} // Opis posta
                            image={post.image} // URL obrazka
                            data={post.creationDate} // Data posta
                          />
                        ))
                }
            </div>
            <Footer />
        </div>
    )
}