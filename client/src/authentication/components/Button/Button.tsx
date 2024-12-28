import classes from './Button.module.css';
import { FC, ReactNode } from "react"

interface ButtonProps {
    type: 'submit' | 'button';
    name: string;
    children?: ReactNode;
    onClick?: () => void;
}
export const Button: FC<ButtonProps> = ({
    type, children, onClick, name,
}) => { return (
    <button className={classes.container} name={name} onClick={onClick} type={type}>{children}</button>
)}