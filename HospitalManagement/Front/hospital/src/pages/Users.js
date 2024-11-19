
import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link, useParams } from 'react-router-dom';
import { confirmAlert } from 'react-confirm-alert';
export default function Users() {

    const [users, setUsers] = useState([]);

    const { id } = useParams()

    useEffect(() => {
        loadUsers();
    }, []);

    const loadUsers = async () => {

        const result = await axios.get("http://localhost:8080/users");
        setUsers(result.data);
    };


    const deleteUser = async (id) => {
        confirmAlert({
            title: 'Confirm Delete',
            message: 'Are you sure you want to delete this user?',
            buttons: [
                {
                    label: 'Yes',
                    onClick: async () => {
                        await axios.delete(`http://localhost:8080/user/${id}`);
                        loadUsers();
                    }
                },
                {
                    label: 'No',
                    onClick: () => {
                        // Do nothing if user cancels
                    }
                }
            ]
        });
    };

    return (
        <div className='container'>
            <div className='py-4'>
                <table className="table border shadow">

                    <thead>
                        <tr>
                            <th scope="col">Role</th>
                            <th scope="col">Surname</th>
                            <th scope="col">Lastname</th>
                            <th scope="col">Email</th>
                            <th scope="col">CNP</th>
                            <th scope="col">varsta</th>
                            <th scope="col">username</th>
                            <th scope="col">password</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>

                        {
                            users.map((user, index) => (
                                <tr> 
                                    {/* <th scope="row" key={index}>{index + 1}</th> */}
                                    <td>{user.role}</td>
                                    <td>{user.surname}</td>
                                    <td>{user.name}</td>
                                    <td>{user.email}</td>
                                    <td>{user.cnp}</td>
                                    <td>{user.varsta}</td>
                                    <td>{user.username}</td>
                                    <td>{user.password}</td>
                                    <td>


                                        <Link className="btn btn-primary mx-2" to={`/viewuser-admin/${user.id}`}
                                        >View</Link>


                                        <Link className="btn btn-outline-primary mx-2"
                                            to={`/edituser-admin/${user.id}`}

                                        >Edit</Link>


                                        <button className="btn btn-danger mx-2"

                                            onClick={() => deleteUser(user.id)}

                                        >Delete</button>
                                        <Link className="btn btn-dark mx-2"to={'/adduser-admin'}

                                        

                                        >Add</Link>
                                    </td>
                                </tr>

                            ))

                        }

                    </tbody>
                </table>

            </div>
        </div>
    )
}
