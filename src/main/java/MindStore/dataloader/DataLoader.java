package MindStore.dataloader;

import MindStore.converters.MainConverterI;
import MindStore.dataloader.ProductsFetch.ApiProduct;
import MindStore.persistence.models.Person.Admin;
import MindStore.persistence.models.Person.Role;
import MindStore.persistence.models.Person.User;
import MindStore.persistence.models.Product.Category;
import MindStore.persistence.models.Product.Product;
import MindStore.persistence.models.Product.AverageRating;
import MindStore.persistence.repositories.Person.AdminRepository;
import MindStore.persistence.repositories.Person.RoleRepository;
import MindStore.persistence.repositories.Person.UserRepository;
import MindStore.persistence.repositories.Product.CategoryRepository;
import MindStore.persistence.repositories.Product.ProductRepository;
import MindStore.persistence.repositories.Product.AverageRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {
    private final RestTemplate restTemplate;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final MainConverterI mainConverter;
    private final AverageRatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) {
        //externalApi();

        Category mensClothing = Category.builder()
                .category("men's clothing")
                .build();

        Category womensClothing = Category.builder()
                .category("women's clothing")
                .build();

        Category jewellery = Category.builder()
                .category("jewellery")
                .build();

        Category electronics = Category.builder()
                .category("electronics")
                .build();

        List<Category> categories = List.of(mensClothing, womensClothing, jewellery, electronics);
        this.categoryRepository.saveAll(categories);

        Role userRole = Role.builder()
                .roleType("USER")
                .build();

        Role adminRole = Role.builder()
                .roleType("ADMIN")
                .build();

        List<Role> roles = List.of(userRole, adminRole);
        this.roleRepository.saveAll(roles);

        User user = User.builder()
                .firstName("Joaquim")
                .lastName("Alberto")
                .email("quim@mail")
                .password(this.encoder.encode("password"))
                .dateOfBirth(LocalDate.of(1973, 2, 23))
                .address("Rua do Quim, 3800-237")
                .roleId(userRole)
                .build();

        this.userRepository.save(user);

        Admin admin = Admin.builder()
                .firstName("Ze")
                .lastName("To")
                .email("zeto@email.com")
                .password(this.encoder.encode("ora_esta_bem_entao"))
                .roleId(adminRole)
                .build();

        this.adminRepository.save(admin);
    }

    public void externalApi() {

        ResponseEntity<ApiProduct[]> response = this.restTemplate.getForEntity("https://fakestoreapi.com/products", ApiProduct[].class);
        ApiProduct[] products = response.getBody();

        if (products != null) {
            for (ApiProduct product : products) {
                Product productEntity = this.mainConverter.converter(product, Product.class);
                AverageRating ratingEntity = this.mainConverter.converter(product.getRating(), AverageRating.class);

                Category category;

                if (this.categoryRepository.findByCategory(product.getCategory()).isEmpty()) {
                    category = Category.builder()
                            .category(product.getCategory())
                            .build();

                    this.categoryRepository.save(category);

                } else
                    category = this.categoryRepository.findByCategory(product.getCategory()).get();

                this.ratingRepository.save(ratingEntity);

                productEntity.setRatingId(ratingEntity);
                productEntity.setCategory(category);
                productEntity.setStock(3);

                this.productRepository.save(productEntity);
            }
        }
    }
}
