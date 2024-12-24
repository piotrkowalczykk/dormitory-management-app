import { Layout } from '../../components/Layout/Layout';
import classes from './Register.module.css';
import { Input } from '../../components/Input/Input';
import { useState } from 'react';
import { Button } from '../../components/Button/Buton';

export function Register(){

    const [formData, setFormData] = useState({
        email: "",
        password: "",
        firstName: "",
        lastName: "",
        gender: "FEMALE",
        dateOfBirth: ""
    });

    const [errors, setErrors] = useState({});

    const handleInputChange = (e) => {
        const {name, value} = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setErrors({});

        try {
            const response = await fetch("http://localhost:8080/auth/register", 
                {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify(formData),
                });

            if (response.ok){
                const data = await response.json();
                alert(`${data.message}`);
            } else {
                const errorData = await response.json();

                const errorMessages = errorData.message.split(", ");
                let newErrors = {};

                errorMessages.forEach((errorMessage) => {
                    const [field, message] = errorMessage.split(": ");
                    newErrors[field.trim()] = message.trim();
                });

                setErrors(newErrors);
            }

        } catch (error) {
            console.error("An unexpected error ocured", error);
        }
    }


    return(
        <Layout>
            <div className={classes.container}>
                <h1 className={classes.title}>Create Account</h1>
                <form onSubmit={handleSubmit}>
                    <div className={classes.name}>
                        <Input type="text" placeholder="First Name" value={formData.firstName} name="firstName"
                        error={errors.firstName} onChange={handleInputChange} />
                        <Input type="text" placeholder="Last Name" value={formData.lastName} name="lastName"
                        error={errors.lastName} onChange={handleInputChange} />
                    </div>
                        <Input type="date" placeholder="Date of Birth" value={formData.dateOfBirth} name="dateOfBirth"
                        error={errors.dateOfBirth} onChange={handleInputChange} />
                        <div className={classes.gender}>
                            <label>
                                <input type="radio" value="FEMALE" name="gender" checked={formData.gender === "FEMALE"} 
                                onChange={handleInputChange} /> Woman
                            </label>
                            <label>
                                <input type="radio" value="MALE" name="gender" checked={formData.gender === "MALE"} 
                                onChange={handleInputChange} /> Man
                            </label>
                        </div>
                        <Input type="text" placeholder="Email" value={formData.email} name="email"
                        error={errors.email} onChange={handleInputChange} />
                        <Input type="password" placeholder="Password" value={formData.password} name="password"
                        error={errors.password} onChange={handleInputChange} />
                        <Button type='submit'>Register</Button>
                </form>
            </div>
        </Layout>
    );
}