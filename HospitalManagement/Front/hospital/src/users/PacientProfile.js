import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';

export default function PacientProfile() {
  const [detaliiMedicamente, setDetaliiMedicamente] = useState({
    temperatura: "",
    tensiune: "",
    boliCronice: "",
  });

  const { id } = useParams();

  const [user, setUser] = useState({
    surname: "",
    name: "",
    email: "",
    cnp: "",
    adresa: "",
    varsta: "",
  });

  useEffect(() => {
    const loadDetaliiMedicamente = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/detaliimedicale/${id}`);
        setDetaliiMedicamente(response.data);
      } catch (error) {
        console.error('Eroare la încărcarea datelor medicamentelor', error);
      }
    };

    // Request to get all users
    const fetchUsers = async () => {
      const response = await axios.get("http://localhost:8080/users");
      const users = response.data;

      // Find a random user with the role of medic
      const randomMedic = users.find((u) => u.role === 'medic');

      // If a random medic is found, set the user state
      if (randomMedic) {
        setUser(randomMedic);
      }
    };

    fetchUsers(); // Call the function to get random medic
    loadDetaliiMedicamente(); // Call the function to get medical details
  }, [id]);

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Detalii medicale</h2>
          <div className="card">
            <div className="card-header">
              Detalii medicamente ale pacientului :
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Temperatura:</b> {detaliiMedicamente.temperatura}
                </li>
                <li className="list-group-item">
                  <b>Tensiune arterială:</b> {detaliiMedicamente.tensiune}
                </li>
                <li className="list-group-item">
                  <b>Boli cronice:</b> {detaliiMedicamente.boliCronice}
                </li>
                
              </ul>
            </div>
          </div>
          <div className="card mt-4">
            {/* Medic details section */}
            {user.role === 'medic' ? (
              <>
                <Link className="btn btn-primary my-2" to={`/viewuser/${user.id}`}>
                  Dr. {user.name}
                </Link>
                {/* Medic details */}
                <div className="card mt-4">
                  <div className="card-header">
                    Detalii medicale ale medicului:
                    <ul className="list-group list-group-flush">
                      <li className="list-group-item">
                        <b>Email:</b> {user.email}
                      </li>
                      <li className="list-group-item">
                        <b>Contact:</b> {user.telefon}
                      </li>
                      {/* Alte detalii medicale ... */}
                    </ul>
                  </div>
                </div>
              </>
            ) : (
              <p className="text-muted"></p>
            )}
          </div>
          <Link className="btn btn-primary my-2" to="/">
            Home
          </Link>
        </div>
      </div>
    </div>
  );
}
