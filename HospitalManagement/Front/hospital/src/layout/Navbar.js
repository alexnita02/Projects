import React from "react"
import { Link } from "react-router-dom"

export default function Navbar() {
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-dark bg-danger">
                <div className="container-fluid">


                    <div className="d-flex align-items-center">
                        {/* Style "Home" as a checkbox */}
                        <input type="checkbox" id="homeCheckbox" className="d-none" />
                        <label
                            htmlFor="homeCheckbox"
                            className="navbar-brand text-dark m-0"
                            style={{ cursor: "pointer" }}
                        >
                            ☰ {/* You can use any other icon or text for your checkbox */}
                        </label>
                        <Link className="navbar-brand text-dark" to="/">
                            Home
                        </Link>
                    </div>

                    <button
                        className="navbar-toggler"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                    >
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <div className="d-flex ml-auto">
                        <Link className="btn btn-outline-light text-dark bg-danger " to="/admin">Admin</Link>
                        <Link className="btn btn-outline-light text-gray bg-danger " to="/login">Login</Link>
                        <Link className="btn btn-primary mx-2" to="/register">Sign up</Link>
                    </div>

                </div>
            </nav>

        </div>
    )
}


