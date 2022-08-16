package MindStore.services;

import MindStore.command.personDto.UserDto;
import MindStore.command.personDto.UserUpdateDto;
import MindStore.command.productDto.CategoryDto;
import MindStore.command.productDto.IndividualRatingDto;
import MindStore.command.productDto.ProductDto;
import MindStore.command.productDto.AverageRatingDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserServiceI {
    List<ProductDto> getAllProducts(String direction, String field, int page, int pageSize);

    List<ProductDto> filterByPrice(String direction, int page, int pageSize, int minPrice, int maxPrice);

    List<ProductDto> getProductsByTitle(String title, int page, int pageSize);

    List<ProductDto> getProductByCategory(String direction, String category, int page, int pageSize);

    ProductDto getProductById(Long id);

    CategoryDto getCategoryById(int id);

    List<ProductDto> getShoppingCart(Long userId);

    List<ProductDto> addProductToCart(Long userId, Long productId);

    List<ProductDto> removeProductFromCart(Long userId, Long productId);

    List<ProductDto> removeAllProductsFromCart(Long userId);

    ResponseEntity<String> buyProducts(Long id, int payement);

    Double getCartTotalPrice(Long userId);

    UserDto updateUser(Long id, UserUpdateDto userUpdateDto);

    void deleteUser(Long id);

    List<IndividualRatingDto> getRatingList(Long userId);

    AverageRatingDto rateProduct(Long userId, Long productId, int rating);

    void deleteRate(Long userId, Long ratingId);

    UserDto signUp(UserDto userDto);

    UserDto getUserById(Long id);

    List<ProductDto> filterByRating(String direction, int page, int pageSize, int minRating, int maxRating);
}
