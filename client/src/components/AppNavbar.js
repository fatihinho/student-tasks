import { Button } from 'primereact/button';
import React, { useState } from 'react';
import { Navbar, NavbarBrand } from 'reactstrap';
import AppSidebar from './Sidebar';

const AppNavbar = () => {
    const [visible, setVisible] = useState(false);

    return (
        <>
            <Navbar style={{
                padding: "16px",
                justifyContent: "space-between",
                background: "#2196F3"
            }} dark expand="md">
                <Button style={{ verticalAlign: 'baseline' }} icon="pi pi-bars" onClick={() => setVisible(true)} className="p-mr-2" />
                <NavbarBrand style={{
                    fontSize: 24,
                    fontWeight: 'bold',
                    fontFamily: 'cursive',
                    color: 'white'
                }}
                >
                    Student Tasks App
                </NavbarBrand>
            </Navbar>
            <AppSidebar visible={visible} setVisible={setVisible} />
        </>
    );
}

export default AppNavbar;