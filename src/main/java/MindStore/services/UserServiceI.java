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

    List<ProductDto> getProductsByTitle(String title, int page, int pageSize);

    List<ProductDto> getProductByCategory(String category, int page, int pageSize);

    ProductDto getProductById(Long id);

    CategoryDto getCategoryById(int id);

    List<ProductDto> getShoppingCart(Long userId);

    Double getCartTotalPrice(Long userId);

    UserDto signUp(UserDto userDto);

    ResponseEntity<String> buyProducts(Long id, int payement);

    UserDto updateUser(Long id, UserUpdateDto userUpdateDto);

    List<IndividualRatingDto> getRatingList(Long userId);

    AverageRatingDto rateProduct(Long userId, Long productId, int rating);

    void deleteRate(Long userId, Long ratingId);

    List<ProductDto> filterByPrice(String direction, int page, int pageSize, int minPrice, int maxPrice);

    List<ProductDto> addProductToCart(Long userId, Long productId);

    List<ProductDto> removeProductFromCart(Long userId, Long productId);
}
