import { useState } from 'react';
import { Layout } from '../../components/Layout/Layout';
import classes from './EmailVerification.module.css';
import { useNavigate } from 'react-router-dom';
import { Box } from '../../components/Box/Box';
import { Input } from '../../components/Input/Input';

export function EmailVerification(){

    const navigate = useNavigate();
    const [errors, setErrors] = useState({});
    const [formData, setFormData] = useState(
        {
            emailCode: "",
            email: "",
        }
    );

    const handleInputChange = (e) => {
        const {name, value} = e.target;
        setFormData(
            {
            ...formData,
            [name]: value,
            }
        );
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setErrors({});

        try {
            const response = await fetch("http://localhost:8080/auth/validate-email", 
                {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify(formData),
                }
            );

            if (response.ok){
                const data = await response.json();
                alert(`${data.message}`);
                navigate("/");
            } else {
                let newErrors = {};
                const errorData = await response.json();
                const errorMessages = errorData.message.split(", ");

                if(errorMessages == "email: email is not verified"){
                    navigate("/email-verification");
                }

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
            <form onSubmit={handleSubmit} className={classes.form}>
                <Box type='submit' btnName='Verify email' title='Only one step left!'
                 text={<>Thanks for registering an account. <br /> Before we get started, we will need to verify your email.</>}>
                    <Input type="text" placeholder="Email code" value={formData.emailCode} name="emailCode"
                     error={errors.emailCode} onChange={handleInputChange} />
                </Box>
             </form>
        </Layout>
    );
}