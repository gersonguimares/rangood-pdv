package br.com.rangood.pdv.rangoodpdvproductservice.expections;

public class NoMacthAttributeModelException  extends Exception{

    public NoMacthAttributeModelException(String message) {
        super(message);
    }

    public NoMacthAttributeModelException(String message, Throwable cause) {
        super(message, cause);
    }
}
