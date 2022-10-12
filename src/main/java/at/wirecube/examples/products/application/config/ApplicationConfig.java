package at.wirecube.examples.products.application.config;

import at.wirecube.examples.products.application.enums.Vat;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class ApplicationConfig {

    @PersistenceContext
    private EntityManager em;

    @Bean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(em);
    }

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
