import { FC, ReactNode } from "react"
import classes from './Box.module.css'
import { Link } from 'react-router-dom';

interface BoxProps{
    title: string;
    text: string;
    link?: string;
    linkName?: string;
    children?: ReactNode;
}

export const Box: FC<BoxProps> = ({
    title, text, link, linkName, children
}) => {
    return(
        <div className={classes.container}>
            <h1 className={classes.title}>{title}</h1>
            {children}
            <span className={classes.text}>{text} <Link to={link} className={classes.link}>{linkName}</Link></span>
        </div>
    )
}
