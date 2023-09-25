package com.example.appdevproject.CustomException;

public class MissingField extends Exception{
    public MissingField(){
        super("field missing");
    }
}
