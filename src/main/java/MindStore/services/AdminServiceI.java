package MindStore.services;

import MindStore.command.ProductDto;

import java.util.List;

public interface AdminServiceI {
    List<ProductDto> getAllProducts(String direction, String field, int page, int pageSize);

    ProductDto getProductById(Long id);

    List<ProductDto> getProductsByName(String title);
}
