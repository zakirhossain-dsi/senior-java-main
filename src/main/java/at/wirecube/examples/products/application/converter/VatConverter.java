package at.wirecube.examples.products.application.converter;

import at.wirecube.examples.products.application.enums.Vat;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class VatConverter implements AttributeConverter<Vat, String> {

    @Override
    public String convertToDatabaseColumn(Vat vat) {
        if (Objects.isNull(vat)) {
            return null;
        }
        return vat.getValue();
    }

    @Override
    public Vat convertToEntityAttribute(String vatValue) {
        if (Objects.isNull(vatValue)) {
            return null;
        }
        return Stream.of(Vat.values()).filter(vat -> vat.getValue().equalsIgnoreCase(vatValue))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
