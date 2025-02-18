import classes from './UserPreview.module.css'

export function UserPreview({firstName, lastName, avatar}){
    return(
        <div className={classes.container}>
            <img src="https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg" className={classes.userAvatar} />
            <p className={classes.userName}>{firstName} {lastName}</p>
        </div>
    )
}