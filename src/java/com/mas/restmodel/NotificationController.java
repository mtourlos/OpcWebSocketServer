package com.mas.restmodel;

public class NotificationController {
    
    //subscribe
    public void subscribe(Notification notification){
        System.out.println("New user subscribed with tokendId:"+notification.getTokenId());
        
    }
    
    //unsubscribe
    public void unsubscribe(Notification notification){
        System.out.println("User unsubscribed:"+notification.getTokenId());
    }
    
    //broadcast message
    public void broadcast(Notification notification){
        System.out.println("Broadcasting message:"+notification.getTokenId());
    }
    
}
