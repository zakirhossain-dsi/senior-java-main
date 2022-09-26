package at.wirecube.examples.products.application.annotation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

@Component
public class StringOptionsValidator implements ConstraintValidator<StringOptions, String> {

    private static final String ERROR_MESSAGE = "value should be one of the followings: %s";
    private List<String> options;

    @Override
    public void initialize(StringOptions annotation) {

        this.options = Arrays.asList(annotation.values());
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {

        if (options.contains(value)) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(String.format(ERROR_MESSAGE, options))
                .addConstraintViolation();

        return false;
    }
}