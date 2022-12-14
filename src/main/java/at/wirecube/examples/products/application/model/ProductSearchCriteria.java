package at.wirecube.examples.products.application.model;

import at.wirecube.examples.products.application.annotation.EnumValidation;
import at.wirecube.examples.products.application.annotation.VatValidation;
import at.wirecube.examples.products.application.enums.SortBy;
import at.wirecube.examples.products.application.enums.SortOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@Builder
@AllArgsConstructor
public class ProductSearchCriteria {

    @Min(1)
    private Integer page;

    @Min(1)
    private Integer size;

    @Schema(example = "name")
    @EnumValidation(enumClass = SortBy.class)
    private String sortBy;

    @Schema(example = "ASC")
    @EnumValidation(enumClass = SortOrder.class)
    private String sortOrder;

    private Double price;
    private Integer descriptionLength;

    @VatValidation
    private String vat;

    public ProductSearchCriteria() {
        this.page = 1;
        this.size = 50;
        this.sortBy = SortBy.NAME.name();
        this.sortOrder = SortOrder.ASC.name();
    }

}
