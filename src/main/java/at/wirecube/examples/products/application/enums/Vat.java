package at.wirecube.examples.products.application.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum Vat {
    @JsonProperty("10")
    TEN("10"),

    @JsonProperty("18")
    EIGHTEEN("18"),

    @JsonProperty("20")
    TWENTY("20");

    private final String value;

    Vat(String value){
        this.value = value;
    }
}
