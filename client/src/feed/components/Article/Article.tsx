import classes from './Article.module.css'
import { Link } from 'react-router-dom'
import { FC } from "react"

interface ArticleProps {
    id: string;
    title: string;
    description: string;
    data: string;
    image: string;
}

const slugify = (text: string) => {
    return text
        .toLocaleLowerCase()
        .replace(/[^a-z0-9]+/g, '-')
        .replace(/^-+|-+$/g, '');
}

export const Article: FC<ArticleProps> = ({
    title, description, data, image, id
}) => {

    return (
        <div className={classes.container}>
            <div className={classes.content}>
                <h1 className={classes.title}>{title}</h1>
                <p className={classes.description}>{description}</p>
                <div className={classes.innerContainer}>
                <p className={classes.data}>{data}</p>
                <Link to={`/${slugify(title)}`} state={{id}}>
                <button className={classes.btn}>Read more</button>
                </Link>
                </div>
            </div>
            <div className={classes.image}>
                {image && <img src={`http://localhost:8080/api/uploads/${image}`} />}
            </div>
        </div>
    )
}