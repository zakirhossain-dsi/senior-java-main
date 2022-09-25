package at.wirecube.examples.products.application.model;

import at.wirecube.examples.products.application.annotation.StringOptions;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ProductSearchCriteria {

    @Min(1)
    @NotNull
    private Integer page;

    @Min(1)
    @NotNull
    private Integer size;

    @StringOptions(values = {"id", "name", "price", "vat"})
    private String sortBy;

    @StringOptions(values = {"asc", "ASC", "desc", "DESC"})
    private String sortOrder;

}
