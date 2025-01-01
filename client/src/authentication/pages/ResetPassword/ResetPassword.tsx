import { useState } from 'react';
import { Box } from '../../components/Box/Box';
import { Input } from '../../components/Input/Input';
import { Layout } from '../../components/Layout/Layout';
import classes from './ResetPassword.module.css';
import { Button } from '../../components/Button/Button';
import { useNavigate } from 'react-router-dom';

export function ResetPassword(){

    const navigate = useNavigate();
    const [emailSent, setEmailSent] = useState(false);
    const [errors, setErrors] = useState({});
    const [formData, setFormData] = useState({
        email: "",
        emailCode: "",
        newPassword: "",
    });

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

        const url = e.target.name === "verifyCode" ? 
        "http://localhost:8080/auth/reset-password" : 
        "http://localhost:8080/auth/send-password-reset-code";

        try {
            console.log(JSON.stringify(formData));
            const response = await fetch(url, 
                {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify(formData),
                }
            );

            if (response.ok){
                setEmailSent(true);
                if(emailSent) navigate("/login");
            } else {
                let newErrors = {};
                const errorData = await response.json();

                console.log(errorData);
                
                const errorMessages = errorData.message.split(", ");
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
            {!emailSent ?
                <form className={classes.form}>
                <Box title='Change your password' text='We will send a verification code to this email address.'>
                    <Input type='text' placeholder='Email' name='email' value={formData.email} onChange={handleInputChange} error={errors.email} />
                    <div className={classes.btns}>
                        <Button type='button' onClick={handleSubmit} name='next'>Next</Button>
                        <Button type='button' onClick={() => navigate("/login")} name='return'>Return</Button>
                    </div>
                </Box>
                </form>
            :
                <form className={classes.form}>
                <Box title='Change your password' text='We will send a verification code to this email address.'>
                    <Input type='text' placeholder='Email code' name='emailCode' value={formData.emailCode} onChange={handleInputChange} error={errors.emailCode} />
                    <Input type='password' placeholder='New password' name='newPassword' value={formData.newPassword} onChange={handleInputChange} error={errors.newPassword} />
                    <div className={classes.btns}>
                        <Button type='button' onClick={handleSubmit} name='verifyCode'>Verify code</Button>
                        <Button type='button' onClick={handleSubmit} name='sendEmail'>Send email</Button>
                    </div>
                </Box>
                </form>
            }
            
        </Layout>
    );
}