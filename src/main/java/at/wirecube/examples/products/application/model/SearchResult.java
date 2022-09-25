package at.wirecube.examples.products.application.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchResult<T> {

    private long totalElements;
    private long currentElements;
    private int totalPages;
    private int currentPage;
    private List<T> data;
}
