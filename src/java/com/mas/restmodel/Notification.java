package com.mas.restmodel;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Notification implements Serializable {
    private String tokenId;
    
    public String getTokenId(){
        return tokenId;
    }
    
    public void setTokendId(String tokenId){
        this.tokenId = tokenId;
    }
    
}
