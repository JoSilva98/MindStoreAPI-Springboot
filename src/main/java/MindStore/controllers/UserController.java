package MindStore.controllers;

import MindStore.command.personDto.UserDto;
import MindStore.command.personDto.UserUpdateDto;
import MindStore.command.productDto.CategoryDto;
import MindStore.command.productDto.IndividualRatingDto;
import MindStore.command.productDto.ProductDto;
import MindStore.command.productDto.AverageRatingDto;
import MindStore.services.UserServiceI;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserServiceI userServiceI;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts(@RequestParam(value = "direction") String direction,
                                           @RequestParam(value = "field") String field,
                                           @RequestParam(value = "page") int page,
                                           @RequestParam(value = "pagesize") int pageSize) {
        return this.userServiceI.getAllProducts(direction, field, page, pageSize);
    }

    @GetMapping("/products/price")
    public List<ProductDto> filterByPrice(@RequestParam(value = "direction") String direction,
                                          @RequestParam(value = "page") int page,
                                          @RequestParam(value = "pagesize") int pageSize,
                                          @RequestParam(value = "min") int minPrice,
                                          @RequestParam(value = "max") int maxPrice) {
        return this.userServiceI.filterByPrice(direction, page, pageSize, minPrice, maxPrice);
    }

    @GetMapping("/products/rating")
    public List<ProductDto> filterByRating(@RequestParam(value = "direction") String direction,
                                          @RequestParam(value = "page") int page,
                                          @RequestParam(value = "pagesize") int pageSize,
                                          @RequestParam(value = "min") int minRating,
                                          @RequestParam(value = "max") int maxRating) {
        return this.userServiceI.filterByRating(direction, page, pageSize, minRating, maxRating);
    }

    @GetMapping("/products/name")
    public List<ProductDto> getProductByTitle(@RequestParam(value = "title") String title,
                                              @RequestParam(value = "page") int page,
                                              @RequestParam(value = "pagesize") int pageSize) {
        return this.userServiceI.getProductsByTitle(title, page, pageSize);
    }

    @GetMapping("/products/category")
    public List<ProductDto> getProductByCategory(@RequestParam(value = "direction") String direction,
                                                 @RequestParam(value = "category") String category,
                                                 @RequestParam(value = "page") int page,
                                                 @RequestParam(value = "pagesize") int pageSize) {
        return this.userServiceI.getProductByCategory(direction, category, page, pageSize);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return this.userServiceI.getUserById(id);
    }

    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable("id") Long id) {
        return this.userServiceI.getProductById(id);
    }

    @GetMapping("/categories/{id}")
    public CategoryDto getCategoryById(@PathVariable("id") int id) {
        return this.userServiceI.getCategoryById(id);
    }

    @GetMapping("/shoppingcart/{userid}")
    public List<ProductDto> getShoppingCart(@PathVariable("userid") Long userId) {
        return this.userServiceI.getShoppingCart(userId);
    }

    @PatchMapping("addtocart")
    public List<ProductDto> addProductToCart(@RequestParam(value = "userid") Long userId,
                                             @RequestParam(value = "productid") Long productId) {
        return this.userServiceI.addProductToCart(userId, productId);
    }

    @PatchMapping("removefromcart")
    public List<ProductDto> removeProductFromCart(@RequestParam(value = "userid") Long userId,
                                                  @RequestParam(value = "productid") Long productId) {
        return this.userServiceI.removeProductFromCart(userId, productId);
    }

    @PatchMapping("clearcart/{userid}")
    public List<ProductDto> removeAllProductsFromCart(@PathVariable("userid") Long userId) {
        return this.userServiceI.removeAllProductsFromCart(userId);
    }

    @PostMapping("buy/{id}")
    public ResponseEntity<String> buyProducts(@PathVariable("id") Long id,
                                              @Valid @RequestBody int payment) {
        return this.userServiceI.buyProducts(id, payment);
    }

    @GetMapping("/shoppingcart/price/{userid}")
    public String getCartTotalPrice(@PathVariable("userid") Long userId) {
        return this.userServiceI.getCartTotalPrice(userId) + "€";
    }

    @PatchMapping("/{id}")
    public UserDto updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        return this.userServiceI.updateUser(id, userUpdateDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        this.userServiceI.deleteUser(id);
    }

    @GetMapping("/rating/{userid}")
    public List<IndividualRatingDto> getRatingList(@PathVariable("userid") Long userId) {
        return this.userServiceI.getRatingList(userId);
    }

    @PostMapping("/rating")
    public AverageRatingDto rateProduct(@RequestParam(value = "userid") Long userId,
                                        @RequestParam(value = "productid") Long productId,
                                        @RequestParam(value = "rating") int rating) {
        return this.userServiceI.rateProduct(userId, productId, rating);
    }

    @DeleteMapping("/rating")
    public void deleteRate(@RequestParam(value = "userid") Long userId,
                           @RequestParam(value = "ratingid") Long ratingId) {
        this.userServiceI.deleteRate(userId, ratingId);
    }

    @PostMapping
    public UserDto signUp(@Valid @RequestBody UserDto userDto) {
        return this.userServiceI.signUp(userDto);
    }
}
