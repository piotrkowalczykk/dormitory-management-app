import classes from './ManageArticle.module.css'
import { Layout } from '../../../components/Layout/Layout'
import { Link, useNavigate } from 'react-router-dom'
import { FileUpload } from '../../../components/FileUpload/FileUpload'
import { useEffect, useState } from 'react'
import { useLocation } from 'react-router-dom'

export function ManageArticle(){

    const location = useLocation();
    const articleId = location.state?.id;

    const navigate = useNavigate();

    const [dormitories, setDormitories] = useState([]);
    const [article, setArticle] = useState({
        title: "",
        description: "",
        content: "",
        visibleInDormitories: []
    });

    useEffect(()=>{
        fetchDormitories();
        articleId && fetchArticle();
    }, []);


    const fetchDormitories = async () => {
        try {
            const response = await fetch("http://localhost:8080/customer/dormitories", {
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
            setDormitories(data);

        } catch (error){
            console.log(error);
        }
    }

    const fetchArticle = async () => {
        try {
            const response = await fetch(`http://localhost:8080/feed/articles/${articleId}`, {
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
            const dormitoryIds = data.visibleInDormitories.map(dorm => dorm.id);
            setArticle({...data, visibleInDormitories: dormitoryIds});

        } catch (error){
            console.log(error);
        }
    }

    const handleVisibilityChange = (dormId) => {
        const selectedDormitories = article.visibleInDormitories?.some(id => id === dormId)
        ? article.visibleInDormitories.filter(id => id !== dormId)
        : [...article.visibleInDormitories, dormId];
        setArticle({...article, visibleInDormitories: selectedDormitories});
    }

    const handleSave = async () => {
        const formData = new FormData();
        formData.append('title', article.title);
        formData.append('description', article.description);
        formData.append('content', article.content);
        formData.append('visibleInDormitories', article.visibleInDormitories)
        console.log('obrazek: ' + article.image);
        if (article.image && typeof article.image !== 'string') {
            formData.append('image', article.image);
        } else if (article.image && typeof article.image === 'string'){
            formData.append('image', new Blob([]), 'empty.jpg');
        }

        const method = articleId ? 'PUT' : 'POST';
        const url = articleId ? `http://localhost:8080/customer/articles/${articleId}` : `http://localhost:8080/customer/articles`;

        try {
            const response = await fetch(url, {
                method,
                headers: {
                    "Authorization": `Bearer ${localStorage.getItem("token")}`,
                },
                body: formData,
            });

            if (!response.ok) {
                console.log(`Failed to save article. Status code: ${response.status}.`);
            }

            navigate('/dashboard/articles');

        } catch (error) {
            console.log(error);
        }
    };

    return(
        <Layout pageTitle="Dashboard" navigation={
            <> 
            <Link to='/dashboard'>Dashboard</Link> / <Link to='/dashboard/articles'>Articles</Link> / <span style={{color: 'red'}}>Manage</span>
            </>
        }>
        <div className={classes.container}>
            <div className={classes.innerContainer}>
                <div className={classes.header}>
                    <h2 className={classes.containerName}>{articleId ? 'Edit Article' : 'Create Article'}</h2>
                    <button className={classes.saveBtn} onClick={handleSave}>SAVE</button>
                </div>
                <div className={classes.inputs}>
                    <div className={classes.container1}>
                        <label htmlFor='title'>Title</label>
                        <input type='text' id='title' placeholder='Enter the title of your article' value={article.title} className={classes.inputTitle} onChange={(e) => setArticle({...article, title: e.target.value})}/>
                        <label htmlFor='description'>Description</label>
                        <textarea id='description' placeholder='Provide a short summary of your article' className={classes.inputDescription} value={article.description} onChange={(e) => setArticle({ ...article, description: e.target.value })}/>
                    </div>
                    <div className={classes.container2}>
                        <label htmlFor='content'>Content</label>
                        <textarea className={classes.inputContent} placeholder='Start writing your article...' id='content' value={article.content} onChange={(e) => setArticle({...article, content: e.target.value})}/>
                    </div>
                    <div className={classes.container3}>
                        <label>Visibility</label>
                        <div className={classes.visibilityContainer}>
                        {dormitories.map(dormitory => (
                            <div key={dormitory.id}  className={classes.visibilityItem}>
                                <input key={dormitory.id} 
                                    type='checkbox' 
                                    value={dormitory.id}
                                    checked={article.visibleInDormitories?.some(id => id === dormitory.id)}
                                    onChange={() => handleVisibilityChange(dormitory.id)}
                                /> {dormitory.name}
                            </div>
                            
                        ))}
                        </div>
                        <label htmlFor='image'>Image</label>
                        <FileUpload 
                            initialImage={article.image} 
                            onFileSelect={(image) => {
                                setArticle({...article, image: image });}}
                            onRemoveImage={() => {
                                setArticle({...article, image: null });
                            }}
                            />
                    </div>
                </div>
            </div>
        </div>
        </Layout>
    )
}