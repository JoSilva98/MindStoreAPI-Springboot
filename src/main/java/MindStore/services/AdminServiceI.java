package MindStore.services;

import MindStore.command.*;

import java.util.List;

public interface AdminServiceI {
    List<ProductDto> getAllProducts(String direction, String field, int page, int pageSize);
    List<ProductDto> getAllProductsByPrice(String direction, int page, int pageSize, int minPrice, int maxPrice);

    ProductDto getProductById(Long id);

    List<ProductDto> getProductsByName(String title);

    List<UserDto> getAllUsers(String direction, String field, int page, int pageSize);

    UserDto getUserById(Long id);

    List<UserDto> getUsersByName(String name);

    AdminDto addAdmin(AdminDto adminDto);

    ProductDto addProduct(ProductDto productDto);

    UserDto addUser(UserDto userDto);

    AdminDto updateAdmin(Long id, AdminUpdateDto adminUpdateDto);

    ProductDto updateProduct(Long id, ProductUpdateDto productUpdateDto);

    UserDto updateUser(Long id, UserUpdateDto userUpdateDto);

    void deleteProduct(String title);
}
