import classes from './DormitoriesPanel.module.css'
import { useState, useEffect } from 'react';
import { Layout } from '../../../components/Layout/Layout';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faEye, faBuilding, faTrashAlt} from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';

export function DormitoriesPanel(){

    const [dormitories, setDormitories] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(()=>{
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
                    console.log('Failed to fetch dormitories.');
                }

                const data = await response.json();
                setDormitories(data);
            } catch (error){
                console.log(error);
            } finally {
                setIsLoading(false);
            }
    }
    fetchDormitories();
    }, []);

    const handleDelete = async (dormitoryId) => {
        try{
            const response = await fetch(`http://localhost:8080/customer/dormitories/${dormitoryId}`, {
                method: "DELETE",
                headers: {
                    "Authorization": `Bearer ${localStorage.getItem("token")}`,
                },
            });

            if(!response.ok){
                console.log(`Failed to delete article. Status code: ${response.status}.`);
            }
            
            setDormitories((prev) => prev.filter(dorm => dorm.id !== dormitoryId));
        } catch (error){
            console.log(error);
        }
    }

    return (
        <Layout pageTitle="Dashboard" navigation={
            <> 
            <Link to='/dashboard'>Dashboard</Link> / <span style={{color: 'red'}}>Dormitories</span>
            </>
        }>
            <div className={classes.container}>
                <div className={classes.innerContainer}>
                <div className={classes.header}>
                    <h2 className={classes.containerName}>Dormitories</h2>
                    <Link to='/dashboard/dormitories/manage' title='Add new article' className={classes.addArticleBtn}>+  <FontAwesomeIcon icon={faBuilding} className={classes.icon} /></Link>
                </div>
                {isLoading ? <p>Loading...</p> :
                <table className={classes.table}>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Address</th>
                            <th>phone</th>
                        </tr>
                    </thead>
                    <tbody>
                        {dormitories.map(dorm => (
                            <tr key={dorm.id} className={classes.rowSeparator}>
                                <td>{dorm.id}</td>
                                <td>{dorm.name}</td>
                                <td>{dorm.address}</td>
                                <td>{dorm.phone}</td>
                                <td> 
                                    <Link to='/dashboard/dormitories/manage' state={{id: dorm.id}} className={classes.manageBtn} title='Edit' style={{background: 'yellow'}} ><FontAwesomeIcon icon={faEdit} className={classes.manageIcon} /></Link>
                                    <button onClick={() => handleDelete(dorm.id)} className={classes.manageBtn} title='Delete' style={{background: 'red'}} ><FontAwesomeIcon icon={faTrashAlt} className={classes.manageIcon} /></button>
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