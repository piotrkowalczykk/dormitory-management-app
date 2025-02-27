import classes from './Home.module.css'
import { Article } from '../../components/Article/Article'
import { useEffect, useState } from 'react'
import { Layout } from '../../components/Layout/Layout'
import { Link } from 'react-router-dom';
export function Home(){

    const [articles, setArticles] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const fetchArticles = async () => {
            try {
                const response = await fetch("http://localhost:8080/feed/articles", {
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
                setArticles(data);
                console.log(data);
            } catch (error){
                console.log(error);
            } finally {
                setIsLoading(false);
            }
        };

        fetchArticles();
    }, []);

    
    return (
        <Layout pageTitle="Home" navigation={
            <> 
            / <Link to="/"><span style={{ color: 'red' }}>Home</span></Link>
            </>
        }>
        <div className={classes.container}>
            <div className={classes.main}>
                {isLoading ? <p>Loading...</p> : 
                    articles.map(article => 
                        (
                            <Article
                            key={article.id}
                            id = {article.id}
                            title={article.title}
                            description={article.description}
                            image={article.image}
                            data={article.creationDate}
                          />
                        ))
                }
            </div>
        </div>
        </Layout>
    )
}