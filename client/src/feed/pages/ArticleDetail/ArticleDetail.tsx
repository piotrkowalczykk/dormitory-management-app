import { useLocation } from 'react-router-dom';
import { Layout } from '../../components/Layout/Layout';
import classes from './ArticleDetail.module.css'
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
export function ArticleDetail(){

    const location = useLocation();
    const articleId = location.state?.id;

    const [article, setArticle] = useState({
        image: '',
        title: '',
        content: '',
        date: ''
    });

    const fetchArticles = async () => {
        try{
            const response = await fetch(`http://localhost:8080/feed/articles/${articleId}`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${localStorage.getItem("token")}`,
                },
        });
        const data = await response.json();
        setArticle({
            image: data.image ? data.image : '',
            title: data.title,
            content: data.content,
            date: data.creationDate,
        });
        } catch(error){
            console.error(error);
        }
    };

    useEffect(() => {
        fetchArticles();
    }, []);

    return (
        <Layout pageTitle="Home" navigation={
            <> 
            / <Link to='/'>Home</Link> / <span style={{ color: 'red' }}>{article.title}</span>
            </>
        }>
        <div className={classes.container}>
            <div className={classes.innerContainer}>
                {article.image && <img className={classes.image} src={article.image} />}
                <h1 className={classes.title}>{article.title}</h1>
                <p className={classes.content}>{article.content}</p>
                <p className={classes.data}>{article.date}</p>
            </div>
        </div>
        </Layout>
    )
    
}