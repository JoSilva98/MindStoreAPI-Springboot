package MindStore.services;

import MindStore.command.CategoryDto;
import MindStore.command.ProductDto;
import MindStore.converters.MainConverterI;
import MindStore.persistence.repositories.Person.UserRepository;
import MindStore.exceptions.NotFoundException;
import MindStore.persistence.models.Product.Category;
import MindStore.persistence.models.Product.Product;
import MindStore.persistence.repositories.Product.CategoryRepository;
import MindStore.persistence.repositories.Product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
//@NoArgsConstructor aqui nao pode senao nao inicializa o rep e converter(?)
public class UserServiceImp implements UserServiceI {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
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
        return null;
    }
}
