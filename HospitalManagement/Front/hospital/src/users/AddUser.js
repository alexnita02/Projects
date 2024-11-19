import axios from "axios";
import React, { useState } from 'react';
import { Link,useNavigate } from "react-router-dom";

export default function AddUser() {

    let navigate=useNavigate()

  const [user, setUser] = useState({
    surname: '',
    name: '',
    email: '',
  });

  const { surname, name, email } = user;

  const onInputCharge = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
    //                            ^^^^^^^^^^
  };

  const onSubmit=async(e)=>{
    e.preventDefault();
    await axios.post("http://localhost:8080/user",user)
    navigate("/");
};
  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Register User</h2>

          <form onSubmit={(e)=>onSubmit(e)}>
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
            Sign Up
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
