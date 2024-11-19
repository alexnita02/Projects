import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import {BrowserRouter as Router,Routes,Route} from 'react-router-dom';
import AddUser from './users/AddUser';
import EditUser from './users/EditUser';
import ViewUser from './users/ViewUser';
import Login from './contexts/Login';
import Register from './contexts/Register';
import PacientProfile from './users/PacientProfile';
import VirtualClinic from './pages/components/VirtualClinic';
import Programare from './pages/components/Programare';
import Admin from './pages/Admin';
import Users from './pages/Users';
import EditUserAdmin from './pages/EditUserAdmin';
import ViewUserAdmin from './pages/ViewUserAdmin';
import AddUserAdmin from './pages/AddUserAdmin';
import React from 'react';
import VirtualChat from './pages/components/chat/VirtualChat';

function App() {
  return (
    <div className="App">
      <Router>
      <Navbar />

      <Routes>
        <Route exact path="/" element={ <Home/>}>
        <Route exact path="/" element={<Home />} /> </Route>
        <Route exact path="/adduser" element={<AddUser />} />
        <Route exact path="/adduser-admin" element={<AddUserAdmin/>} />
        <Route exact path="/edituser/:id" element={<EditUser/>} />
        <Route exact path="/edituser-admin/:id" element={<EditUserAdmin/>} />
        <Route exact path="/viewuser/:id" element={<ViewUser/>}/>
        <Route exact path="/viewuser-admin/:id" element={<ViewUserAdmin/>}/>
        <Route exact path="/login/" element={<Login/>}/>
        <Route exact path="/register/" element={<Register/>}/>
        <Route exact path="/clinica-virtuala" element ={<VirtualClinic/>}/>
        <Route exact path="/clinica-virtuala/programare" element ={<Programare/>}/>
        <Route exact path="/admin" element ={<Admin/>}/>
        <Route exact path="users" element = {<Users/>}/>
        <Route exact path="/detaliimedicale/:id" element = {<PacientProfile/>}/>
        <Route exact path="/chat" element = {<VirtualChat/>}/>
        
      </Routes>
      
      
      </Router>
        
       
    </div>
  );
}

export default App;





