package ru.portfolio.library.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.portfolio.library.annotation.CustomSize;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.time.Year;
@Component
public class CustomSizeValidator implements ConstraintValidator<CustomSize, Integer> {

    private int min;

    @Override
    public void initialize(CustomSize constraintAnnotation) {
        this.min = constraintAnnotation.min();
    }
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        int currentYear = Year.now().getValue();
        int max = currentYear;

        try {
            Field messageField = context.getClass().getDeclaredField("year of birth should be [1900...present year]");
            messageField.setAccessible(true);
            String defaultMessage = (String) messageField.get(context);

            if (value < min || value > max) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(defaultMessage)
                        .addConstraintViolation();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}