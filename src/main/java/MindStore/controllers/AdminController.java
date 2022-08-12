package MindStore.controllers;

import MindStore.command.AdminDto;
import MindStore.command.ProductDto;
import MindStore.command.UserDto;
import MindStore.services.AdminServiceI;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@AllArgsConstructor
public class AdminController {
    private AdminServiceI adminService;

    @GetMapping("products")
    public List<ProductDto> getAllProducts(@RequestParam(value = "direction") String direction,
                                           @RequestParam(value = "field") String field,
                                           @RequestParam(value = "page") int page,
                                           @RequestParam(value = "pagesize") int pageSize) {
        return this.adminService.getAllProducts(direction, field, page, pageSize);
    }

    @GetMapping("products/{id}")
    public ProductDto getProductById(@PathVariable("id") Long id) {
        return this.adminService.getProductById(id);
    }

    @GetMapping("products/title/{title}")
    public List<ProductDto> getProductsByName(@PathVariable("title") String title) {
        return this.adminService.getProductsByName(title);
    }

    @GetMapping("users")
    public List<UserDto> getAllUsers(@RequestParam(value = "direction") String direction,
                                     @RequestParam(value = "field") String field,
                                     @RequestParam(value = "page") int page,
                                     @RequestParam(value = "pagesize") int pageSize) {
        return this.adminService.getAllUsers(direction, field, page, pageSize);
    }

    @GetMapping("users/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return this.adminService.getUserById(id);
    }

    @GetMapping("users/name/{name}")
    public List<UserDto> getUsersByName(@PathVariable("name") String name) {
        return this.adminService.getUsersByName(name);
    }

    @PostMapping
    public AdminDto addAdmin(@Valid @RequestBody AdminDto adminDto) {
        return this.adminService.addAdmin(adminDto);
    }

    @PostMapping("products")
    public ProductDto addProduct(@Valid @RequestBody ProductDto productDto) {
        return this.adminService.addProduct(productDto);
    }

    @PostMapping("users")
    public UserDto addUser(@Valid @RequestBody UserDto userDto) {
        return this.adminService.addUser(userDto);
    }

    @PatchMapping("{id}")
    public AdminDto update


    @DeleteMapping("products/{title}")
    public void deleteProduct(@PathVariable("title") String title) {
        this.adminService.deleteProduct(title);
    }
}
