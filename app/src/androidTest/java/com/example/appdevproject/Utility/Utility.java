package com.example.appdevproject.Utility;

public class Utility {
    public static void sleepMe(int seconds){
        try{
            Thread.sleep(seconds*1000);
        }catch (Exception e){}

    }


}
