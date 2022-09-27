package at.wirecube.examples.products.application.annotation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class EnumValidatorConstraint implements ConstraintValidator<EnumValidator, String> {

    private static final String ERROR_MESSAGE = "must be any of %s";
    Set<String> values;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        values = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        context.buildConstraintViolationWithTemplate(String.format(ERROR_MESSAGE, values))
                .addConstraintViolation();

        return values.contains(value.toUpperCase());
    }
}
