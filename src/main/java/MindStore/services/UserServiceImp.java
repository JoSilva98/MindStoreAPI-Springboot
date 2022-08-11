package MindStore.services;

import MindStore.command.ProductDto;
import MindStore.converters.MainConverterI;
import MindStore.exceptions.NotFoundException;
import MindStore.persistence.models.Product;
import MindStore.persistence.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
//@NoArgsConstructor aqui nao pode senao nao inicializa o rep e converter(?)
public class UserServiceImp implements UserServiceI{

    private ProductRepository productRepository;
    private MainConverterI mainConverter;

    @Override
    public List<ProductDto> getAllProducts() {
        return this.mainConverter.listConverter(this.productRepository.findAll(), ProductDto.class);
    }

    @Override
    public List<ProductDto> getProductsByTitle(String title) {

    List<Product> productsList = this.productRepository.findByTitle(title);
    if(productsList.isEmpty()){
        throw new NotFoundException("No products found with such name");
    }

        return this.mainConverter.listConverter(productsList, ProductDto.class);
    }
}
