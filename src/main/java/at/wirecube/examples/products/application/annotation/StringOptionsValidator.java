package at.wirecube.examples.products.application.annotation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

@Component
public class StringOptionsValidator implements ConstraintValidator<StringOptions, String> {

    private static final String ERROR_MESSAGE = "must be any of %s";
    private List<String> options;

    @Override
    public void initialize(StringOptions annotation) {

        this.options = Arrays.asList(annotation.values());
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {

        context.buildConstraintViolationWithTemplate(String.format(ERROR_MESSAGE, options))
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return options.contains(value);
    }
}
