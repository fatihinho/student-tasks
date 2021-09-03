import React, { useState } from 'react';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { InputMask } from 'primereact/inputmask';
import { Dropdown } from 'primereact/dropdown';
import { InputTextarea } from 'primereact/inputtextarea';
import { Helmet } from 'react-helmet';

const AddStudent = () => {
    const [loading, setLoading] = useState(false);

    const [selectedCity, setSelectedCity] = useState(null);
    const [selectedDistrict, setSelectedDistrict] = useState(null);

    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [description, setDescription] = useState('');

    const [nameError, setNameError] = useState(false);
    const [surnameError, setSurnameError] = useState(false);
    const [phoneError, setPhoneError] = useState(false);
    const [cityError, setCityError] = useState(false);
    const [districtError, setDistrictError] = useState(false);

    let cities = [
        { name: 'Ankara' },
        { name: 'Kayseri' },
        { name: 'İstanbul' },
        { name: 'İzmir' },
    ];

    let districts = [
        { name: 'Arizona', code: 'Arizona' },
        { name: 'California', value: 'California' },
        { name: 'Florida', code: 'Florida' },
        { name: 'Ohio', code: 'Ohio' },
        { name: 'Washington', code: 'Washington' }
    ];

    const onCityChange = (e) => {
        setSelectedCity(e.value);
    }

    const onDistrictChange = (e) => {
        setSelectedDistrict(e.value);
    }

    const onClickRegister = () => {
        if (name.trim().length > 0 && surname.trim().length > 0 && phoneNumber.length > 0 && selectedCity !== null && selectedDistrict !== null) {
            setLoading(true);
            setTimeout(() => {
                setLoading(false);
            }, 1000);


        } else {
            if (name.trim().length <= 0) {
                setNameError(true);
                setTimeout(() => {
                    setNameError(false);
                }, 1500);
            } if (surname.trim().length <= 0) {
                setSurnameError(true);
                setTimeout(() => {
                    setSurnameError(false);
                }, 1500);
            } if (phoneNumber.length <= 0) {
                setPhoneError(true);
                setTimeout(() => {
                    setPhoneError(false);
                }, 1500);
            } if (selectedCity === null) {
                setCityError(true);
                setTimeout(() => {
                    setCityError(false);
                }, 1500);
            } if (selectedDistrict === null) {
                setDistrictError(true);
                setTimeout(() => {
                    setDistrictError(false);
                }, 1500);
            }
        }
    }

    return (
        <>
            <Helmet>
                <title>Add Student</title>
            </Helmet>
            <div style={{ padding: 32 }}>
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
                            options={districts}
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
                }} label="Register" icon="pi pi-check" loading={loading} onClick={onClickRegister} />
            </div>
        </>
    )
}

export default AddStudent;