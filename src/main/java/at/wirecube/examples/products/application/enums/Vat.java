package at.wirecube.examples.products.application.enums;

import at.wirecube.examples.products.application.exception.EnumValidationException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum Vat {
    @JsonProperty("10")
    TEN("10"),

    @JsonProperty("18")
    EIGHTEEN("18"),

    @JsonProperty("20")
    TWENTY("20");

    private final String value;

    Vat(String value) {
        this.value = value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Vat create(String source) {
        if (source.isEmpty()) {
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
