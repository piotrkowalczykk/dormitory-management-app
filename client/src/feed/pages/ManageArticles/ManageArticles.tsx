
import { useState, useEffect } from 'react'
import { Layout } from '../../components/Layout/Layout'
import classes from './ManageArticles.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faEye, faNewspaper, faTrashAlt} from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';

export function ManageArticles(){

    const [articles, setArticles] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(()=>{
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
                    console.log('Failed to fetch articles.');
                }

                const data = await response.json();
                setArticles(data);
            } catch (error){
                console.log(error);
            } finally {
                setIsLoading(false);
            }
    }
    fetchArticles();
    }, []);

    return(
        <Layout pageTitle="Dashboard" navigation={
            <> 
            <Link to='/dashboard'>Dashboard</Link> / <span style={{color: 'red'}}>articles</span>
            </>
        }>
            <div className={classes.container}>
                <div className={classes.innerContainer}>
                <div className={classes.header}>
                    <h2 className={classes.containerName}>Articles</h2>
                    <button className={classes.addArticleBtn} title='Add new article'>+  <FontAwesomeIcon icon={faNewspaper} className={classes.icon} /></button>
                </div>
                {isLoading ? <p>Loading...</p> :
                <table className={classes.table}>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Title</th>
                            <th>Creation Date</th>
                            <th>Last Modification Date</th>
                            <th>Manage</th>
                        </tr>
                    </thead>
                    <tbody>
                        {articles.map(article => (
                            <tr key={article.id} className={classes.rowSeparator}>
                                <td>{article.id}</td>
                                <td>{article.title}</td>
                                <td>{article.creationDate}</td>
                                <td>{article.lastModifiedDate}</td>
                                <td>
                                    <button className={classes.manageBtn} title='View' style={{background: 'blueviolet'}} ><FontAwesomeIcon icon={faEye} className={classes.manageIcon} /></button>
                                    <button className={classes.manageBtn} title='Edit' style={{background: 'yellow'}} ><FontAwesomeIcon icon={faEdit} className={classes.manageIcon} /></button>
                                    <button className={classes.manageBtn} title='Delete' style={{background: 'red'}} ><FontAwesomeIcon icon={faTrashAlt} className={classes.manageIcon} /></button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
                }
                </div>
            </div>

        </Layout>
    )
}
