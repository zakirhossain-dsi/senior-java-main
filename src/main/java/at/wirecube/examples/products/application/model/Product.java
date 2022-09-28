package at.wirecube.examples.products.application.model;

import at.wirecube.examples.products.application.annotation.VatValidation;
import at.wirecube.examples.products.application.validation.OnCreate;
import at.wirecube.examples.products.application.validation.OnUpdate;
import at.wirecube.examples.products.application.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(defaultValue = "1")
    @JsonView(value = {View.ProductView.PUT.class})
    private Integer id;

    @NotEmpty
    @Schema(defaultValue = "iPhone", required = true)
    @JsonView(value = {View.ProductView.POST.class})
    private String name;

    @NotNull
    @Schema(defaultValue = "100", required = true)
    @JsonView(value = {View.ProductView.POST.class})
    private Double price;

    @Schema(defaultValue = "Powerful. Beautiful. Durable.")
    @JsonView(value = {View.ProductView.POST.class})
    private String description;

    @VatValidation
    @Schema(defaultValue = "10")
    @JsonView(value = {View.ProductView.POST.class})
    private String vat;
}
