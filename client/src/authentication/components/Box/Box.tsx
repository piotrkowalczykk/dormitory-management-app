import { Button } from '../Button/Button'
import { FC, ReactNode } from "react"
import classes from './Box.module.css'
import { Link } from 'react-router-dom';

interface BoxProps{
    type: 'submit';
    btnName: string;
    title: string;
    text: string;
    link?: string;
    linkName?: string;
    children?: ReactNode;
}

export const Box: FC<BoxProps> = ({
    type, btnName, title, text, link, linkName, children
}) => {
    return(
        <div className={classes.container}>
            <h1 className={classes.title}>{title}</h1>
            {children}
            <Button type={type}>{btnName}</Button>
            <span className={classes.text}>{text} <Link to={link} className={classes.link}>{linkName}</Link></span>
        </div>
    )
}
