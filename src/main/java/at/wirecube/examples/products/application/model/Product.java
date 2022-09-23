package at.wirecube.examples.products.application.model;

import at.wirecube.examples.products.application.enums.Vat;
import at.wirecube.examples.products.application.validation.OnCreate;
import at.wirecube.examples.products.application.validation.OnUpdate;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class Product {

    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private Integer id;

    @NotEmpty
    private String name;

    @NotNull
    private Double price;

    private String description;

    private Vat vat;
}
