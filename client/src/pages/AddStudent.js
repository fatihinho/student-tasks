import React, { useEffect, useRef, useState } from 'react';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { InputMask } from 'primereact/inputmask';
import { Fieldset } from 'primereact/fieldset';
import { Dropdown } from 'primereact/dropdown';
import { InputTextarea } from 'primereact/inputtextarea';
import { Helmet } from 'react-helmet';
import { StudentService } from '../services/StudentService';
import { Toast } from 'primereact/toast';

const AddStudent = () => {
    const [loading, setLoading] = useState(false);
    const toast = useRef();

    const [selectedCity, setSelectedCity] = useState(null);
    const [selectedCityId, setSelectedCityId] = useState(null);
    const [selectedDistrict, setSelectedDistrict] = useState(null);
    const [selectedDistrictId, setSelectedDistrictId] = useState(null);

    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [description, setDescription] = useState('');

    const [nameError, setNameError] = useState(false);
    const [surnameError, setSurnameError] = useState(false);
    const [phoneError, setPhoneError] = useState(false);
    const [cityError, setCityError] = useState(false);
    const [districtError, setDistrictError] = useState(false);

    const [cities, setCities] = useState([]);
    const [districts, setDistricts] = useState([]);

    const resetInputs = () => {
        setName('');
        setSurname('');
        setPhoneNumber('');
        setDescription('');
        setSelectedCity(null);
        setSelectedCityId(null);
        setSelectedDistrict(null);
        setSelectedDistrictId(null);
    }

    const onCityChange = (e) => {
        setSelectedCity(e.value);
        setSelectedCityId(e.value.id);
    }

    const onDistrictChange = (e) => {
        setSelectedDistrict(e.value);
        setSelectedDistrictId(e.value.id);
    }

    const onClickRegister = () => {
        if (name.trim().length > 0 && surname.trim().length > 0 && phoneNumber.length > 0 && selectedCity !== null && selectedDistrict !== null) {
            setLoading(true);
            const studentService = new StudentService();
            studentService.createStudent(name, surname, phoneNumber, selectedCityId, selectedDistrictId, description)
                .then(res => {
                    if (res.status === 201) {
                        toast.current.show({ severity: 'success', summary: 'Success', detail: 'Student has created!' });
                        setLoading(false);
                    }
                }).catch(err => {
                    toast.current.show({ severity: 'error', summary: 'Error', detail: 'A problem occurred!' });
                }).finally(() => {
                    resetInputs();
                });
        } else {
            if (name.trim().length <= 0) {
                setNameError(true);
                setTimeout(() => {
                    setNameError(false);
                }, 1500);
            } else if (surname.trim().length <= 0) {
                setSurnameError(true);
                setTimeout(() => {
                    setSurnameError(false);
                }, 1500);
            } else if (phoneNumber.length <= 0) {
                setPhoneError(true);
                setTimeout(() => {
                    setPhoneError(false);
                }, 1500);
            } else if (selectedCity === null) {
                setCityError(true);
                setTimeout(() => {
                    setCityError(false);
                }, 1500);
            } else if (selectedDistrict === null) {
                setDistrictError(true);
                setTimeout(() => {
                    setDistrictError(false);
                }, 1500);
            }
        }
    }

    useEffect(() => {
        const studentService = new StudentService();
        studentService.getAllCities()
            .then(res => {
                if (res.status === 200) {
                    setCities(res.data);
                }
            });

        studentService.getAllDistricts()
            .then(res => {
                if (res.status === 200) {
                    setDistricts(res.data);
                }
            });
    }, []);

    return (
        <>
            <Helmet>
                <title>Add Student</title>
            </Helmet>
            <Toast ref={toast} />
            <div style={{ padding: 32 }}>
                <Fieldset legend="Add Student">
                    <div className="p-fluid p-formgrid p-grid">
                        <div className="p-field p-col-12 p-md-4">
                            <label htmlFor="name">Name</label>
                            <InputText
                                className={nameError ? "p-invalid p-d-block" : ""}
                                id="name"
                                type="text"
                                value={name}
                                onChange={(e) => setName(e.target.value)}
                            />
                        </div>
                        <div className="p-field p-col-12 p-md-4">
                            <label htmlFor="surname">Surname</label>
                            <InputText
                                className={surnameError ? "p-invalid p-d-block" : ""}
                                id="surname"
                                type="text"
                                value={surname}
                                onChange={(e) => setSurname(e.target.value)}
                            />
                        </div>
                        <div className="p-field p-col-12 p-md-4">
                            <label htmlFor="phoneNumber">Phone Number</label>
                            <InputMask
                                className={phoneError ? "p-invalid p-d-block" : ""}
                                id="phoneNumber"
                                mask="(999) 999-9999"
                                value={phoneNumber}
                                placeholder="(999) 999-9999"
                                onChange={(e) => setPhoneNumber(e.value)}
                            ></InputMask>
                        </div>
                        <div className="p-field p-col-12 p-md-6">
                            <label htmlFor="city">City</label>
                            <Dropdown
                                className={cityError ? "p-invalid" : ""}
                                inputId="city"
                                value={selectedCity}
                                options={cities}
                                onChange={onCityChange}
                                placeholder="Select"
                                optionLabel="name"
                            />
                        </div>
                        <div className="p-field p-col-12 p-md-6">
                            <label htmlFor="district">District</label>
                            <Dropdown
                                className={districtError ? "p-invalid" : ""}
                                inputId="district"
                                value={selectedDistrict}
                                options={selectedCityId !== null ? districts.filter(i => i.city.id === selectedCityId) : null}
                                onChange={onDistrictChange}
                                placeholder="Select"
                                optionLabel="name"
                            />
                        </div>
                        <div className="p-field p-col-12">
                            <label htmlFor="description">Description</label>
                            <InputTextarea
                                id="description"
                                type="text"
                                rows="4"
                                value={description}
                                onChange={(e) => setDescription(e.target.value)}
                            />
                        </div>
                    </div>
                    <Button style={{
                        marginBottom: 16,
                        marginLeft: 4,
                        float: 'right',
                        zIndex: 1
                    }} label="Register" icon="pi pi-plus" loading={loading} onClick={onClickRegister} />
                </Fieldset>
            </div>
        </>
    )
}

export default AddStudent;