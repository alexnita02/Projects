import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';

export default function ViewUser() {
  const [user, setUser] = useState({
    surname: '',
    name: '',
    email: '',
    cnp: '',
    adresa: '',
    varsta: '',
  });

  const { id } = useParams();

  useEffect(() => {
    loadUser();
  }, []);

  const loadUser = async () => {
    const result = await axios.get(`http://localhost:8080/user/${id}`);
    setUser(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">User details</h2>
          <div className="card">
            <div className="card-header">
              Contact details of user id:
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Surname:</b> {user.surname}
                </li>
                <li className="list-group-item">
                  <b>Name:</b> {user.name}
                </li>
                <li className="list-group-item">
                  <b>Email:</b> {user.email}
                </li>
                <li className="list-group-item">
                  <b>CNP:</b> {user.cnp}
                </li>
                <li className="list-group-item">
                  <b>Adress:</b> {user.adresa}
                </li>
                <li className="list-group-item">
                  <b>Varsta:</b> {user.varsta}
                </li>
                <li className="list-group-item">
                  <b>Other Health Info:</b> {/* Add other health details here */}
                </li>
              </ul>
            </div>
          </div>
          <div className="card mt-4">
            {/* Health details section */}
            {user.role === 'medic' ? (
              <>
                <Link className="btn btn-primary my-2" to={`/viewuser/${user.id}`}>
                  Dr. {user.name}
                </Link>
                {/* Health details for medic */}
                <div className="card mt-4">
                  <div className="card-header">
                    Health details of user id:
                    <ul className="list-group list-group-flush">
                      <li className="list-group-item">
                        <b>Your doctor:</b> {user.name}
                      </li>
                      { }
                    </ul>
                  </div>
                </div>
              </>
            ) : (
              <p className="text-muted"></p>
            )}
          </div>
          <Link className="btn btn-primary my-2" to={`/detaliimedicale/${id}`}>
            Health Details
          </Link>
          <Link className="btn btn-primary my-2" to="/">
            Back
          </Link>
        </div>
      </div>
    </div>
  );
}
