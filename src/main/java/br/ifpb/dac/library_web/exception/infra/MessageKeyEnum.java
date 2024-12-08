package br.ifpb.dac.library_web.exception.infra;

import lombok.Getter;

@Getter
public enum MessageKeyEnum {

    USER_NOT_FOUND_WITH_ID("User not found with ID:"),
    USER_NOT_FOUND_WITH_EMAIL("User not found with email:"),
    INVALID_FIELDS("Invalid field(s)"),
    SURVEY_NOT_FOUND_WITH_ID("Survey not found with ID:"),
    SURVEY_NOT_FOUND_WITH_EMAIL("Survey not found with email:");

    private String message;

    MessageKeyEnum(String message) {
    }

    public String getMessage(Object aux){
        return message +" "+ aux;
    }

}
