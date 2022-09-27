package at.wirecube.examples.products.application.model;

import at.wirecube.examples.products.application.enums.SortBy;
import at.wirecube.examples.products.application.enums.SortOrder;
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

    private SortBy sortBy;

    private SortOrder sortOrder;

    public ProductSearchCriteria() {
        this.page = 1;
        this.size = 50;
        this.sortBy = SortBy.NAME;
        this.sortOrder = SortOrder.ASC;
    }

}
