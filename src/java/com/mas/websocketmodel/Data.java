package com.mas.websocketmodel;

import java.io.Serializable;

public class Data implements Serializable{
    private static String anal;
    private static String bool;
    private static String[]  values = new String[22]; 
    
    public Data(){
    }

    public static String getValues(int id) {
        return values[id];
    }

    public static void setValues(String values, int id) {
        Data.values[id] = values;
    }
    
    
    
    public static String getAnal(){
        return anal;
    }
    
    public static void setAnal(String anal){
        Data.anal=anal;
    }
    
    public static String getBool(){
        return bool;
    }
    
    public static void setBool(String bool){
        Data.bool=bool;
    }
}