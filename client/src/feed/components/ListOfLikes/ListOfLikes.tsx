import { useEffect, useState } from 'react'
import classes from './ListOfLikes.module.css'
import { UserPreview } from '../UserPreview/UserPreview';

export function ListOfLikes({closeModal, postId}){

    const [isLoading, setIsLoading] = useState(true);
    const [users, setUsers] = useState([]);

    useEffect(()=>{
        const fetchUsers = async () => {
            try{
                const response = await fetch(`http://localhost:8080/feed/posts/${postId}/likes`, {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": `Bearer ${localStorage.getItem("token")}`,
                    },
                })

                if(response.ok){
                    const data = await response.json();
                    setUsers(data);
                } else {
                    console.log(response.status);
                }
            } catch(error){
                console.log(error);
            } finally {
                setIsLoading(false);
            } 
        };
        fetchUsers();
    }, []);

    const handleLike = async () => {
        try{
            const response = await fetch(`http://localhost:8080/feed/posts/${postId}/like`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${localStorage.getItem("token")}`,
                },
            })

            if(response.ok){
                const data = await response.json();
            } else {
                console.log(response.status);
            }
        } catch (error){
            console.log(error);
        } finally {
            setIsLoading(false);
        }
    }

    return(
        <div className={classes.overlay} onClick={closeModal}>
            <div className={classes.container} onClick={(e) => e.stopPropagation()}>
                <button className={classes.closeBtn} onClick={closeModal}>X</button>
                <div className={classes.listOfUsers}>
                    {isLoading ? <p>Loading...</p> :
                        users.map(user => (
                            <UserPreview 
                                key={user.id}
                                avatar={user.avatar}
                                firstName={user.firstName}
                                lastName={user.lastName}
                            />
                        ))
                    }
                </div>
            </div>
        </div>
    )
}