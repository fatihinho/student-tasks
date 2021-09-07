import React, { useEffect, useRef, useState } from 'react';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { InputMask } from 'primereact/inputmask';
import { Dropdown } from 'primereact/dropdown';
import { InputTextarea } from 'primereact/inputtextarea';
import { Helmet } from 'react-helmet';
import { useLocation } from 'react-router-dom';
import { StudentService } from '../services/StudentService';
import { Fieldset } from 'primereact/fieldset';
import { Toast } from 'primereact/toast';
import { useHistory, useParams } from 'react-router';

const UpdateStudent = () => {
    const { id } = useParams();
    const location = useLocation();
    const history = useHistory();
    const toast = useRef();

    const [loading, setLoading] = useState(false);

    const [selectedCity, setSelectedCity] = useState(null);
    const [selectedCityId, setSelectedCityId] = useState(null);
    const [selectedDistrict, setSelectedDistrict] = useState(null);
    const [selectedDistrictId, setSelectedDistrictId] = useState(null);

    const [cities, setCities] = useState([]);
    const [districts, setDistricts] = useState([]);

    const [nameError, setNameError] = useState(false);
    const [surnameError, setSurnameError] = useState(false);
    const [phoneError, setPhoneError] = useState(false);
    const [cityError, setCityError] = useState(false);
    const [districtError, setDistrictError] = useState(false);

    const [name, setName] = useState(location.state.name);
    const [surname, setSurname] = useState(location.state.surname);
    const [phoneNumber, setPhoneNumber] = useState(location.state.phoneNumber);
    const [description, setDescription] = useState(location.state.description);

    const city = location.state.city;
    const district = location.state.district;

    const onCityChange = (e) => {
        setSelectedDistrict(null);
        setSelectedDistrictId(null);
        setSelectedCity(e.value);
        setSelectedCityId(e.value.id);
    }

    const onDistrictChange = (e) => {
        setSelectedDistrict(e.value);
        setSelectedDistrictId(e.value.id);
    }

    const onClickUpdate = () => {
        if (name.trim().length > 0 && surname.trim().length > 0 && phoneNumber.length > 0 && selectedCity !== null && selectedDistrict !== null) {
            setLoading(true);
            const studentService = new StudentService();
            studentService.updateStudentById(id, name, surname, phoneNumber, selectedCityId, selectedDistrictId, description)
                .then(res => {
                    if (res.status === 200) {
                        toast.current.show({ severity: 'success', summary: 'Success', detail: 'Student has updated!' });
                        setLoading(false);
                    }
                }).then(() => {
                    setTimeout(() => {
                        history.push('/')
                    }, 1000)
                }).catch(err => {
                    toast.current.show({ severity: 'error', summary: 'Error', detail: 'A problem occurred!' });
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
        setSelectedCity(city);
        setSelectedCityId(city.id);
        setSelectedDistrict(district);
        setSelectedDistrictId(district.id);
        const studentService = new StudentService();
        studentService.getAllCities()
            .then(res => {
                if (res.status === 200) {
                    setCities(res.data);
                }
            }).catch(err => {
                toast.current.show({ severity: 'error', summary: 'Error', detail: 'A problem occurred!' });
            });

        studentService.getAllDistricts()
            .then(res => {
                if (res.status === 200) {
                    setDistricts(res.data);
                }
            }).catch(err => {
                toast.current.show({ severity: 'error', summary: 'Error', detail: 'A problem occurred!' });
            });
    }, [city, district]);

    return (
        <>
            <Helmet>
                <title>Update Student</title>
            </Helmet>
            <Toast ref={toast} />
            <div style={{ padding: 32 }}>
                <Fieldset legend="Update Student">
                    <div className="p-fluid p-formgrid p-grid">
                        <div className="p-field p-col-12 p-md-4">
                            <label htmlFor="name">Name</label>
                            <InputText
                                className={nameError ? "p-invalid p-d-block" : ""}
                                id="name"
                                type="text"
                                value={name}
                                onChange={e => setName(e.target.value)}
                            />
                        </div>
                        <div className="p-field p-col-12 p-md-4">
                            <label htmlFor="surname">Surname</label>
                            <InputText
                                className={surnameError ? "p-invalid p-d-block" : ""}
                                id="surname"
                                type="text"
                                value={surname}
                                onChange={e => setSurname(e.target.value)}
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
                                onChange={e => setPhoneNumber(e.target.value)}
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
                                onChange={e => setDescription(e.target.value)}
                            />
                        </div>
                    </div>
                    <Button
                        className="p-button-warning"
                        style={{
                            marginBottom: 16,
                            marginLeft: 4,
                            float: 'right',
                            zIndex: 1
                        }} label="Update" icon="pi pi-pencil" loading={loading} onClick={onClickUpdate} />
                </Fieldset>
            </div>
        </>
    )
}

export default UpdateStudent;