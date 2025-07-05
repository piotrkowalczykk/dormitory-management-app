import classes from './ManageRooms.module.css'
import { useState, useEffect } from 'react';
import { Layout } from '../../../components/Layout/Layout';
import { Link, useLocation, useNavigate } from 'react-router-dom';

export function ManageRooms(){

    const location = useLocation();
    const roomId = location.state?.id;
    const navigate = useNavigate();

    const [room, setRoom] = useState({
        id: "",
        number: "",
        capacity: "",
        floor: "",
        type: "",
        dormitoryId: '',
        dormitoryName: ''
    });


    const [dormitories, setDormitories] = useState([]);

    useEffect(()=>{
            fetchDormitories();
            roomId && fetchRoom();
        }, []);

    const handleSave = async () => {

        const method = roomId ? 'PUT' : 'POST';
        const url = roomId ? `http://localhost:8080/customer/rooms/${roomId}` : `http://localhost:8080/customer/rooms`;

        try {
            const response = await fetch(url, {
                method,
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${localStorage.getItem("token")}`,
                    
                },
                body: JSON.stringify(room)
            });

            if (!response.ok) {
                console.log(`Failed to save room. Status code: ${response.status}.`);
            }

            navigate('/dashboard/rooms');

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

    const fetchRoom = async () => {
        try {
            const response = await fetch(`http://localhost:8080/customer/rooms/${roomId}`, {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": `Bearer ${localStorage.getItem("token")}`,
                    },
            });

            if(!response.ok){
                console.log('Failed to fetch room.');
            }

            const data = await response.json();
            setRoom(data);
            console.log(data);
            
        } catch (error){
            console.log(error);
        }
    }

    return (
        <Layout pageTitle="Dashboard" navigation={
                    <> 
                    <Link to='/dashboard'>Dashboard</Link> / <Link to='/dashboard/rooms'>Rooms</Link> / <span style={{color: 'red'}}>Manage</span>
                    </>
                }>
                <div className={classes.container}>
                    <div className={classes.innerContainer}>
                        <div className={classes.header}>
                            <h2 className={classes.containerName}>{roomId ? 'Edit Room' : 'Create Room'}</h2>
                            <button className={classes.saveBtn} onClick={handleSave}>SAVE</button>
                        </div>
                        <div className={classes.inputs}>
                            <div className={classes.container12}>
                                <div className={classes.container1}>
                                    <label htmlFor='dormitory'>Dormitory</label>
                                    <select 
                                        id='dormitory' 
                                        value={room.dormitoryId || ""} 
                                        onChange={(e) => {
                                            const selectedDorm = dormitories.find(dorm => dorm.id === Number(e.target.value));
                                            setRoom({
                                                ...room,
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
                                    <label htmlFor='number'>Number</label>
                                    <input type="text" id="number" placeholder="Number" value={room.number} onChange={(e) => setRoom({...room, number: e.target.value})} />
                                </div>
                            </div>
                            <div className={classes.container34}>
                                <div className={classes.container3}>
                                    <label htmlFor='capacity'>Capacity</label>
                                    <input type='text' id='Capacity' placeholder='Capacity' value={room.capacity}  onChange={(e) => setRoom({...room, capacity: e.target.value})}/>
                                </div>
                                <div className={classes.container4}>
                                    <label htmlFor='floor'>Floor</label>
                                    <input type='text' placeholder='Floor' id='floor' value={room.floor} onChange={(e) => setRoom({...room, floor: e.target.value})}/>
                                </div>
                                <div className={classes.container4}>
                                    <label htmlFor='type'>Type</label>
                                    <input type='text' placeholder='Type' id='type' value={room.type} onChange={(e) => setRoom({...room, type: e.target.value})}/>
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