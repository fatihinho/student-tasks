import React from 'react';
import { Sidebar } from 'primereact/sidebar';
import { Button } from 'primereact/button';
import { useHistory } from 'react-router-dom';

const AppSidebar = ({ visible, setVisible }) => {
  const history = useHistory();

  const navToStudentList = () => {
    history.push('/');
    setVisible(false);
  }

  const navToAddStudent = () => {
    history.push('/add-student');
    setVisible(false);
  }

  return (
    <div>
      <div className="card">
        <Sidebar visible={visible} onHide={() => setVisible(false)}>
          <h1>Student Tasks App</h1>
          <h4>You can Add, Update, Delete and List students in this app!</h4>
          <div style={{ margin: 8 }}><Button className="p-button-outlined" onClick={navToStudentList}>List Student</Button></div>
          <div style={{ margin: 8 }}><Button className="p-button-outlined" onClick={navToAddStudent}>Add Student</Button></div>
          <h6>Copyright {new Date().getFullYear()} © Developed by Fatih Çınar</h6>
        </Sidebar>
      </div>
    </div>
  );
};

export default AppSidebar;