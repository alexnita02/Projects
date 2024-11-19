
import axios from "axios";
import React, { useState } from 'react';
import { Link, useNavigate } from "react-router-dom";
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';

export default function Register() {

    let navigate = useNavigate()

    const [user, setUser] = useState({
        role:'',
        codParafaMedic:'',
        surname: '',
        name: '',
        email: '',
        cnp: '',
        adresa: '',
        varsta: '',
        username: '',
        password: ''
    });

    const { role,codParafaMedic,surname, name, email, cnp, adresa, varsta, username, password } = user;

    const onInputCharge = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value });
    };

    const registerUser = () => {
        if (!username || !password ||!role) {
            confirmAlert({
                title: 'Validation Error',
                message: 'Username and password are mandatory fields.',
                buttons: [
                    {
                        label: 'OK',
                        onClick: () => { }
                    }
                ]
            });
        } else if (password.length < 6) {
            confirmAlert({
                title: 'Validation Error',
                message: 'Password has to have a minimum of 6 characters.',
                buttons: [
                    {
                        label: 'OK',
                        onClick: () => { }
                    }
                ]
            });
        } else {
            confirmAlert({
                title: 'Confirm Account Creation',
                message: 'Are you sure you want to create this account?',
                buttons: [
                    {
                        label: 'Yes',
                        onClick: async () => {
                            try {
                                await axios.post("http://localhost:8080/user", user);
                                navigate("/");
                                confirmAlert({
                                    title: 'Create Successful',
                                    message: 'Your account has been successfully created.',
                                    buttons: [
                                        {
                                            label: 'OK',
                                            onClick: () => { }
                                        }
                                    ]
                                });
                            } catch (error) {
                                console.error("Error creating user:", error);
                            }
                        }
                    },
                    {
                        label: 'No',
                        onClick: () => {
                            navigate("/");
                        }
                    }
                ]
            });
        }
    };

    const onSubmit = async (e) => {
        e.preventDefault();
        registerUser();
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 bg-dark border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4"style={{ color: 'white' }}>Register User</h2>

                    <form onSubmit={(e) => onSubmit(e)}>
                    <div className="mb-3">
                    <label htmlFor="role" className="form-label" style={{ color: 'white' }}>
                                Role
                            </label>
                            <select
                                className="form-select"
                                name="role"
                                value={role}
                                onChange={(e) => onInputCharge(e)}
                            >
                                <option value="">Select your role</option>
                                <option value="Pacient">Pacient</option>
                                <option value="Medic">Medic</option>
                            </select>
                        </div>

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
