import React, { useRef } from 'react';
import { Helmet } from 'react-helmet';
import { Fieldset } from 'primereact/fieldset';
import { Toast } from 'primereact/toast';
import { useHistory, useParams } from 'react-router';
import { FileUpload } from 'primereact/fileupload'
import { StudentService } from '../services/StudentService';

const UploadImage = () => {
    const { id } = useParams();
    const history = useHistory();
    const toast = useRef();

    const imageUploadHandler = ({ files }) => {
        const [file] = files;
        const fileReader = new FileReader();
        fileReader.onload = () => {
            onUpload(file);
        };
        fileReader.readAsDataURL(file);
    };

    const onUpload = (imageFile) => {
        const studentService = new StudentService();
        let formData = new FormData();
        formData.append('imageFile', imageFile);
        studentService.uploadImageByStudentId(id, formData)
            .then(res => {
                console.log(res.status)
                if (res.status === 201) {
                    toast.current.show({ severity: 'success', summary: 'Success', detail: 'Image has uploaded!' });
                }
            }).then(() => {
                setTimeout(() => {
                    history.push('/')
                }, 1000)
            }).catch(err => {
                toast.current.show({ severity: 'error', summary: 'Error', detail: 'A problem occurred!' });
            })
    };

    return (
        <>
            <Helmet>
                <title>Upload Image</title>
            </Helmet>
            <Toast ref={toast} />
            <div style={{ padding: 32 }}>
                <Fieldset legend="Upload Image">
                    <FileUpload
                        accept="image/*"
                        customUpload={true}
                        mode="basic"
                        uploadHandler={imageUploadHandler}
                        onUpload={onUpload} />
                </Fieldset>
            </div>
        </>
    )
}

export default UploadImage;