import React, { useState, useEffect, useRef } from 'react';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { StudentService } from '../services/StudentService';
import { Button } from 'primereact/button';
import { Helmet } from 'react-helmet';
import { useHistory } from 'react-router-dom';
import { Toast } from 'primereact/toast';
import { Fieldset } from 'primereact/fieldset';
import { Ripple } from 'primereact/ripple';
import { Dropdown } from 'primereact/dropdown';
import { InputText } from 'primereact/inputtext';
import { classNames } from 'primereact/utils';
import { confirmDialog } from 'primereact/confirmdialog';

const StudentList = () => {
    const history = useHistory();

    const toast = useRef();

    const [students, setStudents] = useState([]);
    const [selectedStudent, setSelectedStudent] = useState(null);

    const [first, setFirst] = useState(0);
    const [rows, setRows] = useState(10);
    const [currentPage, setCurrentPage] = useState(1);
    const [pageInputTooltip, setPageInputTooltip] = useState('Press \'Enter\' key to go to this page.');

    const onPageInputKeyDown = (event, options) => {
        if (event.key === 'Enter') {
            const page = parseInt(currentPage);
            if (page < 0 || page > options.totalPages) {
                setPageInputTooltip(`Value must be between 1 and ${options.totalPages}.`);
            }
            else {
                const first = currentPage ? options.rows * (page - 1) : 0;

                setFirst(first);
                setPageInputTooltip('Press \'Enter\' key to go to this page.');
            }
        }
    }

    const onPageInputChange = (event) => {
        setCurrentPage(event.target.value);
    }

    const onCustomPage = (event) => {
        setFirst(event.first);
        setRows(event.rows);
        setCurrentPage(event.page + 1);
    }

    const onClickUpdate = () => {
        if (selectedStudent !== null) {
            history.push({
                pathname: `/update-student/${selectedStudent.id}`,
                state: {
                    name: selectedStudent.name,
                    surname: selectedStudent.surname,
                    phoneNumber: selectedStudent.phoneNumber,
                    city: selectedStudent.city,
                    district: selectedStudent.district,
                    description: selectedStudent.description
                }
            });
        } else {
            toast.current.show({ severity: 'warn', summary: 'Warning', detail: 'You have to select a student!', life: 3000 });
        }
    }

    const onClickUploadImage = () => {
        if (selectedStudent !== null) {
            history.push({
                pathname: `/upload-image/${selectedStudent.id}`,
            });
        } else {
            toast.current.show({ severity: 'warn', summary: 'Warning', detail: 'You have to select a student!', life: 3000 });
        }
    }

    const deleteStudent = () => {
        const studentService = new StudentService();
        studentService.deleteStudentById(selectedStudent.id)
            .then(res => {
                if (res.status === 200) {
                    toast.current.show({ severity: 'success', summary: 'Success', detail: 'Student has deleted!' });
                    onClickClear();
                }
            }).catch(err => {
                toast.current.show({ severity: 'error', summary: 'Error', detail: 'A problem occurred!' });
            })
    }

    const onClickDelete = () => {
        if (selectedStudent !== null) {
            confirmDialog({
                message: 'Do you want to delete this student?',
                header: 'Delete Confirmation',
                icon: 'pi pi-info-circle',
                acceptClassName: 'p-button-danger',
                accept: deleteStudent,
                reject: () => null
            });
        } else {
            toast.current.show({ severity: 'warn', summary: 'Warning', detail: 'You have to select a student!', life: 3000 });
        }
    }

    const onClickClear = () => {
        setSelectedStudent(null);
    }

    const template = {
        layout: 'PrevPageLink PageLinks NextPageLink RowsPerPageDropdown CurrentPageReport',
        'PrevPageLink': (options) => {
            return (
                <button type="button" className={options.className} onClick={options.onClick} disabled={options.disabled}>
                    <span className="p-p-3">Previous</span>
                    <Ripple />
                </button>
            )
        },
        'NextPageLink': (options) => {
            return (
                <button type="button" className={options.className} onClick={options.onClick} disabled={options.disabled}>
                    <span className="p-p-3">Next</span>
                    <Ripple />
                </button>
            )
        },
        'PageLinks': (options) => {
            if ((options.view.startPage === options.page && options.view.startPage !== 0) || (options.view.endPage === options.page && options.page + 1 !== options.totalPages)) {
                const className = classNames(options.className, { 'p-disabled': true });

                return <span className={className} style={{ userSelect: 'none' }}>...</span>;
            }

            return (
                <button type="button" className={options.className} onClick={options.onClick}>
                    {options.page + 1}
                    <Ripple />
                </button>
            )
        },
        'RowsPerPageDropdown': (options) => {
            const dropdownOptions = [
                { label: 10, value: 10 },
                { label: 20, value: 20 },
                { label: 50, value: 50 },
                { label: 'All', value: options.totalRecords }
            ];

            return <Dropdown value={options.value} options={dropdownOptions} onChange={options.onChange} appendTo={document.body} />;
        },
        'CurrentPageReport': (options) => {
            return (
                <span className="p-mx-3" style={{ color: 'var(--text-color)', userSelect: 'none' }}>
                    Go to <InputText size="2" className="p-ml-1" value={currentPage} tooltip={pageInputTooltip}
                        onKeyDown={(e) => onPageInputKeyDown(e, options)} onChange={onPageInputChange} />
                </span>
            )
        }
    };

    useEffect(() => {
        const studentService = new StudentService();
        studentService.getAllStudents()
            .then(res => {
                if (res.status === 200 || res.status === 204) {
                    setStudents(res.data);
                }
            })
    }, [students]);

    return (
        <>
            <Helmet>
                <title>Student List</title>
            </Helmet>
            <Toast ref={toast} />
            <div style={{ padding: 32 }}>
                <Fieldset legend="Student List">
                    <div className="card">
                        {
                            selectedStudent
                                ?
                                <Button style={{
                                    marginBottom: 16,
                                    marginLeft: 4,
                                }} onClick={onClickClear} className="p-button-text p-button-plain">Clear Selection</Button>
                                : null
                        }
                        <Button style={{
                            marginBottom: 16,
                            marginLeft: 4,
                            float: 'right',
                            zIndex: 1
                        }} onClick={onClickDelete} className="p-button-rounded p-button-danger" label="Delete" icon="pi pi-trash" />

                        <Button style={{
                            marginBottom: 16,
                            marginRight: 4,
                            float: 'right',
                            zIndex: 1
                        }} onClick={onClickUpdate} className="p-button-rounded p-button-warning" label="Update" icon="pi pi-pencil" />

                        <Button style={{
                            marginBottom: 16,
                            marginRight: 4,
                            float: 'right',
                            zIndex: 1
                        }} onClick={onClickUploadImage} className="p-button-rounded" label="Upload Image" icon="pi pi-upload" />

                        <DataTable
                            paginator
                            paginatorTemplate={template}
                            first={first}
                            rows={rows}
                            onPage={onCustomPage}
                            value={students}
                            selectionMode="single"
                            selection={selectedStudent}
                            onSelectionChange={e => setSelectedStudent(e.value)}
                            dataKey="id"
                        >
                            <Column field="name" header="Name"></Column>
                            <Column field="surname" header="Surname"></Column>
                            <Column field="phoneNumber" header="Phone Number"></Column>
                            <Column field="city.name" header="City"></Column>
                            <Column field="district.name" header="District"></Column>
                            <Column field="description" header="Description"></Column>
                        </DataTable>
                    </div>
                </Fieldset>
            </div>
        </>
    );
}

export default StudentList;