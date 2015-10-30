package com.mas.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.json.spi.JsonProvider;
import javax.websocket.Session;
import com.mas.model.Data;
import com.mas.opc.OpcClient;

@ApplicationScoped
public class DataSessionHandler {
    private final Set<Session> sessions = new HashSet<Session>();
    //private static boolean opcRunning = false;
    //OpcClient opc = new OpcClient();
    
    public void sendData(Session session){
        try {
             Thread.sleep(1000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        JsonObject message = buildJSONData();
        sendToSession(session,message);
    }
    
    public void removeSession(Session session){
        for (Session ses : sessions){
            if (session.equals(ses)){
                sessions.remove(ses);
            }
        }
        try{
            session.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void addSession(Session session) {
        sessions.add(session);
        /*try{
            opc.connect();
        }catch(Exception e){
            e.printStackTrace();
        }*/
        if (OpcClient.getRunning()==false){
            OpcClient opc = new OpcClient();
            opc.start();
            /*try{
                //opc.connect();
            }catch(Exception e){
                e.printStackTrace();
            }*/
        }
    }
    public JsonObject buildJSONData(){
        JsonProvider provider = JsonProvider.provider();
        JsonObject dataMessage = provider.createObjectBuilder()
                .add("analog", Data.getAnal())
                .add("boolean",Data.getBool())
                .build();
        return dataMessage;
    }
     
    private void sendToSession(Session session, JsonObject message) {
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            sessions.remove(session);
            Logger.getLogger(DataSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Message sent");
    }
    
    
}
