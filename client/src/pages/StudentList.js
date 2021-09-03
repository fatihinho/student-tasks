import React, { useState, useEffect } from 'react';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { StudentService } from '../services/StudentService';
import { Button } from 'primereact/button';
import { Helmet } from 'react-helmet';

const StudentList = () => {
    const [students, setStudents] = useState([]);
    const [selectedStudent, setSelectedStudent] = useState(null);

    useEffect(() => {
        const studentService = new StudentService();
        studentService.getProductsSmall().then(data => setStudents(data));
    }, []);

    const onClickListItem = (e) => {
        console.log(e.data.id);
    }

    const onClickUpdate = () => {
        console.log('Update');
    }

    const onClickDelete = () => {
        console.log('Delete');
    }

    return (
        <>
            <Helmet>
                <title>List Student</title>
            </Helmet>
            <div style={{ padding: 32 }}>
                <div className="card">

                    <Button style={{
                        marginBottom: 16,
                        marginLeft: 4,
                        float: 'right',
                        zIndex: 1
                    }} onClick={onClickDelete} className="p-button-rounded p-button-danger">Delete</Button>

                    <Button style={{
                        marginBottom: 16,
                        marginRight: 4,
                        float: 'right',
                        zIndex: 1
                    }} onClick={onClickUpdate} className="p-button-rounded p-button-warning">Update</Button>

                    <DataTable
                        value={students}
                        selectionMode="single"
                        onRowSelect={e => onClickListItem(e)}
                        selection={selectedStudent}
                        onSelectionChange={e => setSelectedStudent(e.value)}
                        dataKey="id"
                    >
                        <Column field="code" header="Code"></Column>
                        <Column field="name" header="Name"></Column>
                        <Column field="category" header="Category"></Column>
                        <Column field="quantity" header="Quantity"></Column>
                    </DataTable>
                </div>
            </div>
        </>
    );
}

export default StudentList;