package com.mas.websocketmodel;

import java.io.Serializable;

public class Data implements Serializable{
    private static String anal;
    private static String bool;
    private static String alarmOverFrequency;
    private static String alarmOverVoltage;
    private static String alarmUnderFrequency;
    private static String alarmUnderVoltage;
    private static String alarmAspCommerror;
    private static String alarmEnerconCommerror;
    private static String alarmWindBurstError;
    private static String alarmHighWindSpeed;
    private static String alarmCosfError;
    private static String mVBreakerClosed;
    private static String towerBreakerClosed;
    private static String kw;
    private static String setpoint;
    private static String setpointMode;
    private static String v1;
    private static String windSpeed;
    private static String wg1Run;
    private static String wg1V1;
    private static String wg1Kw;
    private static String wg2Run;
    private static String wg2V1;
    private static String wg2Kw;
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
    
    public static String getAlarmOverFrequency() {
        return alarmOverFrequency;
    }

    public static String getAlarmOverVoltage() {
        return alarmOverVoltage;
    }

    public static String getAlarmUnderFrequency() {
        return alarmUnderFrequency;
    }

    public static String getAlarmUnderVoltage() {
        return alarmUnderVoltage;
    }

    public static String getAlarmAspCommerror() {
        return alarmAspCommerror;
    }

    public static String getAlarmEnerconCommerror() {
        return alarmEnerconCommerror;
    }

    public static String getAlarmWindBurstError() {
        return alarmWindBurstError;
    }

    public static String getAlarmHighWindSpeed() {
        return alarmHighWindSpeed;
    }

    public static String getAlarmCosfError() {
        return alarmCosfError;
    }

    public static String getmVBreakerClosed() {
        return mVBreakerClosed;
    }

    public static String getTowerBreakerClosed() {
        return towerBreakerClosed;
    }

    public static String getKw() {
        return kw;
    }

    public static String getSetpoint() {
        return setpoint;
    }

    public static String getSetpointMode() {
        return setpointMode;
    }

    public static String getV1() {
        return v1;
    }

    public static String getWindSpeed() {
        return windSpeed;
    }

    public static String getWg1Run() {
        return wg1Run;
    }

    public static String getWg1V1() {
        return wg1V1;
    }

    public static String getWg1Kw() {
        return wg1Kw;
    }

    public static String getWg2Run() {
        return wg2Run;
    }

    public static String getWg2V1() {
        return wg2V1;
    }

    public static String getWg2Kw() {
        return wg2Kw;
    }

    public static void setAlarmOverFrequency(String alarmOverFrequency) {
        Data.alarmOverFrequency = alarmOverFrequency;
    }

    public static void setAlarmOverVoltage(String alarmOverVoltage) {
        Data.alarmOverVoltage = alarmOverVoltage;
    }

    public static void setAlarmUnderFrequency(String alarmUnderFrequency) {
        Data.alarmUnderFrequency = alarmUnderFrequency;
    }

    public static void setAlarmUnderVoltage(String alarmUnderVoltage) {
        Data.alarmUnderVoltage = alarmUnderVoltage;
    }

    public static void setAlarmAspCommerror(String alarmAspCommerror) {
        Data.alarmAspCommerror = alarmAspCommerror;
    }

    public static void setAlarmEnerconCommerror(String alarmEnerconCommerror) {
        Data.alarmEnerconCommerror = alarmEnerconCommerror;
    }

    public static void setAlarmWindBurstError(String alarmWindBurstError) {
        Data.alarmWindBurstError = alarmWindBurstError;
    }

    public static void setAlarmHighWindSpeed(String alarmHighWindSpeed) {
        Data.alarmHighWindSpeed = alarmHighWindSpeed;
    }

    public static void setAlarmCosfError(String alarmCosfError) {
        Data.alarmCosfError = alarmCosfError;
    }

    public static void setmVBreakerClosed(String mVBreakerClosed) {
        Data.mVBreakerClosed = mVBreakerClosed;
    }

    public static void setTowerBreakerClosed(String towerBreakerClosed) {
        Data.towerBreakerClosed = towerBreakerClosed;
    }

    public static void setKw(String kw) {
        Data.kw = kw;
    }

    public static void setSetpoint(String setpoint) {
        Data.setpoint = setpoint;
    }

    public static void setSetpointMode(String setpointMode) {
        Data.setpointMode = setpointMode;
    }

    public static void setV1(String v1) {
        Data.v1 = v1;
    }

    public static void setWindSpeed(String windSpeed) {
        Data.windSpeed = windSpeed;
    }

    public static void setWg1Run(String wg1Run) {
        Data.wg1Run = wg1Run;
    }

    public static void setWg1V1(String wg1V1) {
        Data.wg1V1 = wg1V1;
    }

    public static void setWg1Kw(String wg1Kw) {
        Data.wg1Kw = wg1Kw;
    }

    public static void setWg2Run(String wg2Run) {
        Data.wg2Run = wg2Run;
    }

    public static void setWg2V1(String wg2V1) {
        Data.wg2V1 = wg2V1;
    }

    public static void setWg2Kw(String wg2Kw) {
        Data.wg2Kw = wg2Kw;
    }
        
}