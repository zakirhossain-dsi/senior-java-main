package at.wirecube.examples.products.application.config;

import at.wirecube.examples.products.application.enums.Vat;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {

        Converter<Vat, String> toVatValue = new AbstractConverter<>() {
            protected String convert(Vat source) {
                return source == null ? null : source.getValue();
            }
        };

        Converter<String, Vat> toVatEnum = new AbstractConverter<>() {
            protected Vat convert(String source) {
                return source == null ? null : Vat.create(source);
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(toVatValue);
        modelMapper.addConverter(toVatEnum);
        return modelMapper;
    }
}
