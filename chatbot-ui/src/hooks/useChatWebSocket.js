import { useState, useEffect } from "react";
import SockJS from "sockjs-client";
import { Client } from "@stomp/stompjs";

const useChatWebSocket = (onMessageReceived) => {
  const [stompClient, setStompClient] = useState(null);

  useEffect(() => {
    const socket = new SockJS("http://localhost:8080/chat"); // Ensure backend allows this
    const client = new Client({
      webSocketFactory: () => socket,
      reconnectDelay: 5000, // Auto-reconnect after 5s if disconnected
      onConnect: (frame) => {
        console.log("Connected to WebSocket:", frame);

        // Subscribe to the topic where messages are received
        client.subscribe("/topic/messages", (message) => {
          onMessageReceived(JSON.parse(message.body));
        });
      },
      onStompError: (frame) => {
        console.error("WebSocket STOMP Error:", frame);
      },
      onWebSocketError: (error) => {
        console.error("WebSocket Connection Error:", error);
      },
    });

    client.activate(); // Connect WebSocket

    setStompClient(client);

    return () => {
      if (client) {
        client.deactivate(); // Cleanup on unmount
      }
    };
  }, [onMessageReceived]);

  const sendMessage = (message) => {
    if (stompClient && stompClient.connected) {
      stompClient.publish({
        destination: "/app/chat",
        body: JSON.stringify({ content: message }),
      });
    } else {
      console.error("WebSocket is not connected.");
    }
  };

  return { sendMessage };
};

export default useChatWebSocket;

