import React, { useState, useEffect } from 'react';
import { Client } from '@stomp/stompjs';
import './App.css';

function App() {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState('');
  const [client, setClient] = useState(null);

  useEffect(() => {
    const stompClient = new Client({
      brokerURL: 'ws://localhost:8080/chat',
      reconnectDelay: 5000,
    });

    stompClient.onConnect = () => {
      console.log('Connected');
      stompClient.subscribe('/topic/messages', (message) => {
        setMessages((prev) => [...prev, JSON.parse(message.body)]);
      });
    };

    stompClient.activate();
    setClient(stompClient);

    return () => stompClient.deactivate();
  }, []);

  const sendMessage = () => {
    if (client && input.trim()) {
      client.publish({
        destination: '/app/chat',
        body: JSON.stringify({ sender: 'User', content: input }),
      });
      setInput('');
    }
  };

  return (
    <div className="App">
      <h1>Chat Application</h1>
      <div className="chat-box">
        {messages.map((msg, index) => (
          <p key={index}><strong>{msg.sender}:</strong> {msg.content}</p>
        ))}
      </div>
      <input
        type="text"
        value={input}
        onChange={(e) => setInput(e.target.value)}
        placeholder="Type a message..."
      />
      <button onClick={sendMessage}>Send</button>
    </div>
  );
}

export default App;

