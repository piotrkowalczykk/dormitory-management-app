import classes from './ManageStudents.module.css'
import { useState, useEffect } from 'react';
import { Layout } from '../../../components/Layout/Layout';
import { Link, useLocation, useNavigate } from 'react-router-dom';

export function ManageStudents(){

    const location = useLocation();
    const studentId = location.state?.id;
    const navigate = useNavigate();

    const [student, setStudent] = useState({
        id: "",
        email: "",
        studentNumber: "",
        roomId: "",
        roomNumber: "",
        dormitoryId: '',
        dormitoryName: ''
    });

    const [dormitories, setDormitories] = useState([]);
    const [rooms, setRooms] = useState([]);

    useEffect(()=>{
            fetchDormitories();
            studentId && fetchStudent();
        }, []);

    const handleSave = async () => {

        const method = studentId ? 'PUT' : 'POST';
        const url = studentId ? `http://localhost:8080/customer/students/${studentId}` : `http://localhost:8080/customer/students`;

        try {
            const response = await fetch(url, {
                method,
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${localStorage.getItem("token")}`,
                    
                },
                body: JSON.stringify(student)
            });

            if (!response.ok) {
                console.log(`Failed to save student. Status code: ${response.status}.`);
            }

            navigate('/dashboard/students');

        } catch (error) {
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
            setDormitories(data);
        } catch (error){
            console.log(error);
        }
    }

    const fetchRooms = async (dormitoryId) => {
        try {
            const response = await fetch(`http://localhost:8080/customer/rooms/${dormitoryId}`, {
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
            setRooms(data);
        } catch (error){
            console.log(error);
        }
    }

    const fetchStudent = async () => {
        try {
            const response = await fetch(`http://localhost:8080/customer/students/${studentId}`, {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": `Bearer ${localStorage.getItem("token")}`,
                    },
            });

            if(!response.ok){
                console.log('Failed to fetch student.');
            }

            const data = await response.json();
            if(data.dormitoryId){
                fetchRooms(data.dormitoryId);
            }
            setStudent(data);
            
        } catch (error){
            console.log(error);
        }
    }

    return (
        <Layout pageTitle="Dashboard" navigation={
                    <> 
                    <Link to='/dashboard'>Dashboard</Link> / <Link to='/dashboard/students'>Students</Link> / <span style={{color: 'red'}}>Manage</span>
                    </>
                }>
                <div className={classes.container}>
                    <div className={classes.innerContainer}>
                        <div className={classes.header}>
                            <h2 className={classes.containerName}>{studentId ? 'Edit Student' : 'Create Student'}</h2>
                            <button className={classes.saveBtn} onClick={handleSave}>SAVE</button>
                        </div>
                        <div className={classes.inputs}>
                            <div className={classes.container12}>
                                <div className={classes.container1}>
                                    <label htmlFor='dormitory'>Dormitory</label>
                                    <select 
                                        id='dormitory' 
                                        value={student.dormitoryId || ""} 
                                        onChange={(e) => {
                                            const selectedDorm = dormitories.find(dorm => dorm.id === Number(e.target.value));
                                            fetchRooms(selectedDorm.id);
                                            setStudent({
                                                ...student,
                                                dormitoryId: selectedDorm ? selectedDorm.id : "",
                                                dormitoryName: selectedDorm ? selectedDorm.name : ""
                                        });
                                    }}>
                                    <option value="">Select Dormitory</option>
                                        {dormitories.map(dorm => (
                                        <option key={dorm.id} value={dorm.id}>{dorm.name}</option>
                                            ))}
                                    </select>

                                </div>
                                <div className={classes.container2}>
                                    
                                    <label htmlFor='dormitory'>Room</label>
                                    <select 
                                        id='room' 
                                        value={student.roomId || ""} 
                                        onChange={(e) => {
                                            const selectedRoom = rooms.find(room => room.id === Number(e.target.value));
                                            setStudent({
                                                ...student,
                                                roomId: selectedRoom ? selectedRoom.id : "",
                                                roomNumber: selectedRoom ? selectedRoom.number : ""
                                        });
                                        console.log(student);
                                    }}>
                                    <option value="">Select Room</option>
                                        {rooms.map(room => (
                                        <option key={room.id} value={room.id}>{room.number}</option>
                                            ))}
                                    </select>

                                </div>
                            </div>
                            <div className={classes.container34}>
                                <div className={classes.container1}>
                                    <label htmlFor='email'>Email</label>
                                    <input type='text' id='email' placeholder='Email' value={student.email}  onChange={(e) => setStudent({...student, email: e.target.value})}/>
                                </div>
                                <div className={classes.container4}>
                                    <label htmlFor='studentNumber'>Student Number</label>
                                    <input type='text' placeholder='Student Number' id='studentNumber' value={student.studentNumber} onChange={(e) => setStudent({...student, studentNumber: e.target.value})}/>
                                </div>
                            </div>
                        </div>
                        <div>----- Or -----</div>
                        <div>Upload CSV file soon...</div>
                    </div>
                </div>
        </Layout>
    )
}