package MindStore.dataloader;

import MindStore.converters.MainConverterI;
import MindStore.dataloader.ProductsFetch.ApiProduct;
import MindStore.persistence.models.Category;
import MindStore.persistence.models.Product;
import MindStore.persistence.models.Rating;
import MindStore.persistence.repositories.CategoryRepository;
import MindStore.persistence.repositories.ProductRepository;
import MindStore.persistence.repositories.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {
    private final RestTemplate restTemplate;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final MainConverterI mainConverter;
    private final RatingRepository ratingRepository;

    @Override
    public void run(ApplicationArguments args) {
        //criar as categorias na DB para depois objetos da api externa ficarem associadas a estas categorias através
        //da string categoryType (que é igual a do json da api)

//        Category mensClothing = Category.builder()
//                .category("men's clothing")
//                .build();
//
//        Category womensClothing = Category.builder()
//                .category("women's clothing")
//                .build();
//
//        Category jewelery = Category.builder()
//                .category("jewelery")
//                .build();
//
//        Category electronics = Category.builder()
//                .category("electronics")
//                .build();
//
//        List<Category> categoryList = List.of(mensClothing, womensClothing, jewelery, electronics);
//        this.categoryRepository.saveAll(categoryList);


        ResponseEntity<ApiProduct[]> response = this.restTemplate.getForEntity("https://fakestoreapi.com/products", ApiProduct[].class);
        ApiProduct[] products = response.getBody();

        //aqui mapeamos cada produto que vem da Api externa como ApiProduct para Product do modelo e gravamos
        //tb mapeamos o rating porque tb é objeto
        //as strings tendo o nome igual mapeiam automaticamente
//        if (products != null) {
//            Arrays.stream(products)
//                    .map(product -> {
//                        Product prod = this.mainConverter.converter(product, Product.class);
//                        Rating rating = this.mainConverter.converter(product.getRating(), Rating.class);
//                        prod.setRatingId(rating);
//                        return this.productRepository.save(this.mainConverter.converter(product, Product.class));
//                    });
        //}

        for (ApiProduct product : products) {
            Product productEntity = this.mainConverter.converter(product, Product.class);
            Rating rating = this.mainConverter.converter(product.getRating(), Rating.class);

            Category category;

            if (this.categoryRepository.findByCategory(product.getCategory()).isEmpty()) {
                category = Category.builder()
                        .category(product.getCategory())
                        .build();

                this.categoryRepository.saveAndFlush(category);

            } else
                category = this.categoryRepository.findByCategory(product.getCategory()).get(); //get para ir buscar valor do optional (como fizemos o if)

            //temos que guardar antes o arting na DB para podermos associar ao prod
            this.ratingRepository.saveAndFlush(rating);
            //associar o rating ao product
            productEntity.setRatingId(rating);
            productEntity.setCategory(category);
            this.productRepository.saveAndFlush(productEntity);
        }
//
//        System.out.println(Arrays.toString(products));
//    }
    }
}
//https://fakestoreapi.com/
