package MindStore.services;

import MindStore.command.AdminDto;
import MindStore.command.ProductDto;
import MindStore.command.UserDto;

import java.util.List;

public interface AdminServiceI {
    List<ProductDto> getAllProducts(String direction, String field, int page, int pageSize);

    ProductDto getProductById(Long id);

    List<ProductDto> getProductsByName(String title);

    List<UserDto> getAllUsers(String direction, String field, int page, int pageSize);

    UserDto getUserById(Long id);

    List<UserDto> getUsersByName(String name);

    AdminDto addAdmin(AdminDto adminDto);

    ProductDto addProduct(ProductDto productDto);

    UserDto addUser(UserDto userDto);

    void deleteProduct(String title);
}
