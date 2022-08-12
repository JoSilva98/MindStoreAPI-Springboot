package MindStore.controllers;

import MindStore.command.CategoryDto;
import MindStore.command.ProductDto;
import MindStore.command.UserDto;
import MindStore.command.UserUpdateDto;
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


    //filter price
    //filter rating
    //filter alphabetic
    //give rating
}