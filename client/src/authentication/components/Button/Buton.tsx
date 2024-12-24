import classes from './Button.module.css';
import { FC } from "react"

interface ButtonProps {
    type: 'submit';
}
export const Button: FC<ButtonProps> = ({
    type, children,
}) => { return (
    <button className={classes.container} type={type}>{children}</button>
)}