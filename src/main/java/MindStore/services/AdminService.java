package MindStore.services;

import MindStore.command.ProductDto;
import MindStore.converters.MainConverterI;
import MindStore.enums.DirectionEnum;
import MindStore.exceptions.NotFoundException;
import MindStore.helpers.ValidateParams;
import MindStore.persistence.models.Product;
import MindStore.persistence.repositories.AdminRepository;
import MindStore.persistence.repositories.ProductRepository;
import lombok.AllArgsConstructor;
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
    private MainConverterI converter;

    @Override
    public List<ProductDto> getAllProducts(String direction, String field, int page, int pageSize) {
        validatePages(page, pageSize);

        List<Product> products;
        switch (direction) {
            case DirectionEnum.ASC -> products = this.productRepository
                    .findAll(PageRequest.of(page - 1, pageSize)
                            .withSort(Sort.by(Sort.Direction.ASC, field))
                    ).stream().toList();
            case DirectionEnum.DESC -> products = this.productRepository
                    .findAll(PageRequest.of(page - 1, pageSize)
                            .withSort(Sort.by(Sort.Direction.DESC, field))
                    ).stream().toList();
            default -> products = new ArrayList<>();
        }

        return this.converter.listConverter(products, ProductDto.class);
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
