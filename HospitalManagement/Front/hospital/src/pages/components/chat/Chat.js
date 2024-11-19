import React, { useState, useEffect } from 'react';
import io from 'socket.io-client';
import styled from 'styled-components';

const socket = io('http://localhost:3000/chat');

const ChatContainer = styled.div`
  max-width: 400px;
  margin: 0 auto;
`;

const MessageContainer = styled.div`
  overflow-y: auto;
  border: 1px solid #ccc;
  padding: 100px;
  margin-bottom: 10px;
  margin-top: 100px;
  background-color: #f0f0f0;  // sau orice altă culoare gri
  color: black;
`;


const Message = styled.div`
  margin-bottom: 5px;
  background-color: #f0f0f0;
  padding: 5px;
  border-radius: px;
  max-width:100px;
`;

const InputContainer = styled.div`
  display: flex;
  margin-top: 00px;
`;

const Input = styled.input`
  flex: 1;
  margin-right: 5px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 5px;
`;

const Button = styled.button`
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 8px 12px;
  border-radius: 5px;
  cursor: pointer;
`;

const Chat = () => {
  const [message, setMessage] = useState('');
  const [messages, setMessages] = useState([]);

  useEffect(() => {
    socket.on('chat message', (msg) => {
      setMessages((prevMessages) => [...prevMessages, msg]);
    });
  }, []);

  const sendMessage = () => {
    if (message.trim() !== '') {
      socket.emit('chat message', message);
      setMessage('');
    }
  };

  return (
    <ChatContainer>
      <h2>Chat</h2>
      <MessageContainer>
        {messages.map((msg, index) => (
          <Message key={index}>{msg}</Message>
        ))}
      </MessageContainer>
      <InputContainer>
        <Input
          type="text"
          value={message}
          onChange={(e) => setMessage(e.target.value)}
          placeholder="Type your message..."
        />
        <Button onClick={sendMessage}>Send</Button>
      </InputContainer>
    </ChatContainer>
  );
};

export default Chat;
