package MindStore.persistence.repositories;

import MindStore.persistence.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM products WHERE products.title LIKE %:title%", nativeQuery = true)
    Optional<List<Product>> findByTitle(String title);
}
