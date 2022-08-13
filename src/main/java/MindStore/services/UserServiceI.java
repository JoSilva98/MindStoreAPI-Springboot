package MindStore.services;

import MindStore.command.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServiceI {
    List<ProductDto> getAllProducts(String direction, String field, int page, int pageSize);

    List<ProductDto> getProductsByTitle(String title, int page, int pageSize);

    List<ProductDto> getProductByCategory(String category, int page, int pageSize);

    ProductDto getProductById(Long id);

    CategoryDto getCategoryById(int id);

    List<ProductDto> getShoppingCart(Long userId);

    Double getCartTotalPrice(Long userId);

    UserDto signUp(UserDto userDto);

    ResponseEntity<String> buyProducts(Long id, int payement);

    UserDto updateUser(Long id, UserUpdateDto userUpdateDto);

    RatingDto rateProduct(Long userId, Long productId, int rating);

    List<ProductDto> filterByPrice(String direction, int page, int pageSize, int minPrice, int maxPrice);

    List<ProductDto> addProductToCart(Long userId, Long productId);

    List<ProductDto> removeProductFromCart(Long userId, Long productId);
}
