package ru.itsphere.simplechat.controllers.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.itsphere.simplechat.controllers.forms.MessageForm;

@Component
public class MessageFromValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return MessageForm.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "valid.author.empty");
        MessageForm message = (MessageForm) target;
        if (message.getText().length() < 5 || message.getText().length() > 20) {
            errors.rejectValue("text", "valid.text.length");
        }
    }
}
