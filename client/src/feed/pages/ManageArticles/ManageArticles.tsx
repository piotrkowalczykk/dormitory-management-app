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
    const [selectedArticle, setSelectedArticle] = useState(null);
    const [articleData, setArticleData] = useState({
        title: '',
        description: '',
        content: '',
        image: null,
    });
    const [previewImage, setPreviewImage] = useState(null);
    const [imageRemoved, setImageRemoved] = useState(false);

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

    const handleDelete = async (articleId) => {
        try{
            const response = await fetch(`http://localhost:8080/customer/delete-article/${articleId}`, {
                method: "DELETE",
                headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem("token")}`,
                },
            });

            if (response.ok) {
                setArticles(articles.filter(article => article.id !== articleId));
            } else {
                console.log('Failed to delete article');
            }
        } catch (error) {
            console.log(error);
        }
    }

    const handleEdit = async (article) => {
        setSelectedArticle(article);
        setArticleData({
            title: article.title,
            description: article.description,
            content: article.content,
            image: null,
        });

        if(article.image){
            setPreviewImage(`http://localhost:8080/api/uploads/${article.image}`);
        } else {
            setPreviewImage(null);
        }
    }

    const handleRemoveImage = () => {
        setArticleData(prev => ({ ...prev, image: null })); 
        setPreviewImage(null);
        setImageRemoved(true);
    };

    const handleNewArticle = () => {
        setSelectedArticle(null);
        setArticleData({
            title: '',
            description: '',
            content: '',
            image: null,
        });
        setPreviewImage(null);
        setImageRemoved(false);
    }

    const handleChange = (e) => {
        const {name, value} = e.target;
        setArticleData(prevState => ({
            ...prevState,
            [name]: value,
        }));
    }

    const handleSave = async () => {
        const formData = new FormData();
        formData.append('title', articleData.title);
        formData.append('description', articleData.description);
        formData.append('content', articleData.content);

        if(imageRemoved){
            formData.append('image', new Blob([]), "empty.jpg");
        } else if (articleData.image){
            formData.append('image', articleData.image);
        }

        const url = selectedArticle ? 
            `http://localhost:8080/customer/edit-article/${selectedArticle.id}`
            : 'http://localhost:8080/customer/create-article';

        const method = selectedArticle ? 'PUT' : 'POST';

        try{
            const response = await fetch(url, {
                method,
                headers: {
                    "Authorization": `Bearer ${localStorage.getItem("token")}`,
                },
                body: formData,
            });

            if(response.ok){
                const updatedArticle = await response.json();
                if(selectedArticle){
                    setArticles(articles.map(article => article.id === updatedArticle.id ? updatedArticle : article));
                } else {
                    setArticles([...articles, updatedArticle]);
                }
                handleNewArticle();
                handleRemoveImage();
            } else {
                console.log('Failed to save article');
            }
        } catch (error){
            console.log(error);
        }
    }

    return (
        <Layout>
        <div className={classes.container}>
            <div className={classes.innerContainer1}>
                <button className={classes.btnNew} title="Add new article" onClick={handleNewArticle}>+</button>
                <FileUpload onRemoveImage={handleRemoveImage} initialImage={previewImage} onFileSelect={(file) => setArticleData(prev => ({ ...prev, image: file }))} />
                <div className={classes.inputs}>
                    <div className={classes.titleAndDescription}>
                        <label htmlFor='title'>Title</label>
                        <input 
                            type='text'
                            id='title'
                            name='title'
                            value={articleData.title} 
                            onChange={handleChange} 
                            className={classes.input}/>

                        <label htmlFor='description' className={classes.inputLabel}>Description</label>
                        <textarea
                            rows='3'
                            id='description'
                            name='description'
                            value={articleData.description}
                            onChange={handleChange}
                            className={classes.input}/>

                        <button className={classes.btn} onClick={handleSave}>SAVE</button>
                    </div>
                    <div className={classes.content}>
                        <label htmlFor='content'>Content</label>
                        <textarea 
                            rows='12'
                            id='content'
                            name='content'
                            value={articleData.content}
                            onChange={handleChange} 
                            className={classes.input}/>
                    </div>
                </div>
            </div>
            <div className={classes.innerContainer2}>
                <h1 className={classes.title}>Your articles</h1>
                {isLoading ? <p>Loading...</p> : 
                                    <table>
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>TITLE</th>
                                                <th>CREATION DATE</th>
                                                <th>EDIT</th>
                                                <th>DELETE</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        {articles.map(article => 
                                        (
                                            <tr key={article.id}>
                                                <td>{article.id}</td>
                                                <td>{article.title}</td>
                                                <td>{article.creationDate}</td>
                                                <td><button onClick={() => handleEdit(article)}><FontAwesomeIcon icon={faEdit} /></button></td>
                                                <td><button onClick={() => handleDelete(article.id)}><FontAwesomeIcon icon={faTrash} /></button></td>
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