import React, { useState } from 'react';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import './Calendar.css';
import { confirmAlert} from 'react-confirm-alert';
import { Link, useNavigate, useParams } from "react-router-dom";

const Programare = () => {

    let navigate=useNavigate()
  const [selectedDate, setSelectedDate] = useState(new Date());
  const [isExistingAccount, setIsExistingAccount] = useState(false);

  const handleDateChange = (date) => {
    setSelectedDate(date);
  };
  
  const handleSubmit = (e) => {
    e.preventDefault();
    const today = new Date();
     const formattedToday = today.toLocaleDateString();

    confirmAlert({
      title: 'Send Successful',
      message: 'Ai fost programat!',
      buttons: [
        {
          label: 'OK',
          onClick: () => {
            // Redirect to the Admin's Users page
            // navigate('');
          }
        }
      ]
    });
  };

  return (
    <div className="parent">
      <div className="left-container">
        <div className="calendar-container">
          <h3 className="titluCalendar">Calendar</h3>
          <Calendar onChange={handleDateChange} value={selectedDate} />
          <p className="data-selectata">Selected Date: {selectedDate.toDateString()}</p>
        </div>
      </div>
      <div className="right-container">
        <h3 className="formular">Formular de programare</h3>
        <form className="formular1" onSubmit={handleSubmit}>
          <label htmlFor="name">Nume:</label>
          <input type="text" id="name" name="name" required />

          <label htmlFor="email">Email:</label>
          <input type="email" id="email" name="email" required />

          <label htmlFor="phoneNumber">Număr de telefon:</label>
          <input type="tel" id="phoneNumber" name="phoneNumber" required />

          <label htmlFor="comments">Comentarii:</label>
          <textarea id="comments" name="comments" rows="4"></textarea>

          <button type="submit">Trimite programarea</button>
        </form>
      </div>
    </div>
  );
};

export default Programare;
