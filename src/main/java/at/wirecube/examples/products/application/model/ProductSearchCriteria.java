package at.wirecube.examples.products.application.model;

import at.wirecube.examples.products.application.annotation.StringOptions;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Builder
public class ProductSearchCriteria {

    @Min(1)
    private Integer page = 1;

    @Max(50)
    private Integer size = 20;

    @StringOptions(values = {"id", "name", "price", "vat"})
    private String sortBy = "name";

    @StringOptions(values = {"asc", "ASC", "desc", "DESC"})
    private String sortOrder = "asc";

}
