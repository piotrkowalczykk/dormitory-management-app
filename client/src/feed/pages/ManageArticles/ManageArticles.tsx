import { useState, useEffect } from 'react'
import { FileUpload } from '../../components/FileUpload/FileUpload'
import { Layout } from '../../components/Layout/Layout'
import classes from './ManageArticles.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit } from '@fortawesome/free-solid-svg-icons';
import { faTrash } from '@fortawesome/free-solid-svg-icons';

export function ManageArticles(){

    const [isLoading, setIsLoading] = useState(true);
    const [articles, setArticles] = useState([]);

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
        <Layout>
        <div className={classes.container}>
            <div className={classes.innerContainer1}>
                <FileUpload />
                <div className={classes.inputs}>
                    <div className={classes.titleAndDescription}>
                        <label htmlFor='title'>Title</label>
                        <input type='text-field' className={classes.input}/>
                        <label htmlFor='title' className={classes.inputLabel}>Description</label>
                        <textarea rows="3" className={classes.input}/>
                        <button className={classes.btn}>SAVE</button>
                    </div>
                    <div className={classes.content}>
                        <label htmlFor='content'>Content</label>
                        <textarea rows="12" className={classes.input}/>
                    </div>
                </div>
            </div>
            <div className={classes.innerContainer2}>
                <h1 className={classes.title}>Your articles</h1>
                {isLoading ? <p>Loading...</p> : 
                                    <table>
                                        <tr>
                                            <th>ID</th>
                                            <th>TITLE</th>
                                            <th>CREATION DATE</th>
                                            <th>EDIT</th>
                                            <th>DELETE</th>
                                        </tr>
                                        {articles.map(article => 
                                        (
                                            <tr key={article.id}>
                                                <td>{article.id}</td>
                                                <td>{article.title}</td>
                                                <td>{article.creationDate}</td>
                                                <td><button><FontAwesomeIcon icon={faEdit} /></button></td>
                                                <td><button><FontAwesomeIcon icon={faTrash} /></button></td>
                                            </tr>
                                        ))}
                                    </table>
                                    
                                }
            </div>
        </div>
        </Layout>
    )
}