package br.ifpb.dac.library_web.exception.infra;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class LibraryWebRequiredFieldValidator implements ConstraintValidator<LibraryWebRequiredField, String> {

    private MessageKeyEnum messageKey;

    @Override
    public void initialize(LibraryWebRequiredField constraintAnnotation) {
        this.messageKey = constraintAnnotation.messageKey(); // Obtém o enum da anotação
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MessageKeyEnum.valueOf(String.valueOf(messageKey)).getMessage())
                    .addConstraintViolation();
            return false; // O valor está vazio ou nulo
        }

        // Validação do formato de e-mail usando regex para garantir que o formato do e-mail seja válido
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(value).matches()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(MessageKeyEnum.THE_EMAIL_FORMAT_IS_INVALID.getMessage())
                    .addConstraintViolation();
            return false; // O e-mail não tem um formato válido
        }

        return true; // O campo não está vazio e o formato do e-mail está correto
    }


}
