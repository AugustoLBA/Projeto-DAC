package br.ifpb.dac.library_web.exception.infra;

import lombok.Getter;

@Getter
public enum MessageKeyEnum {

    USER_NOT_FOUND_WITH_ID("User not found with ID:"),
    USER_NOT_FOUND_WITH_EMAIL("User not found with email:"),
    INVALID_FIELDS("Invalid field(s)"),
    SURVEY_NOT_FOUND_WITH_ID("Survey not found with ID:"),
    THE_NAME_CANNOT_BE_EMPTY("The name cannot be empty"),
    THE_EMAIL_CANNOT_BE_EMPTY("The e-mail cannot be empty"),
    THE_PASSWORD_CANNOT_BE_EMPTY("The password cannot be empty"),
    THE_TYPER_USER_CANNOT_BE_EMPTY("The typer user cannot be empty"),
    THE_EMAIL_FORMAT_IS_INVALID("The e-mail format is invalid");

    private String message;

    MessageKeyEnum(String message) {
        this.message = message;
    }

    public String getMessage(Object aux){
        return message +" "+ aux;
    }

}
