package MindStore.services;

import MindStore.command.ProductDto;
import MindStore.converters.MainConverterI;
import MindStore.exceptions.NotFoundException;
import MindStore.persistence.models.Product;
import MindStore.persistence.repositories.AdminRepository;
import MindStore.persistence.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminService implements AdminServiceI {
    private AdminRepository adminRepository;
    private ProductRepository productRepository;
    private MainConverterI converter;

    @Override
    public List<ProductDto> getProducts() {
        return this.converter.listConverter(
                this.productRepository.findAll(), ProductDto.class
        );
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        return this.converter.converter(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getProductsByName(String title) {
        List<Product> products = this.productRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        return this.converter.listConverter(products, ProductDto.class);
    }
}
