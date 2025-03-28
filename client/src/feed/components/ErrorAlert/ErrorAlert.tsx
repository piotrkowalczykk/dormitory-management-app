import classes from './ErrorAlert.module.css'

export function ErrorAlert({closeModal, statusCode, message, timestamp}){
    return(
        <div className={classes.overlay} onClick={closeModal}>
            <div className={classes.container}>
                <h1 className={classes.title}>ERROR !!!</h1>
                <p className={classes.text}>Status code: {statusCode}</p>
                <p className={classes.text}>Message: {message}</p>
                <p className={classes.text}>Timestamp: {timestamp}</p>
            </div>
        </div>
    )
}