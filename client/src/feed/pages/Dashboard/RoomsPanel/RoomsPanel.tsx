import { useState, useEffect } from 'react';
import { Layout } from '../../../components/Layout/Layout';
import classes from './RoomsPanel.module.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faBed, faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';
import { ErrorAlert } from '../../../components/ErrorAlert/ErrorAlert';

export function RoomsPanel() {
    const [error, setError] = useState({
        statusCode: '',
        message: '',
        timestamp: ''
    });
    const [isErrorOpen, setIsErrorOpen] = useState(false);
    const [rooms, setRooms] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const fetchRooms = async () => {
            try {
                const response = await fetch("http://localhost:8080/customer/rooms", {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": `Bearer ${localStorage.getItem("token")}`,
                    },
                });

                if (!response.ok) {
                    console.log('Failed to fetch rooms.');
                }

                const data = await response.json();
                setRooms(data);
            } catch (error) {
                console.log(error);
            } finally {
                setIsLoading(false);
            }
        };
        fetchRooms();
    }, []);

    const handleDelete = async (roomId) => {
        try {
            const response = await fetch(`http://localhost:8080/customer/rooms/${roomId}`, {
                method: "DELETE",
                headers: {
                    "Authorization": `Bearer ${localStorage.getItem("token")}`,
                },
            });

            if (response.ok) {
                setRooms((prev) => prev.filter(room => room.id !== roomId));
            } else {
                const errorData = await response.json();
                setError(errorData);
                setIsErrorOpen(true);
            }
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <Layout pageTitle="Dashboard" navigation={
            <>
                <Link to='/dashboard'>Dashboard</Link> / <span style={{ color: 'red' }}>Rooms</span>
            </>
        }>
            <div className={classes.container}>
                {isErrorOpen && error.message && (
                    <ErrorAlert
                        statusCode={error.statusCode}
                        message={error.message}
                        timestamp={error.timestamp}
                        closeModal={() => setIsErrorOpen(false)}
                    />
                )}

                <div className={classes.innerContainer}>
                    <div className={classes.header}>
                        <h2 className={classes.containerName}>Rooms</h2>
                        <Link to='/dashboard/rooms/manage' title='Add new room' className={classes.addArticleBtn}>
                            + <FontAwesomeIcon icon={faBed} className={classes.icon} />
                        </Link>
                    </div>
                    {isLoading ? <p>Loading...</p> :
                        <table className={classes.table}>
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Number</th>
                                    <th>Dormitory</th>
                                    <th>Capacity</th>
                                    <th>Floor</th>
                                    <th>Type</th>
                                    <th>Manage</th>
                                </tr>
                            </thead>
                            <tbody>
                                {rooms.map(room => (
                                    <tr key={room.id} className={classes.rowSeparator}>
                                        <td>{room.id}</td>
                                        <td>{room.number}</td>
                                        <td>{room.dormitory.name}</td>
                                        <td>{room.capacity}</td>
                                        <td>{room.floor}</td>
                                        <td>{room.type}</td>
                                        <td>
                                            <Link to='/dashboard/rooms/manage' state={{ id: room.id }} className={classes.manageBtn} title='Edit' style={{ background: 'yellow' }}>
                                                <FontAwesomeIcon icon={faEdit} className={classes.manageIcon} />
                                            </Link>
                                            <button onClick={() => handleDelete(room.id)} className={classes.manageBtn} title='Delete' style={{ background: 'red' }}>
                                                <FontAwesomeIcon icon={faTrashAlt} className={classes.manageIcon} />
                                            </button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    }
                </div>
            </div>
        </Layout>
    );
}
