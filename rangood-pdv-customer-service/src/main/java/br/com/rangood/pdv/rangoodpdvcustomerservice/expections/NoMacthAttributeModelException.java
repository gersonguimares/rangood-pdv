package br.com.rangood.pdv.rangoodpdvcustomerservice.expections;

public class NoMacthAttributeModelException  extends Exception{

    public NoMacthAttributeModelException(String message) {
        super(message);
    }

    public NoMacthAttributeModelException(String message, Throwable cause) {
        super(message, cause);
    }
}
