package MindStore.services;

import MindStore.command.CategoryDto;
import MindStore.command.ProductDto;
import java.util.List;



public interface UserServiceI {
    List<ProductDto> getAllProducts();

    List<ProductDto> getProductsByTitle(String title);

    List<ProductDto> getProductByCategory(String category);

    ProductDto getProductById(Long id);

    CategoryDto getCategoryById(int id);
}
