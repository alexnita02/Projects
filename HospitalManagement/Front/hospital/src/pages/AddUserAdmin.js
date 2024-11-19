import axios from "axios";
import React, { useState } from 'react';
import { Link,useNavigate } from "react-router-dom";

export default function AddUserAdmin() {

    let navigate=useNavigate()

  const [user, setUser] = useState({
        role:'',
        
        surname: '',
        name: '',
        email: '',
        cnp: '',
        adresa: '',
        varsta: '',
        username: '',
        password: ''
  });

  const { role,surname, name, email, cnp, adresa, varsta, username, password} = user;

  const onInputCharge = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
    //                            ^^^^^^^^^^
  };

  const onSubmit=async(e)=>{
    e.preventDefault();
    await axios.post("http://localhost:8080/user",user)
    navigate("/users");
};
  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Register User</h2>

          <form onSubmit={(e)=>onSubmit(e)}>
          <div className="mb-3">
                            <label htmlFor="surname" className="form-label"style={{ color: 'white' }}>
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
                            <label htmlFor="name" className="form-label"style={{ color: 'white' }}>
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
                            <label htmlFor="name" className="form-label"style={{ color: 'white' }}>
                                CNP
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter your name"
                                name="cnp"
                                value={cnp}
                                onChange={(e) => onInputCharge(e)}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="name" className="form-label"style={{ color: 'white' }}>
                                Varsta
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter your name"
                                name="varsta"
                                value={varsta}
                                onChange={(e) => onInputCharge(e)}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="name" className="form-label"style={{ color: 'white' }}>
                                username
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter your name"
                                name="username"
                                value={username}
                                onChange={(e) => onInputCharge(e)}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="name" className="form-label"style={{ color: 'white' }}>
                                password
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter your name"
                                name="password"
                                value={password}
                                onChange={(e) => onInputCharge(e)}
                            />
                        </div> 
          
          <button type="submit" className="btn btn-outline-primary"to='/users'>
            Sign Up
          </button>
          <Link className="btn btn-outline-danger mx-2" to="/users">
            Cancel
          </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
