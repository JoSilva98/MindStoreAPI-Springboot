package MindStore.services;

import MindStore.command.CategoryDto;
import MindStore.command.ProductDto;
import MindStore.command.UserDto;
import MindStore.command.UserUpdateDto;

import java.util.List;



public interface UserServiceI {
    List<ProductDto> getAllProducts();

    List<ProductDto> getProductsByTitle(String title);

    List<ProductDto> getProductByCategory(String category);

    ProductDto getProductById(Long id);

    CategoryDto getCategoryById(int id);

    List<ProductDto> getShoppingCart(Long userId);

    UserDto signUp(UserDto userDto);

    UserDto updateUser(Long id, UserUpdateDto userUpdateDto);
}
