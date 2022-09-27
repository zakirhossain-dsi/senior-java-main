package at.wirecube.examples.products.application.enums;

import at.wirecube.examples.products.application.exception.EnumValidationException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum Vat {
    TEN("10"), EIGHTEEN("18"), TWENTY("20");

    private final String value;

    Vat(String value) {
        this.value = value;
    }

    public static Vat create(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }

        return Stream.of(Vat.values())
                .filter(vatEnum -> vatEnum.getValue().equalsIgnoreCase(source))
                .findFirst()
                .orElseThrow(() ->
                        new EnumValidationException(
                                String.format("%s is an invalid Vat. Possible values are: %s",
                                        source, Arrays.stream(Vat.values()).map(Vat::getValue).collect(Collectors.toList()))));
    }
}
