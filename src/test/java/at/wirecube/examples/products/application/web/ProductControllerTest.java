package at.wirecube.examples.products.application.web;

import at.wirecube.examples.products.application.model.Product;
import at.wirecube.examples.products.application.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;
import java.util.List;

import static at.wirecube.examples.products.application.constants.ProductApiUrl.BASE;
import static at.wirecube.examples.products.application.constants.Vat.TEN;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.isA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    private final String PRODUCT_ID_ONE = "/1";
    private final String[] errors = {
            "price - must not be null",
            "name - must not be empty",
            "vat - value should be one of the followings: [10, 18, 20]"
    };
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    private Product product;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void insertProductWithInvalidData() throws Exception {

        var invalidProduct = Product.builder().build();
        var product = objectMapper.writeValueAsString(invalidProduct);
        mockMvc
                .perform(
                        post(BASE).content(product).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", isA(JSONArray.class)))
                .andExpect(jsonPath("$.errors", hasItems(errors)))
                .andDo(print());
    }

    @Test
    void insertProductWithValidData() throws Exception {
        when(productService.insertProduct(any())).thenReturn(product);
        mockMvc
                .perform(
                        post(BASE).content(objectMapper.writeValueAsString(product)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void updateProduct() throws Exception {
        product.setId(1);
        when(productService.updateProduct(any())).thenReturn(product);
        mockMvc
                .perform(
                        put(BASE + PRODUCT_ID_ONE).content(objectMapper.writeValueAsString(product)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        product.setId(null);
    }

    @Test
    void getProduct() throws Exception {
        when(productService.getProductById(anyInt())).thenReturn(product);
        mockMvc
                .perform(
                        get(BASE + PRODUCT_ID_ONE))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getAllProducts() throws Exception {
        when(productService.getAllProducts(any())).thenReturn(List.of(product));
        mockMvc
                .perform(get(BASE)
                        .param("page", "1")
                        .param("size", "2")
                        .param("sortBy", "id")
                        .param("sortOrder", "asc")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteProduct() throws Exception {

        doNothing().when(productService).deleteProductById(anyInt());
        mockMvc
                .perform(delete(BASE + PRODUCT_ID_ONE))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @PostConstruct
    public void setup() {
        product = Product.builder()
                .name("Samsung mobile")
                .description("It is a nice mobile")
                .price(10000.0)
                .vat(TEN)
                .build();
    }

}
