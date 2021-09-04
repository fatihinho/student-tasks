import axios from 'axios';

export class StudentService {

    getAllStudents() {
        return axios.get('/api/v1/students');
    }

    getAllCities() {
        return axios.get('/api/v1/cities');
    }

    getAllDistricts() {
        return axios.get('/api/v1/districts');
    }

    createStudent(name, surname, phoneNumber, cityId, districtId, description) {
        return axios.post('/api/v1/students/create', {
            name: name,
            surname: surname,
            phoneNumber: phoneNumber,
            cityId: cityId,
            districtId: districtId,
            description: description
        });
    }

    updateStudentById(id, name, surname, phoneNumber, cityId, districtId, description) {
        return axios.put(`/api/v1/students/update/${id}`, {
            name: name,
            surname: surname,
            phoneNumber: phoneNumber,
            cityId: cityId,
            districtId: districtId,
            description: description
        });
    }

    deleteStudentById(id) {
        return axios.delete(`/api/v1/students/delete/${id}`);
    }
}