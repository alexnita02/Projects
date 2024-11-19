import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link,useParams } from 'react-router-dom';
import hospitalImage from './hospital.jpeg';
import './Home.css';

const HomePage = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        loadUsers();
    }, []);

    const loadUsers = async () => {
        try {
            const response = await axios.get("http://localhost:8080/users");
            setUsers(response.data);
        } catch (error) {
            console.error("Error loading users:", error);
        }
    };

    return (
       


        <div className="homepage-container">
            {/* Header with Image */}
            <div className="jumbotron jumbotron-fluid homepage-header">
                <div className="container text-center">
                    <img src={hospitalImage} alt="Hospital" className="img-fluid" />
                    <h1 className="display-4">Welcome to Our Hospital</h1>
                    <p className="lead">Providing Quality Healthcare Services</p>
                </div>
            </div>

            {/* Content Section */}
            <div className="homepage-content">
                <h2 className="text-center mb-4">Asistență 24/7</h2>
                <Link className="btn btn-primary mb-3" to="/chat">
                    ChatLive
                </Link>




                <div className="custom-section-container">

                    <div className="custom-section">
                        <a className="custom-container3" href="/clinica-virtuala">

                            <h3>Clinica virtuala</h3>
                            <p>Consultatii online, de la tine de acasa</p>
                        </a>
                    </div>



                    <div className="custom-section">

                    {users.map((user) => (
                    <div key={user.id} className="custom-section1">
                        {user.role === 'medic' || user.role === 'Medic' ? (
                        <Link className="btn btn-primary mx-auto my-2" to={`/viewuser/${user.id}`}>
                            Dr. {user.name }
                        </Link>
                        ):(
                            <p className="text-muted"></p>
                        )}

                        {/* <Link className="btn btn-primary mx-auto my-2" to={`/viewuser/${user.id}`}>
                            Dr. {user.name }
                        </Link> */}
                    </div>
                ))}
                        

                            
                           
                        
                    </div>


                </div>



                {/* Custom Section 3 */}
                <div className="custom-section2">

                </div>
            

            {/* Presentation Section */}
            <div className="presentation-section">
                <h2 className="text-center mb-4">About Our Hospital</h2>
                <p>
                    Bun venit la Spitalul Nostru!

                    Suntem mândri să vă oferim îngrijiri medicale de înaltă calitate și servicii personalizate
                    în cadrul Spitalului Nostru. Cu o echipă dedicată de profesioniști în domeniul sănătății,
                    ne străduim să vă oferim cea mai bună îngrijire, combinând experiența medicală de vârf cu
                    compasiunea și atenția la detalii.
                    La Spitalul Nostru, punem pacienții pe primul loc. Fie că aveți nevoie de
                    o consultație de rutină, tratamente specializate sau intervenții chirurgicale complexe,
                    echipa noastră de medici și asistenți medicali vă stă la dispoziție pentru a vă asigura
                    că primiți cea mai bună îngrijire posibilă. Suntem dedicați să vă ajutăm să vă recăpătați
                    sănătatea și să vă mențineți binele.

                    Facilitățile noastre moderne și tehnologiile de ultimă generație ne permit să oferim servicii medicale de calitate superioară într-un mediu sigur și curat. Ne străduim să fim o comunitate de sănătate unde fiecare pacient se simte respectat, susținut și îngrijit cu atenție.
                </p>
                <p>
                    Fie că aveți nevoie de o consultație de rutină, tratamente specializate sau intervenții chirurgicale complexe, echipa noastră de medici și asistenți medicali vă stă la dispoziție pentru a vă asigura că primiți cea mai bună îngrijire posibilă. Suntem dedicați să vă ajutăm să vă recăpătați sănătatea și să vă mențineți binele. Facilitățile noastre moderne și tehnologiile de ultimă generație ne permit să oferim servicii medicale de calitate superioară într-un mediu sigur și curat. Ne străduim să fim o comunitate de sănătate unde fiecare pacient se simte respectat, susținut și îngrijit cu atenție.
                </p>
            </div>

            </div>
        
        </div >
    );
};

export default HomePage;
