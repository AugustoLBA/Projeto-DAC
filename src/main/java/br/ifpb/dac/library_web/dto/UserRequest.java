package br.ifpb.dac.library_web.dto;

import br.ifpb.dac.library_web.enumeration.TypeUser;
import br.ifpb.dac.library_web.exception.infra.LibraryWebRequiredField;
import br.ifpb.dac.library_web.exception.infra.MessageKeyEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRequest {
    @LibraryWebRequiredField(messageKey = MessageKeyEnum.THE_NAME_CANNOT_BE_EMPTY)
    private String name;

    @LibraryWebRequiredField(messageKey = MessageKeyEnum.THE_EMAIL_CANNOT_BE_EMPTY)
    private String email;

    @LibraryWebRequiredField(messageKey = MessageKeyEnum.THE_PASSWORD_CANNOT_BE_EMPTY)
    private String password;

    @LibraryWebRequiredField(messageKey = MessageKeyEnum.THE_TYPER_USER_CANNOT_BE_EMPTY)
    private TypeUser type;

    private AdressRequest adress;
}
