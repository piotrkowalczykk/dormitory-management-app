import { useState } from "react";
import classes from "./FileUpload.module.css";

export function FileUpload() {
    const [file, setFile] = useState(null);
    const [preview, setPreview] = useState(null);

    const handleFileChange = (event) => {
        const selectedFile = event.target.files[0];
        setFile(selectedFile);

        if (selectedFile) {
            const reader = new FileReader();
            reader.onloadend = () => setPreview(reader.result);
            reader.readAsDataURL(selectedFile);
        } else {
            setPreview(null);
        }
    };

    return (
        <div className={classes.container}>
            <div className={classes.imageOutline}>
                {preview && <img src={preview} className={classes.image} />}
            </div>

            <input 
                type="file"
                id="fileInput"
                className={classes.input} 
                onChange={handleFileChange} 
            />

            <label htmlFor="fileInput" className={classes.uploadButton}>
                Upload Image
            </label>
        </div>
    );
}
