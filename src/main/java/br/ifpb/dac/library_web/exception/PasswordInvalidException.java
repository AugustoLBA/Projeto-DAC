package br.ifpb.dac.library_web.exception;

public class PasswordInvalidException extends RuntimeException{

    public PasswordInvalidException(String message){
        super(message);
    }
}
