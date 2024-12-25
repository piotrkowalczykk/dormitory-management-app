import classes from './Button.module.css';
import { FC, ReactNode } from "react"

interface ButtonProps {
    type: 'submit';
    children?: ReactNode;
}
export const Button: FC<ButtonProps> = ({
    type, children,
}) => { return (
    <button className={classes.container} type={type}>{children}</button>
)}