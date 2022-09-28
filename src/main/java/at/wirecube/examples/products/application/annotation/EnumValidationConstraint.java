package at.wirecube.examples.products.application.annotation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class EnumValidationConstraint implements ConstraintValidator<EnumValidation, String> {

    private static final String ERROR_MESSAGE = "must be any of %s";
    Set<String> values;

    @Override
    public void initialize(EnumValidation constraintAnnotation) {
        values = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        context.buildConstraintViolationWithTemplate(String.format(ERROR_MESSAGE, values))
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return values.contains(value.toUpperCase());
    }
}
