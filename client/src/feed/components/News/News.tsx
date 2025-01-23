import classes from './News.module.css'
import { Link } from 'react-router-dom'
import { FC } from "react"

interface NewsProps {
    title: string;
    description: string;
    content: string;
    data: string;
    image: string;
}

const slugify = (text: string) => {
    return text
        .toLocaleLowerCase()
        .replace(/[^a-z0-9]+/g, '-')
        .replace(/^-+|-+$/g, '');
}

const dateFormat = (dateString: string) => {
    const date = new Date(dateString);
    return date.toLocaleString({
        year: "numeric",
        month: "2-digit",
        day: "2-digit",
        hour: "2-digit",
        minute: "2-digit",
        second: "2-digit",
    });
}

export const News: FC<NewsProps> = ({
    title, description, data, image, content
}) => {
    const formatedDate = dateFormat(data);
    return (
        <div className={classes.container}>
            <div className={classes.content}>
                <h1 className={classes.title}>{title}</h1>
                <p className={classes.description}>{description}</p>
                <div className={classes.innerContainer}>
                <p className={classes.data}>{formatedDate}</p>
                <Link to={`/${slugify(title)}`} state={{ title, image, content, data }}>
                <button className={classes.btn}>Read more</button>
                </Link>
                </div>
            </div>
            <div className={classes.image}>
                <img src={image} />
            </div>
        </div>
    )
}