import React, { useState, useEffect } from 'react';
import './VirtualClinic.css';
import consultatie from './consultatie.jpg'

const VirtualClinic = () => {
  const [messages, setMessages] = useState([]);
  const [inputMessage, setInputMessage] = useState('');

  const predefinedConversations = [
    { user: 'Am cazut pe jos', robot: 'Ai nevoie de o ambulanta?' },
    { user: 'Buna', robot: 'Salut!Ce s-a intamplat?' },
    { user: 'Buna! Mi-e rau!', robot: 'Salut!Ce s-a intamplat?' },
    { user: 'Buna!', robot: 'Buna ziua! Cu ce va poate fi de folos ChatBot-ul?' },
    { user: 'Buna ziua!', robot: 'Salut! ChatBot-ul la dispozitia dumneavoastra! Cu ce va pot fi de folos?' },  
    { user: 'Salut!', robot: 'Buna.Poti sa-mi spui ce s-a intamplat?' },
    { user: 'Servus!', robot: 'Buna.Poti sa-mi spui ce s-a intamplat?' },
    { user: 'Am cazut', robot: 'E o situatie dificila. Oare poti intinde mana?' },
    { user: 'Nu pot intinde mana', robot: 'Iti recomand sa faci urgent o programare la unul din doctorii nostri.' },
    { user: 'Am racit', robot: 'Ce simptome aveti?' },
    { user: 'Ma doare capul si am febra', robot: 'Va recomand sa vedeti cat mai rapid un medic!' },
    { user: 'Salut!', robot: 'Buna! Cu ce va pot ajuta astazi?' },
    { user: 'Ce mai faci?', robot: 'Sunt aici pentru a te ajuta. Cu ce poti conta?' },
  { user: 'Multumesc!', robot: 'Cu placere! Daca ai nevoie de ajutor, intreaba-ma oricand.' },
  { user: 'Care este adresa ta?', robot: 'Ne gasesti online la adresa www.chatbotmedical.ro.' },
  { user: 'Cat costa consultatia?', robot: 'Informatiile despre costuri sunt disponibile pe site-ul nostru. Poti verifica acolo pentru mai multe detalii.' },
  { user: 'Pot face o programare?', robot: 'Da, cu siguranta! Poti face o programare online sau sunand la numarul nostru de contact.' },
  { user: 'Ai recomandari pentru medicul de familie?', robot: 'Poti verifica site-ul nostru pentru recomandari ale medicilor din reteaua noastra.' },
  { user: 'Cum pot accesa istoricul medical?', robot: 'Poti accesa istoricul medical prin contul tau online. Daca intampini probleme, suntem aici pentru a te ajuta.' },
  { user: 'La ce ora sunteti deschisi?', robot: 'Suntem deschisi 24/7 online. Pentru programari fizice, verifica orele noastre de functionare pe site.' },
  { user: 'Am uitat parola contului meu', robot: 'Poti reseta parola accesand optiunea "Am uitat parola" de pe pagina de autentificare.' },
  { user: 'Am febra', robot: 'E o situatie dificila. Oare aveti si greturi?' },
  { user: 'Da', robot:'In aceasta situatia va recomand sa va faceti o programare la spitalul nostru.'}

    // Adaugă aici alte perechi de mesaje utilizator-robot
  ];

  const handleSendMessage = () => {
    if (inputMessage.trim() === '') return;

    // Adaugă mesajul utilizatorului în conversație
    setMessages([...messages, { text: inputMessage, isUser: true }]);
    setInputMessage('');
  };

  useEffect(() => {
    const lastUserMessage = messages[messages.length - 1]?.text || ''; // Preia ultimul mesaj al utilizatorului
    const predefinedResponse = predefinedConversations.find(conversation => conversation.user === lastUserMessage)?.robot;

    if (predefinedResponse) {
      // Simulează răspunsul robotului după o întârziere de 500ms
      setTimeout(() => {
        setMessages([...messages, { text: predefinedResponse, isUser: false }]);
      }, 3000);
    }
  }, [messages]);

  return (
    <div className="name">
      <div className="container">
        <div className="row">
          <div className="column">
            <div className="custom-section">
              <h2 className="text-center mb-4">Consultatii online in Clinica Virtuala</h2>
              <img src={consultatie} alt="Section 1" className="img-fluid" />
              <p>
                Consultatii online, de la tine de acasa, beneficiind de opinia avizata a unui medic specialist.
                 Oferim pacientilor posibilitatea de a beneficia de consultatii online, desfasurate de la distanta.
                  In aceasta pagina, poti face o cerere de programare si tot aici vei putea accesa consultatia online,
                   la data si ora confirmate impreuna cu reprezentantii spitalului nostru. Pacientii pot suna
                    la numarul din call-center dedicat +040213010770, atat pentru a face programare in Clinica Virtuala
              </p>
              <div className="field__item">
                <a href="/clinica-virtuala/programare">Programeaza-te aici!</a>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div className="virtual-clinic-container">
        <div className="chat-header">
          <h2>ChatBot</h2>
          <div className="online-indicator"></div>
        </div>
        <div className="chat-container">
          {messages.map((message, index) => (
            <div key={index} className={message.isUser ? 'user-message' : 'robot-message'}>
              {message.text}
            </div>
          ))}
          {messages.length > 0 && (
            <div className="typing-indicator">
              <div className="dot"></div>
              <div className="dot"></div>
              <div className="dot"></div>
            </div>
          )}
        </div>
        <div className="input-container">
          <input
            type="text"
            placeholder="Introduceți mesajul dvs..."
            value={inputMessage}
            onChange={(e) => setInputMessage(e.target.value)}
          />
          <button onClick={handleSendMessage}>Trimite</button>
        </div>
      </div>
    </div>
  );
  
  
          }
  

export default VirtualClinic;
