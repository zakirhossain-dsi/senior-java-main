package at.wirecube.examples.products.application.converter;

import at.wirecube.examples.products.application.enums.SortOrder;
import at.wirecube.examples.products.application.exception.EnumValidationException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SortOrderConverter implements Converter<String, SortOrder> {

    @Override
    public SortOrder convert(String source) {
        try {
            return SortOrder.valueOf(source.toUpperCase());
        } catch (Exception exception) {
            throw new EnumValidationException(String.format("%s is an invalid SortOrder. Possible values are: %s", source, Arrays.toString(SortOrder.values())));
        }
    }
}
