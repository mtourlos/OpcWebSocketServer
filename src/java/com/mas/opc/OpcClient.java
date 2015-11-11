package com.mas.opc;

import java.util.concurrent.Executors;
import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.openscada.opc.lib.common.AlreadyConnectedException;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.common.NotConnectedException;
import org.openscada.opc.lib.da.AccessBase;
import org.openscada.opc.lib.da.AddFailedException;
import org.openscada.opc.lib.da.DataCallback;
import org.openscada.opc.lib.da.DuplicateGroupException;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.ItemState;
import org.openscada.opc.lib.da.Server;
import org.openscada.opc.lib.da.SyncAccess;
import com.mas.websocketmodel.Data;

public class OpcClient extends Thread{
    private static boolean running;
    
    public OpcClient(){
        
    }
    
    public void run(){
        System.out.println("Opc Client Thread Started");
        try{
            connect();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void connect()throws Exception{    
        // create connection information
        final ConnectionInformation ci = new ConnectionInformation();
        ci.setHost("10.10.10.62");
        ci.setDomain("");
        ci.setUser("mtourlos");
        ci.setPassword("egwimioxristis");
        ci.setProgId("SWToolbox.TOPServer");
        //ci.setClsid("680DFBF7-C92D-484D-84BE-06DC3DECCD68"); // if ProgId is not working, try it using the Clsid instead
        String[] tagList = new String[22];
        tagList[0] = "enercon.digital.Alarm-OverFrequency";
        tagList[1] = "enercon.digital.Alarm-OverVoltage";
        tagList[2] = "enercon.digital.Alarm-UnderFrequency";
        tagList[3] = "enercon.digital.Alarm-UnderVoltage";
        tagList[4] = "enercon.digital.Alarm-AspCommerror";
        tagList[5] = "enercon.digital.Alarm-EnerconCommerror";
        tagList[6] = "enercon.digital.Alarm-WindBurstError";
        tagList[7] = "enercon.digital.Alarm-HighWindSpeed";
        tagList[8] = "enercon.digital.Alarm-CosFError";
        tagList[9] = "enercon.digital.MV_Breaker_Closed";
        tagList[10] = "enercon.digital.Tower_Breaker_Closed";
        tagList[11] = "enercon.totals.kw";
        tagList[12] = "enercon.totals.setpoint";
        tagList[13] = "enercon.totals.setpoint_mode";
        tagList[14] = "enercon.totals.v1";
        tagList[15] = "enercon.totals.wind_speed";
        tagList[16] = "enercon.wg1.run";
        tagList[17] = "enercon.wg1.v1";
        tagList[18] = "enercon.wg1.kw";
        tagList[19] = "enercon.wg2.run";
        tagList[20] = "enercon.wg2.v1";
        tagList[21] = "enercon.wg2.kw";
        // create a new server
        final Server server = new Server(ci, Executors.newSingleThreadScheduledExecutor());
        try {
            // connect to server
            server.connect();
            // add sync access, poll every 500 ms
            final AccessBase access = new SyncAccess(server, 500);
            for(int i=0;i<tagList.length;i++){
                addItem(tagList[i],access,i);
            }
            
            
            // start reading
            //System.out.println("test");
            access.bind();
            // wait a little bit
            running = true;
            while (running){
                Thread.sleep(10 * 100);
            }
            //stop reading
            //access.unbind();
            
            
        } catch (final JIException e) {
            //System.out.println(String.format("%08X: %s", e.getErrorCode(), server.getErrorMessage(e.getErrorCode())));
         }
    
   
}

    private static void addItem(String itemId, AccessBase access,int element){
	try {
           
		access.addItem(itemId, new DataCallback() {
		    @Override
		    public void changed(Item item, ItemState state) {
		    	 try {
		             if (state.getValue().getType() == JIVariant.VT_UI4 || state.getValue().getType()==JIVariant.VT_UI2) {
                                 //String s = String.valueOf(state.getValue().getObjectAsUnsigned().getValue());
                                 String s =state.getValue().getObjectAsUnsigned().getValue().toString();
                                 Data.setValues(s, element);
		                 //System.out.println("value1:"+String.valueOf(state.getValue().getObjectAsUnsigned().getValue()));
		             } else {
                                 String s = String.valueOf(state.getValue().getObject());
                                 //String s =state.getValue().getObjectAsUnsigned().getValue().toString();
                                 Data.setValues(s, element);
		                 //System.out.println("value2:"+String.valueOf(state.getValue().getObject()));
		             }
		         } catch (JIException e) {
		             //e.printStackTrace();
		         }
		    }
		});
	} catch (JIException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
	} catch (AddFailedException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
	}
    }
    
    public static boolean getRunning(){
        return running;
    }
    
    public static void setRunning(boolean set){
        OpcClient.running=set;
    }
}
