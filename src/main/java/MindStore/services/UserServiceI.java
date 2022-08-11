package MindStore.services;

import MindStore.command.ProductDto;
import java.util.List;



public interface UserServiceI {
    List<ProductDto> getAllProducts();

    List<ProductDto> getProductsByTitle(String title);
}
