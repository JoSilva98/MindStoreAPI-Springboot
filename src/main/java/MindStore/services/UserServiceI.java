package MindStore.services;

import MindStore.command.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserServiceI {
    List<ProductDto> getAllProducts(String direction, String field, int page, int pageSize);

    List<ProductDto> getProductsByTitle(String title, String direction, String field, int page, int pageSize);

    List<ProductDto> getProductByCategory(String category, String direction, String field, int page, int pageSize);

    ProductDto getProductById(Long id);

    CategoryDto getCategoryById(int id);

    List<ProductDto> getShoppingCart(Long userId);

    String getCartTotalPrice(Long userId);

    UserDto signUp(UserDto userDto);

    ResponseEntity<String> buyProducts(Long id, int payement);

    UserDto updateUser(Long id, UserUpdateDto userUpdateDto);

    RatingDto giveRating(Long userId, Long productId, double rating);

    List<ProductDto> filterByPrice(String direction);

    List<ProductDto> filterByRatingOrTitle(String field, String direction);

    List<ProductDto> addProductToCart(Long userId, Long productId);

    List<ProductDto> removeProductFromCart(Long userId, Long productId);
}
