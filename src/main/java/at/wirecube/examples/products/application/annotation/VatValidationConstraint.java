package at.wirecube.examples.products.application.annotation;

import at.wirecube.examples.products.application.enums.Vat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class VatValidationConstraint implements ConstraintValidator<VatValidation, String> {

    private static final String ERROR_MESSAGE = "must be any of %s";
    Set<String> values;

    @Override
    public void initialize(VatValidation constraintAnnotation) {
        values = Arrays.stream(Vat.values()).map(Vat::getValue).collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        context.buildConstraintViolationWithTemplate(String.format(ERROR_MESSAGE, values))
                .addConstraintViolation();

        return StringUtils.isEmpty(value) || values.contains(value.toUpperCase());
    }
}
