package br.com.rangood.pdv.rangoodpdvordermanagementservice.service.exceptions;

public class EntityAlreadyExistException extends Exception{

    public EntityAlreadyExistException(Throwable cause) {
        super("Already exist entity", cause);
    }
}
