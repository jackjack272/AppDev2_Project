package com.example.appdevproject.Utility.CustomException;

public class MissingField extends Exception{
    public MissingField(){
        super("field missing");
    }
}
