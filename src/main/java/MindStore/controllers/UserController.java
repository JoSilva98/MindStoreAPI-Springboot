package MindStore.controllers;

import MindStore.command.ProductDto;
import MindStore.services.UserServiceI;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserServiceI userServiceI;
    @GetMapping("/products")
    public Set<ProductDto> getAllProducts() {
        return this.userServiceI.getAllProducts();
    }
}