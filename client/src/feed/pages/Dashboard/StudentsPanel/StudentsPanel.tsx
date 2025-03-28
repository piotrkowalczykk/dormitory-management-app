
import { useState, useEffect } from 'react'
import { Layout } from '../../../components/Layout/Layout'
import classes from './StudentsPanel.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faEye, faTrashAlt, faUser} from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';

export function StudentsPanel(){

    const [students, setStudents] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(()=>{
        const fetchStudents = async () => {
            try {
                const response = await fetch("http://localhost:8080/customer/students", {
                        method: "GET",
                        headers: {
                            "Content-Type": "application/json",
                            "Authorization": `Bearer ${localStorage.getItem("token")}`,
                        },
                });

                if(!response.ok){
                    console.log('Failed to fetch students.');
                }

                const data = await response.json();
                setStudents(data);
            } catch (error){
                console.log(error);
            } finally {
                setIsLoading(false);
            }
    }
    fetchStudents();
    }, []);

    const handleDelete = async (studentId) => {
        try{
            const response = await fetch(`http://localhost:8080/customer/students/${studentId}`, {
                method: "DELETE",
                headers: {
                    "Authorization": `Bearer ${localStorage.getItem("token")}`,
                },
            });

            if(!response.ok){
                console.log(`Failed to delete student. Status code: ${response.status}.`);
            }
            
            setStudents((prev) => prev.filter(student => student.id !== studentId));
        } catch (error){
            console.log(error);
        }
    }

    const slugify = (text: string) => {
        return text
            .toLocaleLowerCase()
            .replace(/[^a-z0-9]+/g, '-')
            .replace(/^-+|-+$/g, '');
    }

    return(
        <Layout pageTitle="Dashboard" navigation={
            <> 
            <Link to='/dashboard'>Dashboard</Link> / <span style={{color: 'red'}}>Students</span>
            </>
        }>
            <div className={classes.container}>
                <div className={classes.innerContainer}>
                <div className={classes.header}>
                    <h2 className={classes.containerName}>Students</h2>
                    <Link to='/dashboard/students/manage' title='Add new student' className={classes.addArticleBtn}>+  <FontAwesomeIcon icon={faUser} className={classes.icon} /></Link>
                </div>
                {isLoading ? <p>Loading...</p> :
                <table className={classes.table}>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Image</th>
                            <th>Email</th>
                            <th>Room</th>
                            <th>Student Number</th>
                            <th>Dormitory</th>
                            <th>Manage</th>
                        </tr>
                    </thead>
                    <tbody>
                        {students.map(student => (
                            <tr key={student.id} className={classes.rowSeparator}>
                                <td>{student.id}</td>
                                <td><img className={classes.imageList} src='/default-avatar.jpg' /></td>
                                <td>{student.email}</td>
                                <td>{student.roomNumber}</td>
                                <td>{student.studentNumber}</td>
                                <td>{student.dormitoryName}</td>
                                <td>
                                    <Link to='' state={{id: student.id}} className={classes.manageBtn} title='View profile' style={{background: 'blueviolet'}}><FontAwesomeIcon icon={faEye} className={classes.manageIcon} /></Link>
                                    <Link to='/dashboard/students/manage' state={{id: student.id}} className={classes.manageBtn} title='Edit' style={{background: 'yellow'}} ><FontAwesomeIcon icon={faEdit} className={classes.manageIcon} /></Link>
                                    <button onClick={() => handleDelete(student.id)} className={classes.manageBtn} title='Delete' style={{background: 'red'}} ><FontAwesomeIcon icon={faTrashAlt} className={classes.manageIcon} /></button>
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
