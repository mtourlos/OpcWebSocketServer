package com.mas.model;

import java.io.Serializable;

public class Data implements Serializable{
    private static String anal;
    private static String bool;
    
    
    public Data(){
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