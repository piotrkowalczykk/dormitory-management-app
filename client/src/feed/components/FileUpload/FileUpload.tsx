import { useState, useEffect } from "react";
import classes from "./FileUpload.module.css";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faImage } from '@fortawesome/free-solid-svg-icons';

export function FileUpload({ onFileSelect, initialImage, onRemoveImage }) {
    const [preview, setPreview] = useState(null);

    useEffect(() => {
        setPreview(initialImage || null);
    }, [initialImage]);

    const handleFileChange = (event) => {
        const selectedFile = event.target.files[0];
        if (!selectedFile) return;

        onFileSelect(selectedFile);

        const reader = new FileReader();
        reader.onloadend = () => setPreview(reader.result);
        reader.readAsDataURL(selectedFile);
    };

    const handleRemove = () => {
        setPreview(null);
        onRemoveImage();
        const inputElement = document.getElementById("fileInput");
        inputElement.value = "";
    };

    return (
        <div className={classes.container}>
            {preview && <img src={preview} className={classes.image} />}
            <input 
                type="file"
                id="fileInput"
                className={classes.input} 
                onChange={handleFileChange} 
            />
            <label htmlFor="fileInput" className={classes.content}>
                <FontAwesomeIcon icon={faImage} className={classes.icon}/>
            </label>
            {preview && (
                <button onClick={handleRemove} className={classes.removeButton}>
                    X
                </button>
            )}
        </div>
    );
}