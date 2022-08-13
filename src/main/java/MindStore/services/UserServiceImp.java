package MindStore.services;

import MindStore.command.*;
import MindStore.converters.MainConverterI;
import MindStore.enums.DirectionEnum;
import MindStore.enums.ProductFieldsEnum;
import MindStore.enums.RoleEnum;
import MindStore.exceptions.ConflictException;
import MindStore.exceptions.NotAllowedValueException;
import MindStore.helpers.ValidateParams;
import MindStore.persistence.models.Person.Role;
import MindStore.persistence.models.Person.User;
import MindStore.persistence.models.Product.Rating;
import MindStore.persistence.repositories.Person.RoleRepository;
import MindStore.persistence.repositories.Person.UserRepository;
import MindStore.exceptions.NotFoundException;
import MindStore.persistence.models.Product.Category;
import MindStore.persistence.models.Product.Product;
import MindStore.persistence.repositories.Product.CategoryRepository;
import MindStore.persistence.repositories.Product.ProductRepository;
import MindStore.persistence.repositories.Product.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static MindStore.helpers.ValidateParams.validatePages;

@Service
@AllArgsConstructor
//@NoArgsConstructor aqui nao pode senao nao inicializa o rep e converter(?)
public class UserServiceImp implements UserServiceI {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private RatingRepository ratingRepository;
    private MainConverterI mainConverter;
    private DecimalFormat decimalFormat;


    @Override
    public List<ProductDto> getAllProducts(String direction, String field, int page, int pageSize) {
        //valida parametros das paginas (classe a parte), italico é static
        validatePages(page, pageSize);

        List<Product> products;
        switch (direction){
            //enum nosso e no findproducts funçao do java para dar a direção
            case DirectionEnum.ASC -> products = findProducts(Sort.Direction.ASC, field, page, pageSize)
                    .stream().toList();
            case DirectionEnum.DESC -> products = findProducts(Sort.Direction.DESC, field, page, pageSize)
                    .stream().toList();
            default -> throw new NotAllowedValueException("Direction not allowed");
        }
        return this.mainConverter.listConverter(products, ProductDto.class);
    }

    @Override
    public List<ProductDto> getProductsByTitle(String title, String direction, String field, int page, int pageSize) {
        validatePages(page, pageSize);

        List<Product> productsList = this.productRepository.findByTitleLike(title);
        if (productsList.isEmpty()) {
            throw new NotFoundException("No products found with such name");
        }
        switch (direction){
            //enum nosso e no findproducts funçao do java para dar a direção
            case DirectionEnum.ASC -> productsList = findProducts(Sort.Direction.ASC, field, page, pageSize)
                    .stream().toList();
            case DirectionEnum.DESC -> productsList = findProducts(Sort.Direction.DESC, field, page, pageSize)
                    .stream().toList();
            default -> throw new NotAllowedValueException("Direction not allowed");
        }
        return this.mainConverter.listConverter(productsList, ProductDto.class);
    }

    @Override
    public List<ProductDto> getProductByCategory(String category, String direction, String field, int page, int pageSize) {
        Category categoryEntity = this.categoryRepository.findByCategory(category)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        List<Product> productList = categoryEntity.getProductList();
        if (productList.isEmpty()) {
            throw new NotFoundException("No products found with that category");
        }

        switch (direction){
            //enum nosso e no findproducts funçao do java para dar a direção
            case DirectionEnum.ASC -> productList = findProducts(Sort.Direction.ASC, field, page, pageSize)
                    .stream().toList();
            case DirectionEnum.DESC -> productList = findProducts(Sort.Direction.DESC, field, page, pageSize)
                    .stream().toList();
            default -> throw new NotAllowedValueException("Direction not allowed");
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
    public List<ProductDto> getShoppingCart(Long userId, String direction, String field, int page, int pageSize) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Set<Product> productList = user.getShoppingCart();

        switch (direction) {
            //enum nosso e no findproducts funçao do java para dar a direção
            case DirectionEnum.ASC -> productList = findProducts(Sort.Direction.ASC, field, page, pageSize)
                    .stream().collect(Collectors.toSet());
            case DirectionEnum.DESC -> productList = findProducts(Sort.Direction.DESC, field, page, pageSize)
                    .stream().collect(Collectors.toSet());
            default -> throw new NotAllowedValueException("Direction not allowed");

        }
            return productList.stream()
                    .map(product -> mainConverter.converter(product, ProductDto.class))
                    .toList();
    }

    @Override
    public UserDto signUp(UserDto userDto) {
        this.userRepository.findByEmail(userDto.getEmail())
                .ifPresent((student) -> {
                    throw new ConflictException("User already exists");
                });

        User userToSave = this.mainConverter.converter(userDto, User.class);

        //dar set do role, primeiro temos que o encontrar no rep
        Role role = this.roleRepository.findById(RoleEnum.USER)
                .orElseThrow(() -> new NotFoundException("Role not found"));

        userToSave.setRoleId(role);
        this.userRepository.save(userToSave);

        return this.mainConverter.converter(userToSave, UserDto.class);
    }

    @Override
    public ResponseEntity<String> buyProducts(Long id, int payment) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        double totalPrice = user.getShoppingCart()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();

        if (totalPrice == 0) throw new NotFoundException("Your shopping cart is empty");

        if (payment < totalPrice)
            return new ResponseEntity<>("You don't have enough money", HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<>("Payment accepted!", HttpStatus.OK);
    }

    @Override
    public UserDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        User updatedUser = this.mainConverter.updateConverter(userUpdateDto, user);
        this.userRepository.save(updatedUser);

        return this.mainConverter.converter(updatedUser, UserDto.class);
    }

    @Override
    public RatingDto giveRating(Long userId, Long productId, double rating) {
        if (rating < 0 || rating > 5) {
            throw new ConflictException("Rating value should be between 0 and 5");
        }

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        /*
        ver o count da rating associada ao produto
        adicionar a nossa e fazer nova media e fazer set count + 1 e set rating nova
         */

        double oldRating = product.getRatingId().getRate();
        int ratingCount = product.getRatingId().getCount(); //120

        double newRating = oldRating + ((rating - oldRating) / (ratingCount + 1));
        //para por a rating so com 1 casa decimal, o parse double pq decimal format devolve string
        double newRatingFormatted = Double.parseDouble(decimalFormat.format(newRating));

        product.getRatingId().setRate(newRatingFormatted);
        product.getRatingId().setCount(ratingCount + 1);
        this.productRepository.save(product);

        //product.getRatingId() vai buscar o objeto rating
        return this.mainConverter.converter(product.getRatingId(), RatingDto.class);
    }

    @Override
    public List<ProductDto> filterByPrice(String direction) {

        return switch (direction) {
            case DirectionEnum.ASC -> ascendingDirectionPrice(direction);
            case DirectionEnum.DESC -> descendingDirectionPrice(direction);
            default -> throw new NotFoundException("Input is not a direction");
        };
    }

    @Override
    public List<ProductDto> filterByRatingOrTitle(String field, String direction) {
        List<ProductDto> productList = new ArrayList<>();

        switch (field) {
            case ProductFieldsEnum.RATING -> {
                //ignore case para poder por asc
                if (direction.equalsIgnoreCase(DirectionEnum.ASC)) {
                    productList = ascendingRating(direction);
                } else if (direction.equals(DirectionEnum.DESC)) {
                    productList = descendingRating(direction);
                }
            } case ProductFieldsEnum.TITLE -> {
                if(direction.equalsIgnoreCase(DirectionEnum.ASC)) {
                    productList = ascendingTitle(direction);
                } else if (direction.equals(DirectionEnum.DESC)){
                    productList = descendingTitle(direction);
                }
            }
            default -> throw new NotFoundException("Field not found");
        }
        return productList;
    }

    //rating and alphabetic:

    private List<ProductDto> ascendingRating(String direction) {
        //tabela rating e ordenar e buscar products associadoas
        List<Rating> ratings = this.ratingRepository.findAll(
                Sort.by(Sort.Direction.ASC, "rate")
        );

        List<Product> productList = ratings.stream()
                .map((rating) -> rating.getProductId())
                .toList();

        return this.mainConverter.listConverter(productList, ProductDto.class);
    }

    private List<ProductDto> descendingRating(String direction) {

        List<Rating> ratings = this.ratingRepository.findAll(
                Sort.by(Sort.Direction.DESC, "rate")
        );

        List<Product> productList = ratings.stream()
                .map((rating) -> rating.getProductId())
                .toList();

        return this.mainConverter.listConverter(productList, ProductDto.class);
    }

    private List<ProductDto> ascendingTitle(String direction) {
        List<Product> productList = this.productRepository.findAll(
                Sort.by(Sort.Direction.ASC, "title")
        );

        return this.mainConverter.listConverter(productList, ProductDto.class);
    }

    private List<ProductDto> descendingTitle(String direction) {
        List<Product> productList = this.productRepository.findAll(
                Sort.by(Sort.Direction.DESC, "title")
        );

        return this.mainConverter.listConverter(productList, ProductDto.class);
    }

    //price
    private List<ProductDto> ascendingDirectionPrice(String direction) {
        /*
        vai a tabela products e vai fazer por ordem descendetente com base na coluna preço
         */
        List<Product> productList = this.productRepository.findAll(
                Sort.by(Sort.Direction.ASC, "price")
        );

        return this.mainConverter.listConverter(productList, ProductDto.class);
    }

    private List<ProductDto> descendingDirectionPrice(String direction) {
        List<Product> productList = this.productRepository.findAll(
                Sort.by(Sort.Direction.DESC, "price")
        );

        return this.mainConverter.listConverter(productList, ProductDto.class);
    }

    //funçao paginação do get All products
    private Page<Product> findProducts(Sort.Direction direction, String field, int page, int pageSize) {
        return this.productRepository.findAll(
                //vai buscar a pagina nr page-1 (para n poderem por pagina 0), e qtos queres
                PageRequest.of(page - 1, pageSize)
                        //sort com base na direção e no field do objeto (title, id)
                        .withSort(Sort.by(direction, field))
        );
    }
}
