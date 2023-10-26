package com.example.appdevproject.InterFaces.CustomException;

public class MissingField extends Exception{
    public MissingField(){
        super("field missing");
    }
}
