package fr.diginamic.hello.exception;

public class InvalidRequestParameterException extends Exception{
    public InvalidRequestParameterException(String message) {
        super(message);
    }
}
