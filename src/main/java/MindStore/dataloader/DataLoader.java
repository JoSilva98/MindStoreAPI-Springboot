package MindStore.dataloader;

import MindStore.converters.MainConverterI;
import MindStore.dataloader.ProductsFetch.ApiProduct;
import MindStore.persistence.models.Person.Admin;
import MindStore.persistence.models.Person.Role;
import MindStore.persistence.models.Person.User;
import MindStore.persistence.models.Product.Category;
import MindStore.persistence.models.Product.IndividualRating;
import MindStore.persistence.models.Product.Product;
import MindStore.persistence.models.Product.AverageRating;
import MindStore.persistence.repositories.Person.AdminRepository;
import MindStore.persistence.repositories.Person.RoleRepository;
import MindStore.persistence.repositories.Person.UserRepository;
import MindStore.persistence.repositories.Product.CategoryRepository;
import MindStore.persistence.repositories.Product.IndividualRatingRepository;
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
import java.util.ArrayList;
import java.util.List;

import static MindStore.helpers.FindBy.findCategoryById;

@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {
    private final RestTemplate restTemplate;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final MainConverterI mainConverter;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AdminRepository adminRepository;
    private final AverageRatingRepository avRatingRepository;
    private final IndividualRatingRepository indRatingRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) {
        //Roles
        Role userRole = Role.builder()
                .roleType("USER")
                .build();

        Role adminRole = Role.builder()
                .roleType("ADMIN")
                .build();

        List<Role> roles = List.of(userRole, adminRole);
        this.roleRepository.saveAll(roles);

        //Users
        User user1 = User.builder()
                .firstName("Joaquim")
                .lastName("Alberto")
                .email("quim@email.com")
                .password(this.encoder.encode("password"))
                .dateOfBirth(LocalDate.of(1973, 2, 23))
                .address("Rua do Quim, 3800-237")
                .roleId(userRole)
                .build();

        User user2 = User.builder()
                .firstName("Ana")
                .lastName("Costa")
                .email("ana@email.com")
                .password(this.encoder.encode("qwe123ytr654"))
                .dateOfBirth(LocalDate.of(1996, 6, 16))
                .address("Rua da Ana, 3801-238")
                .roleId(userRole)
                .build();

        User user3 = User.builder()
                .firstName("João")
                .lastName("Couto")
                .email("coutinho@email.com")
                .password(this.encoder.encode("incriiiiiivel"))
                .dateOfBirth(LocalDate.of(1997, 9, 13))
                .address("Rua do Coutinho, 3802-239")
                .roleId(userRole)
                .build();

        User user4 = User.builder()
                .firstName("Luís")
                .lastName("Couto")
                .email("luisinho@email.com")
                .password(this.encoder.encode("asvezespareceque"))
                .dateOfBirth(LocalDate.of(1999, 10, 22))
                .address("Rua do Coutinho, 3802-239")
                .roleId(userRole)
                .build();

        User user5 = User.builder()
                .firstName("Elisa")
                .lastName("Moutinho")
                .email("elisinha@email.com")
                .password(this.encoder.encode("palavrapassdificil"))
                .dateOfBirth(LocalDate.of(1989, 12, 22))
                .address("Rua da Elisa, 3803-240")
                .roleId(userRole)
                .build();

        User user6 = User.builder()
                .firstName("Ala")
                .lastName("Kropa")
                .email("alinha@email.com")
                .password(this.encoder.encode("sqlinjection"))
                .dateOfBirth(LocalDate.of(1993, 10, 7))
                .address("Rua da Ala, 3804-241")
                .roleId(userRole)
                .build();

        User user7 = User.builder()
                .firstName("Nuno")
                .lastName("Carmo")
                .email("nuninho@email.com")
                .password(this.encoder.encode("mesmoaniversariodojoao"))
                .dateOfBirth(LocalDate.of(1995, 9, 13))
                .address("Rua do Nuno, 3805-242")
                .roleId(userRole)
                .build();

        User user8 = User.builder()
                .firstName("Carolina")
                .lastName("Ferraz")
                .email("carolininha@email.com")
                .password(this.encoder.encode("pequenina"))
                .dateOfBirth(LocalDate.of(2004, 8, 26))
                .address("Rua da Carolina, 3806-243")
                .roleId(userRole)
                .build();

        List<User> users = List.of(user1, user2, user3, user4, user5, user6, user7, user8);
        this.userRepository.saveAll(users);

        //Admins
        Admin admin1 = Admin.builder()
                .firstName("Ze")
                .lastName("To")
                .email("zeto@email.com")
                .password(this.encoder.encode("ora_esta_bem_entao"))
                .roleId(adminRole)
                .build();

        Admin admin2 = Admin.builder()
                .firstName("João")
                .lastName("Silva")
                .email("john@email.com")
                .password(this.encoder.encode("agorasouadmin"))
                .roleId(adminRole)
                .build();

        List<Admin> admins = List.of(admin1, admin2);
        this.adminRepository.saveAll(admins);

        //External API
        externalApi(users);

        //Categories
        Category mensClothing = findCategoryById(1, this.categoryRepository);
        Category womensClothing = findCategoryById(2, this.categoryRepository);

        List<Category> categories = List.of(mensClothing, womensClothing);
        this.categoryRepository.saveAll(categories);

        //Ratings
        //Rating 1
        int count = (int) (Math.random() * 8);
        List<IndividualRating> userRatings = new ArrayList<>();
        String title = "VLogo short-sleeved T-shirt";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        double average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating1 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating1);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating1));
        this.indRatingRepository.saveAll(userRatings);

        //Product 1
        Product product = Product.builder()
                .title(title)
                .price(349.99)
                .description("An homage to Valentino Garavani's VLogo Signature, " +
                        "this black short-sleeved T-shirt features the house's " +
                        "iconic branding in contrast white to the chest.")
                .category(mensClothing)
                .image("https://cdn-images.farfetch-contents.com/18/36/38/25/18363825_40033351_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating1)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 2
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Monogram-pattern knee-length shorts";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 5) + 1)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating2 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating2);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating2));
        this.indRatingRepository.saveAll(userRatings);

        //Product 2
        product = Product.builder()
                .title(title)
                .price(719.99)
                .description("Taken from the house's '70s archives, the GG monogram; " +
                        "Guccio Gucci's initials, are a key feature of Gucci designs. " +
                        "Taking on many different iterations, it is showcased on " +
                        "these knee-length shorts in a patterned jacquard material.")
                .category(mensClothing)
                .image("https://cdn-images.farfetch-contents.com/18/05/10/41/18051041_40936990_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating2)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 3
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "VLogo embroidered cap";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating3 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating3);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating3));
        this.indRatingRepository.saveAll(userRatings);

        //Product 3
        product = Product.builder()
                .title(title)
                .price(349.99)
                .description("Valentino Garavani first started marking his creations in 1968, " +
                        "for his iconic show at The Sala Bianca in Florence. The house has since " +
                        "reimagined the iconic logo and applied it to their SS22 collection, adorning " +
                        "the front of this cotton cap with its signature VLogo.")
                .category(mensClothing)
                .image("https://cdn-images.farfetch-contents.com/17/53/00/51/17530051_40729716_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating3)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 4
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "High-waisted tailored trousers";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 3) + 3)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating4 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating4);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating4));
        this.indRatingRepository.saveAll(userRatings);

        //Product 4
        product = Product.builder()
                .title(title)
                .price(749.99)
                .description("A testimony to Valentino's impeccable tailoring, these trousers are " +
                        "all about sophistication. Crafted from virgin wool with a bit of stretch, " +
                        "the chocolate brown hue and pressed crease add a refined touch to the pair.")
                .category(mensClothing)
                .image("https://cdn-images.farfetch-contents.com/17/88/75/93/17887593_37953720_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating4)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 5
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Logo bomber jacket";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 2) + 4)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating5 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating5);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating5));
        this.indRatingRepository.saveAll(userRatings);

        //Product 5
        product = Product.builder()
                .title(title)
                .price(1389.99)
                .description("Logo bomber jacket from VALENTINO.")
                .category(mensClothing)
                .image("https://cdn-images.farfetch-contents.com/15/28/33/98/15283398_28202420_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating5)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 6
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Long-sleeve striped polo shirt";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 2) + 4)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating6 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating6);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating6));
        this.indRatingRepository.saveAll(userRatings);

        //Product 6
        product = Product.builder()
                .title(title)
                .price(1149.99)
                .description("White/blue cotton/silk long-sleeve striped polo shirt from PRADA " +
                        "featuring horizontal stripe pattern, triangle logo, polo collar, front " +
                        "button placket and long sleeves.")
                .category(mensClothing)
                .image("https://cdn-images.farfetch-contents.com/18/64/76/63/18647663_40499710_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating6)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 7
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Short-sleeved cotton shirt";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating7 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating7);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating7));
        this.indRatingRepository.saveAll(userRatings);

        //Product 7
        product = Product.builder()
                .title(title)
                .price(759.99)
                .description("Prada keeps it minimal with this cotton shirt, presented in a " +
                        "sophisticated two-tonal design in shades of blue. The brand's iconic " +
                        "triangle logo appears in a stripped-down design on the chest pocket, in " +
                        "tune with the house's new understated approach to branding.")
                .category(mensClothing)
                .image("https://cdn-images.farfetch-contents.com/18/42/63/93/18426393_39564934_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating7)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 8
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Twisted Seam denim jeans";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating8 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating8);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating8));
        this.indRatingRepository.saveAll(userRatings);

        //Product 8
        product = Product.builder()
                .title(title)
                .price(694.99)
                .description("Versace offers up a new take on denim with these straight-leg jeans " +
                        "that feature a Twisted Seam detail to each outer edge. Fabricated in Italy, " +
                        "they are detailed with a signature Medusa logo-embossed button to the front.")
                .category(mensClothing)
                .image("https://cdn-images.farfetch-contents.com/17/97/10/74/17971074_40010139_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating8)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 9
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Paint-splatter denim jacket";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating9 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating9);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating9));
        this.indRatingRepository.saveAll(userRatings);

        //Product 9
        product = Product.builder()
                .title(title)
                .price(644.99)
                .description("Paint-splatter denim jacket from OFF-WHITE featuring black/white, cotton, " +
                        "paint splatter detail, signature Diag-stripe print, front button fastening, long " +
                        "sleeves, two patch pockets and two side slit pockets.")
                .category(mensClothing)
                .image("https://cdn-images.farfetch-contents.com/18/71/70/76/18717076_40458141_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating9)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 10
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Logo plaque belt buckle";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating10 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating10);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating10));
        this.indRatingRepository.saveAll(userRatings);

        //Product 10
        product = Product.builder()
                .title(title)
                .price(429.99)
                .description("Amiri's bold take on accessories for AW22 is shown by this black " +
                        "statement belt. Crafted from soft buttery leather, it boasts silver-tone " +
                        "hardware for an industrial-inspired look whilst showcasing the brand's logo at the buckle.")
                .category(mensClothing)
                .image("https://cdn-images.farfetch-contents.com/17/92/31/93/17923193_40383125_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating10)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 11
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Ruffled silk dress";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating11 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating11);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating11));
        this.indRatingRepository.saveAll(userRatings);

        //Product 11
        product = Product.builder()
                .title(title)
                .price(3979.99)
                .description("Showcasing a '70s-inspired silhouette, this Gucci dress is crafted " +
                        "in a fluid silk fabrication. Decorated with ruffled trims, the romantic " +
                        "design is complete with GG buttons for a branded finish.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/18/55/11/10/18551110_39922671_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating11)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 12
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Wide-leg GG jeans";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating12 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating12);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating12));
        this.indRatingRepository.saveAll(userRatings);

        //Product 12
        product = Product.builder()
                .title(title)
                .price(1149.99)
                .description("Gucci's signature GG pattern is prominent across all collections. " +
                        "It is also found on these wide-leg jeans which are presented in a light " +
                        "blue colourway with the notable design throughout.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/18/81/26/79/18812679_40800508_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating12)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 13
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Graphic-print cotton sweatshirt";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating13 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating13);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating13));
        this.indRatingRepository.saveAll(userRatings);

        //Product 13
        product = Product.builder()
                .title(title)
                .price(819.99)
                .description("Graphic-print cotton sweatshirt from GUCCI featuring white, cotton, " +
                        "graphic print to the front, round neck, long sleeves and ribbed edge.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/18/74/01/77/18740177_40568518_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating13)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 14
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Violet floral-print minidress";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating14 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating14);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating14));
        this.indRatingRepository.saveAll(userRatings);

        //Product 14
        product = Product.builder()
                .title(title)
                .price(674.99)
                .description("Cut for an A-line silhouette, ZIMMERMANN's Violet minidress is crafted" +
                        " from soft cotton and decorated with a colourful floral print all over. " +
                        "The piece is detailed with an elegant sweetheart neck and puff shoulders.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/17/94/07/63/17940763_40627267_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating14)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 15
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Pleated two-tone sleeveless blouse";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating15 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating15);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating15));
        this.indRatingRepository.saveAll(userRatings);

        //Product 15
        product = Product.builder()
                .title(title)
                .price(889.99)
                .description("Marni's expertise in textiles dates back to the early '90s. The Italian " +
                        "house creates contemporary designs with sublime attention to detail. Such as " +
                        "this two-tone top, playful cream-hued pleats are juxtaposed with the navy fabric " +
                        "for a modern twist.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/18/44/98/88/18449888_39655759_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating15)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 16
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "May plissé-effect short-sleeve shirt";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating16 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating16);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating16));
        this.indRatingRepository.saveAll(userRatings);

        //Product 16
        product = Product.builder()
                .title(title)
                .price(553.99)
                .description("Named after May, this Pleats Please Issey Miyake shirt is part of " +
                        "the label's Monthly Colour series, a project that is characterized by " +
                        "refreshing colours and billowing silhouettes inspired by the ocean. " +
                        "This iteration is presented in a pastel green colourway.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/17/89/18/47/17891847_40801966_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating16)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 17
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Pleated full maxi skirt";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating17 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating17);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating17));
        this.indRatingRepository.saveAll(userRatings);

        //Product 17
        product = Product.builder()
                .title(title)
                .price(2989.99)
                .description("Never one to shy away from oversized proportions, Balenciaga present " +
                        "this full pleated maxi skirt for AW21. Crafted from silk, the A-line design " +
                        "sits high on the waist and fastens with a concealed side zipper closure.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/16/91/04/82/16910482_35698590_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating17)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 18
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Feather-trim strapless dress";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating18 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating18);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating18));
        this.indRatingRepository.saveAll(userRatings);

        //Product 18
        product = Product.builder()
                .title(title)
                .price(514.99)
                .description("The upbeat and opulent design style of De La Vali is showcased " +
                        "on this strapless dress from the AW22 collection through the use " +
                        "satin-finish material and eye-catching feather-trim detailing.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/18/03/21/36/18032136_40641692_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating18)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 19
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Pleated wide-leg trousers";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating19 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating19);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating19));
        this.indRatingRepository.saveAll(userRatings);

        //Product 19
        product = Product.builder()
                .title(title)
                .price(949.99)
                .description("Defined by its pleated detailing and knitted construction, these trousers " +
                        "by Stella McCartney follows the motto of the SS22 collection: returning to life. " +
                        "Crafted from sustainable viscose, it features an all-over metallic threading " +
                        "and a wide leg cut.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/17/96/19/45/17961945_38004049_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating19)
                .build();

        this.productRepository.save(product);

        //-----------------------------
        //Rating 20
        count = (int) (Math.random() * 8);
        userRatings = new ArrayList<>();
        title = "Box-pleat mini skirt";

        for (int i = 0; i < count; i++) {
            IndividualRating userRating = IndividualRating.builder()
                    .rate((int) (Math.random() * 4) + 2)
                    .productTitle(title)
                    .userId(users.get(i))
                    .build();

            userRatings.add(userRating);
        }
        average = userRatings.stream().mapToDouble(IndividualRating::getRate).average().orElse(0);

        AverageRating averageRating20 = AverageRating.builder()
                .rate(average)
                .count(count)
                .build();

        this.avRatingRepository.save(averageRating20);

        userRatings.forEach(x -> x.setAverageRatingId(averageRating20));
        this.indRatingRepository.saveAll(userRatings);

        //Product 20
        product = Product.builder()
                .title(title)
                .price(1983.99)
                .description("Prada is renowned for updating classic silhouettes with its signature " +
                        "branding. A fine demonstration is this pleated skirt. Boasting a virgin wool " +
                        "construction, the traditional style is elevated by the inclusion of the label's " +
                        "signature triangle logo to the belted waist, adding a recognisable and " +
                        "contemporary touch to the design.")
                .category(womensClothing)
                .image("https://cdn-images.farfetch-contents.com/18/70/15/96/18701596_40633185_1000.jpg")
                .stock((int) (Math.random() * 15) + 2)
                .ratingId(averageRating20)
                .build();

        this.productRepository.save(product);
    }

    public void externalApi(List<User> users) {
        ResponseEntity<ApiProduct[]> response = this.restTemplate.getForEntity("https://fakestoreapi.com/products", ApiProduct[].class);
        ApiProduct[] products = response.getBody();

        if (products != null) {
            for (ApiProduct product : products) {
                Product productEntity = this.mainConverter.converter(product, Product.class);
                //AverageRating ratingEntity = this.mainConverter.converter(product.getRating(), AverageRating.class);

                Category category;
                if (this.categoryRepository.findByCategory(product.getCategory()).isEmpty()) {
                    category = Category.builder()
                            .category(product.getCategory())
                            .build();

                    this.categoryRepository.save(category);
                } else
                    category = this.categoryRepository.findByCategory(product.getCategory()).get();

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

                this.avRatingRepository.save(averageRating);

                userRatings.forEach(x -> x.setAverageRatingId(averageRating));
                this.indRatingRepository.saveAll(userRatings);
                this.avRatingRepository.save(averageRating);

                productEntity.setRatingId(averageRating);
                productEntity.setCategory(category);
                productEntity.setStock((int) (Math.random() * 15) + 2);

                this.productRepository.save(productEntity);
            }
        }
    }
}
