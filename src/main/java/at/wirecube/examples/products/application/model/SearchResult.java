package at.wirecube.examples.products.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchResult<T> {

    @Schema(example = "1")
    private long totalElements;
    @Schema(example = "1")
    private long currentElements;
    @Schema(example = "1")
    private int totalPages;
    @Schema(example = "1")
    private int currentPage;
    private List<T> data;
}
