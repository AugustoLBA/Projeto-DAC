package br.ifpb.dac.library_web.exception.infra;

import lombok.Getter;

@Getter
public enum MessageKeyEnum {

    USER_NOT_FOUND_WITH_ID("User not found with ID:"),
    USER_NOT_FOUND_WITH_EMAIL("User not found with email:"),
    INVALID_FIELDS("Invalid field(s)");
    private String message;

    MessageKeyEnum(String message) {
    }

    public String getMessage(Object aux){
        return message +" "+ aux;
    }

}
