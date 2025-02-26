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
    THE_EMAIL_FORMAT_IS_INVALID("The e-mail format is invalid"),
    BOOK_NOT_FOUND_WITH_ID("Book not found with ID:"),
    BOOK_NOT_FOUND_WITH_TITLE("Book not found with title:"),
    EXEMPLARY_NOT_FOUND_WITH_ID("Exemplary not found with ID:"),
    AUTHOR_WITH_ID_NOT_FOUND("Author with id not found:"),
    PUBLISHER_WITH_ID_NOT_FOUND("Publisher with id not found:"),
    ADDRESS_NOT_FOUND_WITH_ID("Address with id not found:"),
    CONTRACT_NOT_FOUND_WITH_ID("Contract with id not found:"),
    LIBRARY_NOT_FOUND_WITH_ID("Library with id not found:"),
    ONE_OR_MORE_CLAUSES_NOT_FOUND("One or more clauses not found:");


    private String message;

    MessageKeyEnum(String message) {
        this.message = message;
    }

    public String getMessage(Object aux){
        return message +" "+ aux;
    }

}
