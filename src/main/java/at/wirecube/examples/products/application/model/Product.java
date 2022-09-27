package at.wirecube.examples.products.application.model;

import at.wirecube.examples.products.application.annotation.VatValidation;
import at.wirecube.examples.products.application.validation.OnCreate;
import at.wirecube.examples.products.application.validation.OnUpdate;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {

    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private Integer id;

    @NotEmpty
    private String name;

    @NotNull
    private Double price;

    private String description;

    @VatValidation
    private String vat;
}
