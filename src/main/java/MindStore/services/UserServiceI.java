package MindStore.services;

import MindStore.command.CategoryDto;
import MindStore.command.ProductDto;
import MindStore.command.UserDto;
import MindStore.command.UserUpdateDto;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;



public interface UserServiceI {
    List<ProductDto> getAllProducts();

    List<ProductDto> getProductsByTitle(String title);

    List<ProductDto> getProductByCategory(String category);

    ProductDto getProductById(Long id);

    CategoryDto getCategoryById(int id);

    List<ProductDto> getShoppingCart(Long userId);

    UserDto signUp(UserDto userDto);

    ResponseEntity<String> buyProducts(Long id, int payement);

    UserDto updateUser(Long id, UserUpdateDto userUpdateDto);
}
