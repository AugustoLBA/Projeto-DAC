package br.ifpb.dac.library_web.exception.infra;

import jakarta.validation.Payload;

public @interface LibraryWebRequiredField {
    MessageKeyEnum messageKey(); // Recebe o enum da mensagem

    String message() default "Campo obrigatório"; // Mensagem padrão, pode ser substituída

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
