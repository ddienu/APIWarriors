package com.diegonunez.warriors.exception.Unchecked;

public class EntityInUseException extends RuntimeException{

    public EntityInUseException(String message){
        super(message);
    }
}
