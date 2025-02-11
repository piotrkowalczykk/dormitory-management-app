import { useState, useEffect } from "react";
import classes from "./FileUpload.module.css";

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

            {preview && (
                <button onClick={handleRemove} className={classes.removeButton}>
                    X
                </button>
            )}
        </div>
    );
}
