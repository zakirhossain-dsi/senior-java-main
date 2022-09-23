package at.wirecube.examples.products.application.enums;

import lombok.Getter;

@Getter
public enum Vat {
    TEN(10), EIGHTEEN(18), TWENTY(20);

    private final int value;

    Vat(int value){
        this.value = value;
    }

}
