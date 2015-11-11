package com.mas.websocketservice;

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
import com.mas.websocketmodel.Data;
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
                /*.add("alarmOverFrequency", Data.getAlarmOverFrequency())
                .add("alarmOverVoltage",Data.getAlarmOverVoltage())
                .add("alarmUnderFrequency",Data.getAlarmUnderFrequency())
                .add("alarmUnderVoltage",Data.getAlarmUnderVoltage())
                .add("alarmAspCommerror",Data.getAlarmAspCommerror())
                .add("alarmEnerconCommerror",Data.getAlarmEnerconCommerror())
                .add("alarmWindBurstError",Data.getAlarmWindBurstError())
                .add("alarmHighWindSpeed",Data.getAlarmHighWindSpeed())
                .add("alarmsCosfError",Data.getAlarmCosfError())
                .add("mvBreakerClosed",Data.getmVBreakerClosed())
                .add("towerBreakerClosed",Data.getTowerBreakerClosed())
                .add("kw",Data.getKw())
                .add("setpoint",Data.getSetpoint())
                .add("setpointMode",Data.getSetpointMode())
                .add("v1",Data.getV1())
                .add("windSpeed",Data.getWindSpeed())
                .add("wg1Run",Data.getWg1Run())
                .add("wg1V1",Data.getWg1V1())
                .add("wg1Kw",Data.getWg1Kw())
                .add("wg2Run",Data.getWg2Run())
                .add("wg2V1",Data.getWg2V1())
                .add("wg2Kw",Data.getWg2Kw())*/
                
                .add("alarmOverFrequency", Data.getValues(0))
                .add("alarmOverVoltage",Data.getValues(1))
                .add("alarmUnderFrequency",Data.getValues(2))
                .add("alarmUnderVoltage",Data.getValues(3))
                .add("alarmAspCommerror",Data.getValues(4))
                .add("alarmEnerconCommerror",Data.getValues(5))
                .add("alarmWindBurstError",Data.getValues(6))
                .add("alarmHighWindSpeed",Data.getValues(7))
                .add("alarmCosfError",Data.getValues(8))
                .add("mvBreakerClosed",Data.getValues(9))
                .add("towerBreakerClosed",Data.getValues(10))
                .add("kw",Data.getValues(11))
                .add("setpoint",Data.getValues(12))
                .add("setpointMode",Data.getValues(13))
                .add("v1",Data.getValues(14))
                .add("windSpeed",Data.getValues(15))
                .add("wg1Run",Data.getValues(16))
                .add("wg1V1",Data.getValues(17))
                .add("wg1Kw",Data.getValues(18))
                .add("wg2Run",Data.getValues(19))
                .add("wg2V1",Data.getValues(20))
                .add("wg2Kw",Data.getValues(21))
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
