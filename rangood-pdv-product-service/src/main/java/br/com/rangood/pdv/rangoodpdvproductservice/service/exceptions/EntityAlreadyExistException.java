package br.com.rangood.pdv.rangoodpdvproductservice.service.exceptions;

public class EntityAlreadyExistException extends Exception{

    public EntityAlreadyExistException(Throwable cause) {
        super("Already exist entity", cause);
    }
}
