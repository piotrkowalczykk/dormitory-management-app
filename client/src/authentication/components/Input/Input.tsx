import { ChangeEvent, FC } from "react"
import classes from './Input.module.css';

interface InputProps {
    type: 'text' | 'email' | 'password' | 'date' | 'radio';
    placeholder: string;
    value: string
    name: string;
    error: string;
    onChange: (e: ChangeEvent<HTMLInputElement>) => void;
}

export const Input: FC<InputProps> = ({
    type,
    placeholder,
    value,
    name,
    error,
    onChange,
}) => {
    return(
        <div className={classes.container}>
        <input className={classes.input} type={type} value={value}
         name={name} placeholder={placeholder} onChange={onChange} />
        <p className={classes.error}>{error}</p>
        </div>
    )
}