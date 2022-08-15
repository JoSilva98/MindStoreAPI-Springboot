package MindStore.persistence.repositories.Product;

import MindStore.persistence.models.Product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT * From categories WHERE UPPER(categories.category) = UPPER(:category)", nativeQuery = true)
    Optional<Category> findByCategory(String category);

}
