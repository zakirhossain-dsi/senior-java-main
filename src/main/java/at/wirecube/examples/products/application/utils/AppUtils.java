package at.wirecube.examples.products.application.utils;

import at.wirecube.examples.products.application.enums.SortOrder;
import at.wirecube.examples.products.application.model.ProductSearchCriteria;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class AppUtils {

    private AppUtils() {

    }

    public static Pageable getPageable(ProductSearchCriteria criteria) {

        Sort sort = Sort.by(criteria.getSortBy().toLowerCase());

        if (SortOrder.DESC.name().equalsIgnoreCase(criteria.getSortOrder())) {
            sort = sort.descending();
        }

        return PageRequest.of(criteria.getPage() - 1, criteria.getSize(), sort);
    }

}
