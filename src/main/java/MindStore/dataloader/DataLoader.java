package MindStore.dataloader;

import MindStore.converters.MainConverterI;
import MindStore.dataloader.ProductsFetch.ApiProduct;
import MindStore.persistence.models.*;
import MindStore.persistence.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
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
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AdminRepository adminRepository;

    @Override
    public void run(ApplicationArguments args) {
        externalApi();

        Role userRole = Role.builder()
                .roleType("USER")
                .build();

        Role adminRole = Role.builder()
                .roleType("ADMIN")
                .build();

        List<Role> roles = List.of(userRole, adminRole);
        this.roleRepository.saveAll(roles);

        User user = User.builder()
                .name("Joaquim Alberto")
                .email("quim@mail")
                .password("pass")
                .dateOfBirth(LocalDate.of(1973, 2, 23))
                .adress("Rua do Quim, 3800-237")
                .roleId(userRole)
                .build();

        this.userRepository.save(user);

        Admin admin = Admin.builder()
                .name("Ze To")
                .email("zeto@email.com")
                .password("ora_esta_bem_entao")
                .roleId(adminRole)
                .build();

        this.adminRepository.save(admin);
    }

    /*
    funçao que usa rest template para ir buscar o array de products que esta na api externa, (getForEntity porque array)

     */
    public void externalApi() {

        ResponseEntity<ApiProduct[]> response = this.restTemplate.getForEntity("https://fakestoreapi.com/products", ApiProduct[].class);
        ApiProduct[] products = response.getBody();

        //se nao houver products quer dizer que o vetor nao iniciou e é nulo
        if (products != null) {
            for (ApiProduct product : products) {
                //converter objetos todos:
                //passar products e ratings para os objetos que fizemos pra mapear
                Product productEntity = this.mainConverter.converter(product, Product.class);
                //porque tambem é um objeto que vem do produto (tb é tabela na DB)
                Rating ratingEntity = this.mainConverter.converter(product.getRating(), Rating.class);

                //para converter categoria (do json) de string para objeto:
                //scope
                Category category;

                //findbycategory query do categoryjpa
                //se n houver categorias no repositorio construimos categoria, se nao vamos buscar a que existe
                if (this.categoryRepository.findByCategory(product.getCategory()).isEmpty()) {
                    category = Category.builder()
                            .category(product.getCategory())
                            .build();

                    this.categoryRepository.save(category);

                } else
                    category = this.categoryRepository.findByCategory(product.getCategory()).get(); //get para ir buscar valor do optional (como fizemos o if)

                //temos que guardar antes o rating na DB para podermos associar ao productEntity
                this.ratingRepository.save(ratingEntity);
                //associar o rating ao product
                productEntity.setRatingId(ratingEntity);
                productEntity.setCategory(category);
                this.productRepository.save(productEntity);
            }
        }
    }
}
