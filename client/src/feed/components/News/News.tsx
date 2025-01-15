import classes from './News.module.css'
import { FC } from "react"

interface NewsProps {
    title: string;
    description: string;
    data: string;
    image: string;
}

export const News: FC<NewsProps> = ({
    title, description, data, image,
}) => {
    return (
        <div className={classes.container}>
            <div className={classes.content}>
                <h1 className={classes.title}>{title}</h1>
                <p className={classes.description}>{description}</p>
                <p className={classes.data}>{data}</p>
            </div>
            <div className={classes.image}>
                <img src={image} />
            </div>
        </div>
    )
}