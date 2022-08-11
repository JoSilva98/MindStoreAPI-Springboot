package MindStore.services;

import MindStore.command.ProductDto;

import java.util.List;

public interface AdminServiceI {
    List<ProductDto> getProducts();

    ProductDto getProductById(Long id);

    List<ProductDto> getProductsByName(String title);
}
