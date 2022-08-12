package MindStore.services;

import MindStore.command.ProductDto;
import MindStore.command.UserDto;
import MindStore.converters.MainConverterI;
import MindStore.enums.DirectionEnum;
import MindStore.exceptions.NotAllowedValueException;
import MindStore.exceptions.NotFoundException;
import MindStore.helpers.ValidateParams;
import MindStore.persistence.models.Product;
import MindStore.persistence.models.User;
import MindStore.persistence.repositories.AdminRepository;
import MindStore.persistence.repositories.ProductRepository;
import MindStore.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static MindStore.helpers.ValidateParams.validatePages;

@Service
@AllArgsConstructor
public class AdminService implements AdminServiceI {
    private AdminRepository adminRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private MainConverterI converter;

    @Override
    public List<ProductDto> getAllProducts(String direction, String field, int page, int pageSize) {
        validatePages(page, pageSize);

        List<Product> products;
        switch (direction) {
            case DirectionEnum.ASC -> products = findProducts(Sort.Direction.ASC, field, page, pageSize);
            case DirectionEnum.DESC -> products = findProducts(Sort.Direction.DESC, field, page, pageSize);
            default -> throw new NotAllowedValueException("Direction not allowed");
        }

        return this.converter.listConverter(products, ProductDto.class);
    }

    private List<Product> findProducts(Sort.Direction direction, String field, int page, int pageSize) {
        return this.productRepository.findAll(
                PageRequest.of(page - 1, pageSize)
                        .withSort(Sort.by(direction, field))
        ).stream().toList();
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        return this.converter.converter(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getProductsByName(String title) {
        List<Product> products = this.productRepository.findByTitle(title);
        if (products.isEmpty()) throw new NotFoundException("Product not found");
        return this.converter.listConverter(products, ProductDto.class);
    }

    @Override
    public List<UserDto> getAllUsers(String direction, String field, int page, int pageSize) {
        validatePages(page, pageSize);

        List<User> users;
        switch (direction) {
            case DirectionEnum.ASC -> users = findUsers(Sort.Direction.ASC, field, page, pageSize);
            case DirectionEnum.DESC -> users = findUsers(Sort.Direction.DESC, field, page, pageSize);
            default -> throw new NotAllowedValueException("Direction not allowed");
        }

        return this.converter.listConverter(users, UserDto.class);
    }

    private List<User> findUsers(Sort.Direction direction, String field, int page, int pageSize) {
        return this.userRepository.findAll(
                PageRequest.of(page - 1, pageSize)
                        .withSort(Sort.by(direction, field))
        ).stream().toList();
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return this.converter.converter(user, UserDto.class);
    }

    @Override
    public List<UserDto> getUsersByName(String name) {
        List<User> user = this.userRepository.findByName(name);
        if (user.isEmpty()) throw new NotFoundException("User not found");
        return this.converter.listConverter(user, UserDto.class);
    }
}
