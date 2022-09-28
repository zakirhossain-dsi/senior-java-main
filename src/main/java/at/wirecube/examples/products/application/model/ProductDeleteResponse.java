package at.wirecube.examples.products.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ProductDeleteResponse {

    private ProductDeleteResponse(){

    }

    private ProductDeleteResponse(Integer productId, String message){
        this.productId = productId;
        this.message = message;
    }

    @Schema(example = "1")
    private Integer productId;

    @Schema(example = "Product has been deleted successfully.")
    private String message;

    public static ProductDeleteResponse of(Integer productId){
        return new ProductDeleteResponse(productId, "Product has been deleted successfully.");

    }
}
