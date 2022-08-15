package MindStore.dataloader.productsFetch;

import MindStore.converters.MainConverterI;
import MindStore.persistence.models.Person.User;
import MindStore.persistence.models.Product.AverageRating;
import MindStore.persistence.models.Product.Category;
import MindStore.persistence.models.Product.IndividualRating;
import MindStore.persistence.models.Product.Product;
import MindStore.persistence.repositories.Product.AverageRatingRepository;
import MindStore.persistence.repositories.Product.CategoryRepository;
import MindStore.persistence.repositories.Product.IndividualRatingRepository;
import MindStore.persistence.repositories.Product.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class ExternalApiFetch {
    public static void externalApi(List<User> users, RestTemplate restTemplate, MainConverterI mainConverter,
                                   CategoryRepository categoryRepository, AverageRatingRepository avRatingRepository,
                                   IndividualRatingRepository indRatingRepository, ProductRepository productRepository) {
        try {
            ResponseEntity<ApiProduct[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products", ApiProduct[].class);
            ApiProduct[] products = response.getBody();

            if (products != null) {
                for (ApiProduct product : products) {
                    Product productEntity = mainConverter.converter(product, Product.class);
                    //AverageRating ratingEntity = this.mainConverter.converter(product.getRating(), AverageRating.class);

                    Category category;
                    if (categoryRepository.findByCategory(product.getCategory()).isEmpty()) {
                        category = Category.builder()
                                .category(product.getCategory())
                                .build();

                        categoryRepository.saveAndFlush(category);
                    } else
                        category = categoryRepository.findByCategory(product.getCategory()).get();

                    int count = (int) (Math.random() * 8);
                    List<IndividualRating> userRatings = new ArrayList<>();

                    for (int i = 0; i < count; i++) {
                        IndividualRating userRating = IndividualRating.builder()
                                .rate((int) (Math.random() * 4) + 2)
                                .productTitle(productEntity.getTitle())
                                .userId(users.get(i))
                                .build();

                        userRatings.add(userRating);
                    }

                    double average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

                    AverageRating averageRating = AverageRating.builder()
                            .rate(average)
                            .count(count)
                            .build();

                    if (avRatingRepository.findByProductTitle(productEntity.getTitle()).isEmpty()) {
                        avRatingRepository.saveAndFlush(averageRating);
                        userRatings.forEach(x -> x.setAverageRatingId(averageRating));
                        indRatingRepository.saveAllAndFlush(userRatings);
                    }

                    if (productRepository.findByTitle(productEntity.getTitle()).isEmpty()) {
                        productEntity.setRatingId(averageRating);
                        productEntity.setCategory(category);
                        productEntity.setStock((int) (Math.random() * 15) + 2);

                        productRepository.saveAndFlush(productEntity);
                    }
                }
            }
        } catch (Exception ignored) {

        }
    }
}
