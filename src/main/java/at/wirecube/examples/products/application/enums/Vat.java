package at.wirecube.examples.products.application.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

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
    public static Vat create(String valueStr){
        if(valueStr.isEmpty()){
            return null;
        }

        return Stream.of(Vat.values())
                .filter(vatEnum -> vatEnum.getValue().equalsIgnoreCase(valueStr))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
