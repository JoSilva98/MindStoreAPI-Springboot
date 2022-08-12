package MindStore.services;

import MindStore.command.CategoryDto;
import MindStore.command.ProductDto;
import MindStore.command.UserDto;
import MindStore.command.UserUpdateDto;
import MindStore.converters.MainConverterI;
import MindStore.enums.RoleEnum;
import MindStore.exceptions.ConflictException;
import MindStore.persistence.models.Person.Role;
import MindStore.persistence.models.Person.User;
import MindStore.persistence.repositories.Person.RoleRepository;
import MindStore.persistence.repositories.Person.UserRepository;
import MindStore.exceptions.NotFoundException;
import MindStore.persistence.models.Product.Category;
import MindStore.persistence.models.Product.Product;
import MindStore.persistence.repositories.Product.CategoryRepository;
import MindStore.persistence.repositories.Product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@AllArgsConstructor
//@NoArgsConstructor aqui nao pode senao nao inicializa o rep e converter(?)
public class UserServiceImp implements UserServiceI {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private MainConverterI mainConverter;


    @Override
    public List<ProductDto> getAllProducts() {
        return this.mainConverter.listConverter(this.productRepository.findAll(), ProductDto.class);
    }

    @Override
    public List<ProductDto> getProductsByTitle(String title) {

        List<Product> productsList = this.productRepository.findByTitleLike(title);
        if (productsList.isEmpty()) {
            throw new NotFoundException("No products found with such name");
        }

        return this.mainConverter.listConverter(productsList, ProductDto.class);
    }

    @Override
    public List<ProductDto> getProductByCategory(String category) {
        Category categoryEntity = this.categoryRepository.findByCategory(category)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        List<Product> productList = categoryEntity.getProductList();
        if(productList.isEmpty()){
            throw new NotFoundException("No products found with that category");
        }

        return this.mainConverter.listConverter(productList, ProductDto.class);
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        return this.mainConverter.converter(product, ProductDto.class);
    }

    @Override
    public CategoryDto getCategoryById(int id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        return this.mainConverter.converter(category, CategoryDto.class);
    }

    @Override
    public List<ProductDto> getShoppingCart(Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Set<Product> productList = user.getShoppingCart();

        return productList.stream()
                .map(product -> mainConverter.converter(product, ProductDto.class))
                .toList();
    }

    @Override
    public UserDto signUp(UserDto userDto) {
        this.userRepository.findByEmail(userDto.getEmail())
                .ifPresent((student) -> {
                    throw new ConflictException("User already exists");
                });

        User userToSave = this.mainConverter.converter(userDto, User.class);

        //dar set do role, primeiro temos que o encontrar no rep
        Role role = this.roleRepository.findById(RoleEnum.USER)
                        .orElseThrow(() -> new NotFoundException("Role not found"));

        userToSave.setRoleId(role);
        this.userRepository.save(userToSave);

        return this.mainConverter.converter(userToSave, UserDto.class);
    }

    @Override
    public UserDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        User updatedUser = this.mainConverter.updateConverter(userUpdateDto, user);
        this.userRepository.save(updatedUser);

        return this.mainConverter.converter(updatedUser, UserDto.class);
    }
}
