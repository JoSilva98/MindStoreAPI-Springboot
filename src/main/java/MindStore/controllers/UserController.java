package MindStore.controllers;

import MindStore.command.*;
import MindStore.services.UserServiceI;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserServiceI userServiceI;

    //posso apresentar list porque set ja esta na entidade para ser filtrado
    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return this.userServiceI.getAllProducts();
    }

    @GetMapping("/products/byname/{title}")
    public List<ProductDto> getProductByTitle(@PathVariable("title") String title){
        return this.userServiceI.getProductsByTitle(title);
    }

    @GetMapping("/products/bycategory/{category}")
    public List<ProductDto> getProductByCategory(@PathVariable("category") String category){
        return this.userServiceI.getProductByCategory(category);
    }

    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable("id") Long id){
        return this.userServiceI.getProductById(id);
    }

    @GetMapping("/categories/{id}")
    public CategoryDto getCategoryById(@PathVariable("id") int id){
        return this.userServiceI.getCategoryById(id);
    }

    @GetMapping("/shoppingcart/{userid}")
    public List<ProductDto> getShoppingCart(@PathVariable("userid") Long userId){
        return this.userServiceI.getShoppingCart(userId);
    }

    @PostMapping
    public UserDto signUp(@Valid @RequestBody UserDto userDto){
        return this.userServiceI.signUp(userDto);
    }

    @PatchMapping("/{id}")
    public UserDto updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateDto userUpdateDto){
        return this.userServiceI.updateUser(id, userUpdateDto);
    }

    @PostMapping("/rating")
    public RatingDto giveRating(@RequestParam(value = "userid") Long userId,
                                @RequestParam(value = "productid") Long productId,
                                @RequestParam(value = "rating") double rating){
        return this.userServiceI.giveRating(userId, productId, rating);
    }

    //update rating?

    //filter price
    @GetMapping("/price")
    public List<ProductDto> filterByPrice(@RequestParam(value = "direction") String direction){
        return this.userServiceI.filterByPrice(direction);
    }

    //rating e alfabetic orders (fields)
    @GetMapping
    public List<ProductDto> filterByRatingAndAlphabetic(@RequestParam(value = "field") String field,
                                                        @RequestParam(value = "direction") String direction){
        return this.userServiceI.filterByRatingAndAlphabetic(field, direction);
    }

}