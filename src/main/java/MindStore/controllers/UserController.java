package MindStore.controllers;

import MindStore.command.ProductDto;
import MindStore.services.UserServiceI;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}