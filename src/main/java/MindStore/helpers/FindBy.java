package MindStore.helpers;

import MindStore.command.productDto.IndividualRatingDto;
import MindStore.exceptions.NotFoundException;
import MindStore.persistence.models.Person.Admin;
import MindStore.persistence.models.Person.Person;
import MindStore.persistence.models.Person.Role;
import MindStore.persistence.models.Person.User;
import MindStore.persistence.models.Product.Category;
import MindStore.persistence.models.Product.IndividualRating;
import MindStore.persistence.models.Product.Product;
import MindStore.persistence.repositories.Person.AdminRepository;
import MindStore.persistence.repositories.Person.PersonRepository;
import MindStore.persistence.repositories.Person.RoleRepository;
import MindStore.persistence.repositories.Person.UserRepository;
import MindStore.persistence.repositories.Product.CategoryRepository;
import MindStore.persistence.repositories.Product.IndividualRatingRepository;
import MindStore.persistence.repositories.Product.ProductRepository;

public class FindBy {
    public static Product findProductById(Long id, ProductRepository productRepository) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    public static Category findCategoryById(int id, CategoryRepository categoryRepository) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
    }

    public static User findUserById(Long id, UserRepository userRepository) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public static Role findRoleById(int id, RoleRepository roleRepository) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role not found"));
    }

    public static Admin findAdminById(Long id, AdminRepository adminRepository) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Admin not found"));
    }

    public static IndividualRating findRatingById(Long id, IndividualRatingRepository indRatingRepository) {
        return indRatingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rating not found"));
    }
}
