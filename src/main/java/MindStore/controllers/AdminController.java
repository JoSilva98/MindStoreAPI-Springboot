package MindStore.controllers;

import MindStore.command.ProductDto;
import MindStore.services.AdminServiceI;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class AdminController {
    private AdminServiceI adminService;

    @GetMapping("products")
    public List<ProductDto> getProducts() {
        return this.adminService.getProducts();
    }

    @GetMapping("id")
    public ProductDto getProductById(@PathVariable("id") Long id) {
        return this.adminService.getProductById(id);
    }

    @GetMapping("products/{title}")
    public List<ProductDto> getProductsByName(@PathVariable("title") String title) {
        return this.adminService.getProductsByName(title);
    }


}
