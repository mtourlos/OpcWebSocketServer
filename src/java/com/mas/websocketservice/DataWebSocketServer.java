package com.mas.websocketservice;

import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
@ServerEndpoint("/actions")
public class DataWebSocketServer {
    
    @Inject
    private DataSessionHandler sessionHandler;
    
    @OnOpen
        public void open(Session session) {
            System.out.println("New user connected:"+session.getId());
            sessionHandler.addSession(session);
            sessionHandler.sendData(session);
    }

    @OnClose
        public void close(Session session) {
            System.out.println("User disconnected :"+session.getId());
            sessionHandler.removeSession(session);
    }

    @OnError
        public void onError(Throwable error) {
            System.out.println(error);
            Logger.getLogger(DataWebSocketServer.class.getName()).log(Level.SEVERE, null, error);
    }

    @OnMessage
        public void handleMessage(String message, Session session) {
            try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();

            if ("stop".equals(jsonMessage.getString("action"))) {
                close(session);
            }

            if ("ok".equals(jsonMessage.getString("action"))) {
                sessionHandler.sendData(session);
                System.out.println("ok received");
            }
        }
        
    }
    
}
