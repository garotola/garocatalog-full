package com.garosuperior.dscatalog.services.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String msg){
        super(msg);
    }
}
