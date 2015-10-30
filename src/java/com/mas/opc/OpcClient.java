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
import com.mas.model.Data;

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
        String[] tagList = new String[2];
        tagList[0] = "Channel1.Device1.Tag1";
        tagList[1] = "Channel1.Device1.Tag2";
        // create a new server
        final Server server = new Server(ci, Executors.newSingleThreadScheduledExecutor());
        try {
            // connect to server
            server.connect();
            // add sync access, poll every 500 ms
            final AccessBase access = new SyncAccess(server, 500);
        
            addItem(tagList[0],access,1);
            addItem(tagList[1],access,2);
        
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
		             if (state.getValue().getType() == JIVariant.VT_UI4) {
                                 String s = String.valueOf(state.getValue().getObjectAsUnsigned().getValue());
                                 if (element==1)
                                    Data.setAnal(s);
                                 else if (element==2)
                                    Data.setBool(s);
		                 //System.out.println("value1:"+String.valueOf(state.getValue().getObjectAsUnsigned().getValue()));
		             } else {
                                 String s = String.valueOf(state.getValue().getObject());
                                 if (element==1)
                                    Data.setAnal(s);
                                 else if (element==2) 
                                    Data.setBool(s);
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
