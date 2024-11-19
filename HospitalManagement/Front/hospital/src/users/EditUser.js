
import axios from "axios";
import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from "react-router-dom";
import { confirmAlert } from 'react-confirm-alert'; // Import the library
import 'react-confirm-alert/src/react-confirm-alert.css'; // Import the default styles

export default function EditUser() {
  let navigate = useNavigate();
  const { id } = useParams();

  const [user, setUser] = useState({
    surname: '',
    name: '',
    email: '',
  });

  const { surname, name, email } = user;

  const onInputCharge = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  useEffect(() => {
    loadUser();
  }, []);

  
  const onSubmit = async (e) => {
    e.preventDefault();

    const isConfirmed = window.confirm("Are you sure you want to modify your details?");
    if (isConfirmed) {
      await axios.put(`http://localhost:8080/user/${id}`, user);
      showEditSuccessAlert();
      navigate("/");
    } else {
      loadUser();
    }
  };

  const loadUser = async () => {
    const result = await axios.get(`http://localhost:8080/user/${id}`);
    setUser(result.data);
  };

  const showEditSuccessAlert = () => {
    confirmAlert({
      title: 'Edit Successful',
      message: 'Your details have been successfully edited.',
      buttons: [
        {
          label: 'OK',
          onClick: () => {}
        }
      ]
    });
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Edit User</h2>
          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="surname" className="form-label">
                Surname
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter your surname"
                name="surname"
                value={surname}
                onChange={(e) => onInputCharge(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="name" className="form-label">
                Name
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter your name"
                name="name"
                value={name}
                onChange={(e) => onInputCharge(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">
                Email
              </label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter your email"
                name="email"
                value={email}
                onChange={(e) => onInputCharge(e)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Edit
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
