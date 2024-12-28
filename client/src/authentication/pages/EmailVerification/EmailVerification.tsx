import { useState } from 'react';
import { Layout } from '../../components/Layout/Layout';
import classes from './EmailVerification.module.css';
import { useNavigate } from 'react-router-dom';
import { Box } from '../../components/Box/Box';
import { Input } from '../../components/Input/Input';
import { Button } from '../../components/Button/Button';

export function EmailVerification(){

    const navigate = useNavigate();
    const [errors, setErrors] = useState({});
    const [formData, setFormData] = useState(
        {
            emailCode: "",
            email: localStorage.getItem("email"),
        }
    );
    const [formData2, setFormData2] = useState(
        {
            email: localStorage.getItem("email"),
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
            const url = e.target.name === "verifyEmail"
            ? "http://localhost:8080/auth/validate-email"
            : "http://localhost:8080/auth/resend-email-verification-code";

            const response = await fetch(url, 
                {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify(e.target.name === "verifyEmail" ? formData : formData2),
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
                    newErrors["emailCode"] = message.trim();
                });

                setErrors(newErrors);
            }
        } catch (error) {
            console.error("An unexpected error ocured", error);
        }
    }

    return(
        <Layout>
            <form className={classes.form}>
                <Box title='Only one step left!'
                 text={<>Thanks for registering an account. <br /> Before we get started, we will need to verify your email.</>}>
                    <Input type="text" placeholder="Email code" value={formData.emailCode} name="emailCode"
                     error={errors.emailCode} onChange={handleInputChange} />
                     <div className={classes.btns}>
                        <Button type='button' onClick={handleSubmit} name="verifyEmail">Verify email</Button>
                        <Button type='button' onClick={handleSubmit} name="sendEmail">Send email</Button>
                     </div>
                </Box>
             </form>
        </Layout>
    );
}