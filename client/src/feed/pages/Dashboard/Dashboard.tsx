import classes from './Dashboard.module.css'
import { Layout } from '../../components/Layout/Layout'
import { Link } from 'react-router-dom'
import { ManageCard } from '../../components/ManageCard/ManageCard'
import { useEffect, useState } from 'react'
export function Dashboard(){

    const [articlesCount, setArticlesCount] = useState(0);
    const [dormitoriesCount, setDormitoriesCount] = useState(0);
    const [studentsCount, setStudentsCount] = useState(0);
    const [roomsCount, setRoomsCount] = useState(0);

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
                    setArticlesCount(data.length);
                } catch (error){
                    console.log(error);
                }
            }

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
                    setDormitoriesCount(data.length);
                } catch (error){
                    console.log(error);
                }
            }

            
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
                    setStudentsCount(data.length);
                } catch (error){
                    console.log(error);
                }
            }

            const fetchRooms = async () => {
                try {
                    const response = await fetch("http://localhost:8080/customer/rooms", {
                            method: "GET",
                            headers: {
                                "Content-Type": "application/json",
                                "Authorization": `Bearer ${localStorage.getItem("token")}`,
                            },
                    });
    
                    if(!response.ok){
                        console.log('Failed to fetch rooms.');
                    }
    
                    const data = await response.json();
                    setRoomsCount(data.length);
                } catch (error){
                    console.log(error);
                }
            }

        fetchRooms();
        fetchStudents();
        fetchArticles();
        fetchDormitories();
    },[]);

    return (
        <Layout pageTitle="Dashboard" navigation={
            <> 
            / <Link to='/dashboard'><span style={{ color: 'red' }}>Dashboard</span></Link>
            </>
        }>
            <div className={classes.container}>
                <ManageCard bgColor='#ff0000' iconName='faNewspaper' counter={articlesCount} name='Articles' link='articles' />
                <ManageCard bgColor='#4cff05' iconName='faBuilding' counter={dormitoriesCount} name='Dormitories' link='dormitories' />
                <ManageCard bgColor='#0d85fc' iconName='faUsers' counter={studentsCount} name='Students' link='students' />
                <ManageCard bgColor='#f5ee25' iconName='faBed' counter={roomsCount} name='Rooms' link='rooms' />
            </div>
        </Layout>
    )
}