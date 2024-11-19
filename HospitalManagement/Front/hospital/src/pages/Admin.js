import axios from "axios";
import React, { useState } from 'react';
import { Link, useNavigate, useParams } from "react-router-dom";
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';

export default function Login() {
  let navigate = useNavigate();
  const { id } = useParams();

  const [user, setUser] = useState({
    role:'',
    email: '',
    password: '',
  });

  const { role,email, password } = user;

  const onInputCharge = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const showLoginSuccessAlert = () => {
    
  };

  const showLoginErrorAlert = () => {
    confirmAlert({
      title: 'Login Failed',
      message: 'Invalid email or password. Please try again.',
      buttons: [
        {
          label: 'OK',
          onClick: () => {}
        }
      ]
    });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
     // Make an API call to get the list of users
     const response = await axios.get("http://localhost:8080/users");
     const users = response.data;
 
     // Check if there is a user with the provided email and password
     const authenticatedUser = users.find(
       (u) => u.email === user.email && u.password === user.password
     );
 
     if (authenticatedUser) {
       
       confirmAlert({
        title: 'Login Successful',
        message: 'You have been successfully connected.',
        buttons: [
          {
            label: 'OK',
            onClick: () => {}
          }
        ]
       });
       navigate(`/users`);
       // Poți face orice altceva aici în caz de conectare reușită
     } else {
       // Login failed
       showLoginErrorAlert();
     }
    
     
    
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 bg-dark border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4"style={{ color: 'white' }}>Login</h2>
          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="email" className="form-label"style={{ color: 'white' }}>
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
            <div className="mb-3">
              <label htmlFor="password" className="form-label"style={{ color: 'white' }}>
                Password
              </label>
              <input
                type="password"
                className="form-control"
                placeholder="Enter your password"
                name="password"
                value={password}
                onChange={(e) => onInputCharge(e)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary"to={`/admin/users`} >
              Connect
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