import classes from './ManageDormitory.module.css'
import { useState, useEffect } from 'react';
import { Layout } from '../../../components/Layout/Layout';
import { Link, useLocation, useNavigate } from 'react-router-dom';

export function ManageDormitory(){

    const location = useLocation();
    const dormitoryId = location.state?.id;
    const navigate = useNavigate();

    const [dormitory, SetDormitory] = useState({
        id: "",
        name: "",
        address: "",
        phone: "",
    });

    useEffect(()=>{
            dormitoryId && fetchDormitory();
        }, []);

    const handleSave = async () => {

        const method = dormitoryId ? 'PUT' : 'POST';
        const url = dormitoryId ? `http://localhost:8080/customer/dormitories/${dormitoryId}` : `http://localhost:8080/customer/dormitories`;

        try {
            const response = await fetch(url, {
                method,
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${localStorage.getItem("token")}`,
                    
                },
                body: JSON.stringify(dormitory)
            });

            if (!response.ok) {
                console.log(`Failed to save article. Status code: ${response.status}.`);
            }

            navigate('/dashboard/dormitories');

        } catch (error) {
            console.log(error);
        }
    }

    const fetchDormitory = async () => {
        try {
            const response = await fetch(`http://localhost:8080/customer/dormitories/${dormitoryId}`, {
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
            SetDormitory(data);
        } catch (error){
            console.log(error);
        }
    }

    return (
        <Layout pageTitle="Dashboard" navigation={
                    <> 
                    <Link to='/dashboard'>Dashboard</Link> / <Link to='/dashboard/dormitories'>Dormitories</Link> / <span style={{color: 'red'}}>Manage</span>
                    </>
                }>
                <div className={classes.container}>
                    <div className={classes.innerContainer}>
                        <div className={classes.header}>
                            <h2 className={classes.containerName}>{dormitoryId ? 'Edit Dormitory' : 'Create Dormitory'}</h2>
                            <button className={classes.saveBtn} onClick={handleSave}>SAVE</button>
                        </div>
                        <div className={classes.inputs}>
                            <div className={classes.container1}>
                                <label htmlFor='name'>Name</label>
                                <input type='text' id='name' placeholder='Name your dormitory' value={dormitory.name}  onChange={(e) => SetDormitory({...dormitory, name: e.target.value})}/>
                            </div>
                            <div className={classes.container2}>
                                <label htmlFor='phone'>Phone</label>
                                <input type='text' placeholder='Phone number' id='phone' value={dormitory.phone} onChange={(e) => SetDormitory({...dormitory, phone: e.target.value})}/>
                            </div>
                            <div className={classes.container3}>
                                <label htmlFor='address'>Address</label>
                                <input type='text' id='address' placeholder='Provide an address' value={dormitory.address} onChange={(e) => SetDormitory({ ...dormitory, address: e.target.value })}/>
                            </div>
                        </div>
                        <div>----- Or -----</div>
                        <div>Upload CSV file soon...</div>
                    </div>
                </div>
        </Layout>
    )
}