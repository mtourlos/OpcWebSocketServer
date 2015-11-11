package com.mas.restservice;

import javax.ws.rs.*;
import com.mas.restmodel.*;
import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Path("/notificationservice")

public class NotificationService {
    private NotificationController notificationC = new NotificationController();
    
    @Path("/test")
    @GET
    @Produces("text/plain")
    public String test(){
        return "testok";
    }
    
    @Path("/subscribe")
    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    public String subscribe(Notification notification){
        notificationC.subscribe(notification);
        return "ok";
    }
    
    @Path("/unsubscribe")
    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    public String unsubscribe(Notification notification){
        notificationC.unsubscribe(notification);
        return "ok";
    }
    
    @Path("/broadcast")
    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    public String sendNotification(Notification notification){
        notificationC.broadcast(notification);
        return "ok";
    }
    
}
